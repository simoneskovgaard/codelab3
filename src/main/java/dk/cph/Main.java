package dk.cph;


import dk.cph.config.HibernateConfig;
import dk.cph.model.Teacher;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory(false);

        Teacher t1 = new Teacher("Bob", "mail", "zoomlink");

        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(t1);
            em.getTransaction().commit();
        }
    }
}