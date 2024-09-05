package dk.cph;


import dk.cph.config.HibernateConfig;

import dk.cph.dao.TeacherDaoImpl;
import dk.cph.model.*;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory(false);

        TeacherDaoImpl dao = TeacherDaoImpl.getInstance(emf);

//        Teacher t1 = new Teacher("Bob", "mail", "zoomlink");
//        Teacher t1upd = dao.findEntity(1);
//        t1upd.setName("Henrik");
//        dao.updateEntity(t1upd, 1);
        Student s1 = new Student("Gjon","email", LocalDateTime.now(),LocalDateTime.now());
        GradeSheet gs1 = new GradeSheet(CourseName.ENGLISH, GradeScale.B);
        gs1.addStudent(s1);

        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
//            em.persist(t1);
            em.persist(s1);
            em.persist(gs1);
            em.getTransaction().commit();
        }
    }
}