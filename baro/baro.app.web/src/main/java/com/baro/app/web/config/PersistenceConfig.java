package com.baro.app.web.config;

import java.util.Arrays;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baro.app.web.persistence.service.CustomerService;

/**
 * 
 * @author 김경진
 * @since 2014.01.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일                          수정자                   수정내용
 *  -------        --------    ---------------------------
 *   2014.01.25     손호성                   vm argument spring.profiles.dbtype 추가                
 *
 * </pre>
 */
@Configuration
@EnableTransactionManagement
//@PropertySource({ "classpath:properties/persistence-mysql.properties" })
//@PropertySource({ "classpath:properties/persistence-altibase.properties" })
@PropertySource({ "classpath:properties/persistence-${spring.profiles.dbtype}.properties" })
@ComponentScan(basePackageClasses = {CustomerService.class})
public class PersistenceConfig {

	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(restDataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.baro.app.web.persistence.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;

	}

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean( DataSource dataSource  ) throws Exception {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource( dataSource );
        em.setPackagesToScan(new String[] { "com.baro.app.web.persistence.model" });
        em.setPersistenceProvider(new HibernatePersistence());
        em.setJpaProperties(hibernateProperties());
        return em;
    }

	@Bean
	public DataSource restDataSource() {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
		return dataSource;

	}

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) throws Exception {
        return new JpaTransactionManager(entityManagerFactory);
    }
    
	@Bean
	public HibernateTransactionManager transactionManager() {

		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		return txManager;

	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

		return new PersistenceExceptionTranslationPostProcessor();

	}
	
    @Bean
    public CacheManager cacheManager() throws Exception {
        SimpleCacheManager scm = new SimpleCacheManager();
        Cache cache = new ConcurrentMapCache("customers");
        scm.setCaches(Arrays.asList(cache));
        return scm;
    }

	Properties hibernateProperties() {

		return new Properties() {
			
			private static final long serialVersionUID = 1L;

			{
				setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
				setProperty(org.hibernate.cfg.Environment.DIALECT, env.getProperty("hibernate.dialect"));
				setProperty(org.hibernate.cfg.Environment.GLOBALLY_QUOTED_IDENTIFIERS, "true");
				setProperty(org.hibernate.cfg.Environment.SHOW_SQL, "true");
//				setProperty(org.hibernate.cfg.Environment.HBM2DDL_IMPORT_FILES, "import_h2.sql");

			}

		};

	}
}
