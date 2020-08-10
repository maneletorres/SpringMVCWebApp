package com.maneletorres.springmvc.services;

import domain.Customer;
import domain.DomainObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public void loadDomainObjects() {
        domainMap = new HashMap<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Michael");
        customer1.setLastName("Orton Towers");
        customer1.setEmail("michael_orton@test.com");
        customer1.setPhoneNumber("605111111");
        customer1.setAddressLineOne("C/Mayor, nº51");
        customer1.setAddressLineTwo("Avda. Principal, 25A");
        customer1.setCity("Alcorcón");
        customer1.setState("Madrid");
        customer1.setZipCode("28922");

        domainMap.put(1, customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Heather");
        customer2.setLastName("Jones");
        customer2.setEmail("heather_jones@test.com");
        customer2.setPhoneNumber("777111325");
        customer2.setAddressLineOne("C/False, nº1");
        customer2.setAddressLineTwo("Plaza Mayor, nº5");
        customer2.setCity("London");
        customer2.setState("England");
        customer2.setZipCode("EC2P");

        domainMap.put(2, customer2);
    }
}
