package com.distribuida.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class AppConfig {

    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        System.out.println("*************JpaInit::em");
        emf = Persistence.createEntityManagerFactory("pu-distribuida");
    }

    @Produces
    public EntityManager em() {
        System.out.println("**********JpaConfig::em");
        return emf.createEntityManager();
    }

}
