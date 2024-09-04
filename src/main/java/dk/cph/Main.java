package dk.cph;


import dk.cph.config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory(false);

        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
//            em.persist();
            em.getTransaction().commit();
        }

    }
}