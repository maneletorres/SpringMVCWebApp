package com.maneletorres.springmvc.services.jpaservices;

import com.maneletorres.springmvc.domain.security.Role;
import com.maneletorres.springmvc.services.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class RoleServiceJPADaoImpl extends AbstractJPADaoService implements RoleService {
    @Override
    public List<Role> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Role.class, id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Role savedRole = em.merge(domainObject); // Creates a new object that doesn't exist or merge the properties of that object if it does exist.
        em.getTransaction().commit();

        return savedRole;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Role.class, id));
        em.getTransaction().commit();
    }
}
