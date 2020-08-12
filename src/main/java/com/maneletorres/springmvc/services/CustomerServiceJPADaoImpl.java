package com.maneletorres.springmvc.services;

import com.maneletorres.springmvc.domain.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
@Profile("jpadao")
public class CustomerServiceJPADaoImpl implements CustomerService {
    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Customer> listAll() {
        EntityManager em = emf.createEntityManager();

        List<Customer> customers = em.createQuery("from Customer", Customer.class).getResultList();

        em.close();

        return customers;
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.close();

        return customer;
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Customer savedCustomer = em.merge(domainObject);
        em.getTransaction().commit();
        em.close();

        return savedCustomer;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
        em.close();
    }
}
