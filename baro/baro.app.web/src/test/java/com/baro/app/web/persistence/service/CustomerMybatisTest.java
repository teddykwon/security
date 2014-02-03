package com.baro.app.web.persistence.service;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.baro.app.web.config.MybatisConfig;
import com.baro.app.web.config.PersistenceConfig;
import com.baro.app.web.config.WebConfig;
import com.baro.app.web.persistence.mapper.CustomerMapper;
import com.baro.app.web.persistence.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("default")
@ContextConfiguration(classes = {PersistenceConfig.class, WebConfig.class, MybatisConfig.class}, loader = AnnotationConfigContextLoader.class)
public class CustomerMybatisTest {

	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void selectListTest() {
		CustomerMapper cm = sqlSession.getMapper(CustomerMapper.class);
		List<Customer> list = cm.getAllCustomers();
		
		for(Customer c : list){
			System.out.println("list : " + c.getFirstName() + " " + c.getLastName());
		}
	}

	@Test
	public void selectOneTest() {
		Customer customer = sqlSession.selectOne("com.baro.app.web.persistence.mapper.CustomerMapper.getCustomerById", 9);
		System.out.println("each : " + customer.getFirstName() + " " + customer.getLastName());
	}
}
