package com.baro.app.web.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baro.app.web.persistence.model.Customer;
import com.baro.app.web.persistence.service.UserService;

@Slf4j
@Controller
public class UserApiController {

    @Autowired  
    private UserService userService;

    public static final String USERS_ENTRY_URL = "/usr/users";
    public static final String USERS_SEARCH_URL = "/usr/search";
    public static final String USERS_BY_ID_ENTRY_URL = USERS_ENTRY_URL + "/{id}";

    @ResponseBody
    @RequestMapping(value = USERS_SEARCH_URL, method = RequestMethod.GET)
    public Collection<Customer> search(@RequestParam("q") String query) throws Exception {
        Collection<Customer> customers = userService.search(query);
        //log.debug(String.format("retrieved %s results for search query '%s'", Integer.toString(customers.size()), query));
        return customers;
    }

    @ResponseBody
    @RequestMapping(value = USERS_BY_ID_ENTRY_URL, method = RequestMethod.GET)
    public Customer customerById(@PathVariable  Integer id) {
        return this.userService.getCustomerById(id);
    }

    @ResponseBody
    @RequestMapping(value = USERS_ENTRY_URL, method = RequestMethod.GET)
    public List<Customer> customers() {
        return this.userService.getAllCustomers();
    }

    @ResponseBody
    @RequestMapping(value = USERS_ENTRY_URL, method = RequestMethod.PUT)
    public Integer addCustomer(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.createCustomer(firstName, lastName, new Date()).getId();
    }

    @ResponseBody
    @RequestMapping(value = USERS_BY_ID_ENTRY_URL, method = RequestMethod.POST)
    public Integer updateCustomer(@PathVariable  Integer id, @RequestBody Customer customer) {
        userService.updateCustomer(id, customer.getFirstName(), customer.getLastName(), customer.getSignupDate());
        return id;
    }
}