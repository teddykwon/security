package com.baro.app.web.persistence.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baro.app.web.persistence.model.Customer;

@Service
@SuppressWarnings("unchecked")
@Transactional
public class UserService {

    static private final String CUSTOMERS_REGION = "customers";

    @PersistenceContext
    private EntityManager em;

    public Customer createCustomer(String firstName, String lastName, Date signupDate) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setSignupDate(signupDate);
        em.persist(customer);
        return customer;
    }

    public Collection<Customer> search(String name) {
        String sqlName = ("%" + name + "%").toLowerCase();
        String sql = "select c.* from customer c where (LOWER( c.firstName ) LIKE :fn OR LOWER( c.lastName ) LIKE :ln)";
        return em.createNativeQuery(sql, Customer.class)
                .setParameter("fn", sqlName)
                .setParameter("ln", sqlName)
                .getResultList();
    }


    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return em.createQuery("FROM "+ getClassName(Customer.class)).getResultList();
    }


    @Cacheable(CUSTOMERS_REGION)
    @Transactional(readOnly = true)
    public Customer getCustomerById(Integer id) {
        return em.find(Customer.class, id);
    }

    @CacheEvict(CUSTOMERS_REGION)
    public void deleteCustomer(Integer id) {
        Customer customer = getCustomerById(id);
        em.remove(customer);
    }

    @CacheEvict(value = CUSTOMERS_REGION, key = "#id")
    public void updateCustomer(Integer id, String fn, String ln, Date birthday) {
        Customer customer = getCustomerById(id);
        customer.setLastName(ln);
        customer.setSignupDate(birthday);
        customer.setFirstName(fn);
        //sessionFactory.getCurrentSession().update(customer);
        em.merge(customer);
    }
    
    static <T> String getClassName(Class<T> clazz){
		Matcher matcher = Pattern.compile(".*\\.").matcher(clazz.getName());
		matcher.find();
		return matcher.replaceAll("");
    }
}
