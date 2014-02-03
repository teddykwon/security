package com.baro.app.web.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baro.app.web.persistence.model.Customer;
import com.baro.app.web.persistence.service.CustomerService;

@Controller
public class CustomerApiController {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomerApiController.class);

    @Autowired  
    private CustomerService customerService;

    public static final String CUSTOMERS_ENTRY_URL = "/crm/customers";
    public static final String CUSTOMERS_SEARCH_URL = "/crm/search";
    public static final String CUSTOMERS_BY_ID_ENTRY_URL = CUSTOMERS_ENTRY_URL + "/{id}";

    @ResponseBody
    @RequestMapping(value = CUSTOMERS_SEARCH_URL, method = RequestMethod.GET)
    public Collection<Customer> search(@RequestParam("q") String query) throws Exception {
        Collection<Customer> customers = customerService.search(query);
        log.debug(String.format("retrieved %s results for search query '%s'", Integer.toString(customers.size()), query));
        return customers;
    }

    @ResponseBody
    @RequestMapping(value = CUSTOMERS_BY_ID_ENTRY_URL, method = RequestMethod.GET)
    public Customer customerById(@PathVariable  Integer id) {
        return this.customerService.getCustomerById(id);
    }

    @ResponseBody
    @RequestMapping(value = CUSTOMERS_ENTRY_URL, method = RequestMethod.POST)
    public List<Customer> customers() {
        return this.customerService.getAllCustomers();
    }

    @ResponseBody
    @RequestMapping(value = CUSTOMERS_ENTRY_URL, method = RequestMethod.PUT)
    public Integer addCustomer(@RequestParam String firstName, @RequestParam String lastName) {
        return customerService.createCustomer(firstName, lastName, new Date()).getId();
    }

    @ResponseBody
    @RequestMapping(value = CUSTOMERS_BY_ID_ENTRY_URL, method = RequestMethod.POST)
    public Integer updateCustomer(@PathVariable  Integer id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer.getFirstName(), customer.getLastName(), customer.getSignupDate());
        return id;
    }
}
