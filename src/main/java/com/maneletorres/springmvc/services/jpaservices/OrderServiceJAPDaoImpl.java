package com.maneletorres.springmvc.services.jpaservices;

import com.maneletorres.springmvc.domain.Order;
import com.maneletorres.springmvc.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class OrderServiceJAPDaoImpl extends AbstractJPADaoService implements OrderService {
    @Override
    public List<Order> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Order", Order.class).getResultList();
    }

    @Override
    public Order getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Order.class, id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Order savedOrder = em.merge(domainObject); // Creates a new object that doesn't exist or merge the properties of that object if it does exist.
        em.getTransaction().commit();

        return savedOrder;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Order.class, id));
        em.getTransaction().commit();
    }
}
