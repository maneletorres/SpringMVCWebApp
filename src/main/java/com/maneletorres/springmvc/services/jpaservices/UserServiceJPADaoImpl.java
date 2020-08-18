package com.maneletorres.springmvc.services.jpaservices;

import com.maneletorres.springmvc.domain.User;
import com.maneletorres.springmvc.services.UserService;
import com.maneletorres.springmvc.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class UserServiceJPADaoImpl extends AbstractJPADaoService implements UserService {
    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<User> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        if (domainObject.getPassword() != null) {
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }

        User savedUser = em.merge(domainObject); // Creates a new object that doesn't exist or merge the properties of that object if it does exist.
        em.getTransaction().commit();

        return savedUser;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
    }
}
