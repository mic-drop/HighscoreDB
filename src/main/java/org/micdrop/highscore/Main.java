package org.micdrop.highscore;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        // Use the test persistence unit to configure a new
// entity manager factory and start up JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

// Open a new database connection by getting a new
// entity manager from the entity manager factory
        EntityManager em = emf.createEntityManager();

// Used to test the database connection, should return 2
        System.out.println("Result: " + em.createNativeQuery("select 1 + 1").getSingleResult());

// Close the database connection
        em.close();

// Shutdown JPA
        emf.close();
    }
}
