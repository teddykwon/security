package com.baro.app.web.improved.security.securedobject.impl;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.baro.app.web.config.MybatisConfig;
import com.baro.app.web.config.PersistenceConfig;
import com.baro.app.web.config.WebConfig;
import com.baro.app.web.config.WebSecurityConfig;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={WebConfig.class, PersistenceConfig.class, MybatisConfig.class, WebSecurityConfig.class})
public class SecuredObjectDAOTest {
	@Autowired
	SecuredObjectDAO dao;

	@Test
	public void test() {
		assertThat(dao).isNotNull();
	}

}
