package dk.cph;


import dk.cph.config.HibernateConfig;

import dk.cph.dao.TeacherDaoImpl;
import dk.cph.model.Teacher;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory(false);

        TeacherDaoImpl dao = TeacherDaoImpl.getInstance(emf);

        Teacher t1 = new Teacher("Bob", "mail", "zoomlink");
        Teacher t1upd = dao.findEntity(1);
        t1upd.setName("Henrik");
        dao.updateEntity(t1upd, 1);

        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
//            em.persist(t1);
            em.getTransaction().commit();
        }
    }
}