package com.baro.app.web.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baro.app.web.persistence.model.Customer;

public interface CustomerMapper {

	@Select("SELECT * FROM CUSTOMER")
	public List<Customer> getAllCustomers();
	
	@Select("SELECT * FROM CUSTOMER WHERE ID = #{id}")
	public Customer getCustomerById(int id);
	
	public void updateCustomer(Customer customer);
	
}
