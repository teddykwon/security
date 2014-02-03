package com.baro.app.web.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.baro.app.web.persistence.mapper")
public class MybatisConfig {

	@Autowired
	public DataSource restDataSource;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(restDataSource);
		return ssf.getObject();
	}
	
	@Bean
	public SqlSession sqlSession() throws Exception{
		return new SqlSessionTemplate(this.sqlSessionFactory());
	}
}
