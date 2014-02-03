package com.baro.app.web.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.RequestMatcher;

import com.baro.app.web.improved.security.intercept.EgovReloadableFilterInvocationSecurityMetadataSource;
import com.baro.app.web.improved.security.intercept.UrlResourcesMapFactoryBean;
import com.baro.app.web.improved.security.securedobject.EgovSecuredObjectService;
import com.baro.app.web.improved.security.securedobject.impl.SecuredObjectDAO;
import com.baro.app.web.improved.security.securedobject.impl.SecuredObjectServiceImpl;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WebSecurityConfig.class);
	@Autowired
	private DataSource dataSource;
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("user").roles("USER")
		.and()
		.withUser("admin").password("admin").roles("ADMIN", "USER");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/js/**")
		.antMatchers("/css/**")
		.antMatchers("/img/**")
		.antMatchers("/message/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		//.csrf().disable()
		.authorizeRequests()
		//.antMatchers("/signup", "/about").permitAll()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/common/login").permitAll();
	}
	
	@Bean
	public FilterSecurityInterceptor getFilterSecurityInterceptor() throws Exception{
		FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();
		
		log.debug("==========set FilterSecurityInterceptor");
		System.out.println("==========set FilterSecurityInterceptor");
		interceptor.setAuthenticationManager(authenticationManagerBean());
		interceptor.setAccessDecisionManager(accessDecisionManager());
		interceptor.setSecurityMetadataSource(getFilterInvocationSecurityMetadataSource());
		
		return interceptor;
	}
	
    @Bean 
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter> voters = Arrays.<AccessDecisionVoter>asList(new RoleVoter(), new WebExpressionVoter());
        return new AffirmativeBased(voters);
    }	
    
	@Bean
	public FilterInvocationSecurityMetadataSource getFilterInvocationSecurityMetadataSource() throws Exception{
		EgovReloadableFilterInvocationSecurityMetadataSource source = new EgovReloadableFilterInvocationSecurityMetadataSource(getRequestMap());
		source.setSecuredObjectService(getSecuredObjectService());
		return source;
	}
	
	@Bean 
	public LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> getRequestMap() throws Exception{
		UrlResourcesMapFactoryBean map = new UrlResourcesMapFactoryBean();
		map.setSecuredObjectService(getSecuredObjectService());
		map.init();
		return map.getObject();
	}
	
	@Bean
	public EgovSecuredObjectService getSecuredObjectService(){
		SecuredObjectServiceImpl service = new SecuredObjectServiceImpl();
		service.setSecuredObjectDAO(getSecuredObjectDAO());
		return service;
	}
	
	@Bean
	public SecuredObjectDAO getSecuredObjectDAO(){
		SecuredObjectDAO dao = new SecuredObjectDAO();
		dao.setDataSource(dataSource);
		return dao;
	}
	
}
