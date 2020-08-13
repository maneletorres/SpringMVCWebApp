package com.maneletorres.springmvc.services.jpaservices;

import com.maneletorres.springmvc.domain.Product;
import com.maneletorres.springmvc.services.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class ProductServiceJPADaoImpl extends AbstractJPADaoService implements ProductService {
    @Override
    public List<Product> listAll() {
        EntityManager em = emf.createEntityManager();

        List<Product> products = em.createQuery("from Product", Product.class).getResultList();
        em.close();

        return products;
    }

    @Override
    public Product getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();

        return product;
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Product savedProduct = em.merge(domainObject); // Creates a new object that doesn't exist or merge the properties of that object if it does exist.
        em.getTransaction().commit();
        em.close();

        return savedProduct;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Product.class, id));
        em.getTransaction().commit();
        em.close();
    }
}
