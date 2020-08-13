package com.maneletorres.springmvc.services.jpaservices;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class AbstractJPADaoService {
    protected EntityManagerFactory emf;

    @PersistenceUnit // This annotation forms part of JPA Standard. Dependency injection.
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
