package dk.cph;


import dk.cph.config.HibernateConfig;

import dk.cph.dao.TeacherDaoImpl;
import dk.cph.model.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EnumType;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory(false);

        TeacherDaoImpl dao = TeacherDaoImpl.getInstance(emf);

        Teacher teacherOne = new Teacher("Bob", "email@emailink.com", "zoomlink");
        Teacher teacherTwo = new Teacher("Bill", "mail@mail.com", "zoomlink two");

        Student studentOne = new Student("Garfield Garfielding","lasagne@gmail.com");
        Student studentTwo = new Student("Hans Henrik","hans@hotmail.com");
        Course math = new Course(CourseName.MATH, LocalDate.of(2024,8,19), LocalDate.of(2025,1,14));
        Course english = new Course(CourseName.ENGLISH, LocalDate.of(2024,8,19), LocalDate.of(2025,1,14));
        math.addStudent(studentOne);
        english.addStudent(studentTwo);
//        math.addTeacher(teacherOne);
//        english.addTeacher(teacherTwo);
        GradeSheet gradeOne = new GradeSheet(CourseName.MATH, GradeScale.B);
        GradeSheet gradeTwo = new GradeSheet(CourseName.ENGLISH, GradeScale.A);
        gradeOne.addStudent(studentTwo);
        gradeTwo.addStudent(studentOne);

//        Teacher t1upd = dao.findEntity(1);
//        t1upd.setName("Henrik");
//        dao.updateEntity(t1upd, 1);

        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(teacherOne);
            em.persist(teacherTwo);

            em.persist(studentOne);
            em.persist(studentTwo);

            em.persist(math);
            em.persist(english);

            em.persist(gradeOne);
            em.persist(gradeTwo);

            em.getTransaction().commit();
        }
    }
}