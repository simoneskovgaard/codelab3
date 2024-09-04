package dk.cph.dao;

import dk.cph.config.HibernateConfig;
import dk.cph.dto.StudentDTO;
import dk.cph.model.Student;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoImplTest {

    private static EntityManagerFactory emf;
    private static StudentDaoImpl studentDao;

    Student studentOne = new Student("Garfield Garfielding","lasagne@gmail.com");
    Student studentTwo = new Student("Hans Henrik","hans@hotmail.com");
    Student studentThree = new Student("Anders Anden","andeby@gmail.com");
    Student studentFour = new Student("Mickey Mussing","mouse@hotmail.com");
    Student studentFive = new Student("Vilhelmina Vilhelmsen","mina@gmail.com");

    @BeforeAll
    static void setUpAll() {
        emf = HibernateConfig.getEntityManagerFactory(true);
        studentDao = StudentDaoImpl.getInstance(emf);
    }

    @BeforeEach
    void setUp() {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Student").executeUpdate();
            em.persist(studentOne);
            em.persist(studentTwo);
            em.persist(studentThree);
            em.persist(studentFour);
            em.persist(studentFive);
            em.getTransaction().commit();
        }
    }

    @Test
    void findAll() {
        List<Student> students = studentDao.findAll();
        int size = students.size();
        int addedStudents = 5;
        assertEquals(addedStudents, size);
    }

    @Test
    void persistEntity() {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // How many students do we have before adding a new one
            long initialEntryCount = em.createQuery("SELECT COUNT(p) FROM Student p", Long.class).getSingleResult();

            // given
            Student studentTest = new Student("Emma Gad-ikke", "gad@hotmail.com");
            // when
            studentDao.persistEntity(studentTest);

            // How many students do we have after adding a new one
            long newEntryCount = em.createQuery("SELECT COUNT(p) FROM Student p", Long.class).getSingleResult();

            Student studentRetrieved = em.createQuery("SELECT p FROM Student p where p.email = :email", Student.class)
                    .setParameter("email", studentTest.getEmail())
                    .getSingleResult();

            // then
            em.getTransaction().commit();

            // Have we added a student?
            assertEquals(initialEntryCount + 1, newEntryCount);

            // Does a student in DB have the new email?
            assertNotNull(studentRetrieved);
            assertEquals("gad@hotmail.com", studentRetrieved.getEmail());

            // Does the new student have the correct info?
            assertEquals(studentTest.getName(), studentRetrieved.getName());
            assertEquals(studentTest.getEmail(), studentRetrieved.getEmail());
        }
    }

    @Test
    void removeEntity() {
        studentDao.removeEntity(studentTwo.getId());
        List<Student> students = studentDao.findAll();
        int size = students.size();
        int addedStudents = 5;
        assertEquals(addedStudents - 1, size);

        List<Student> students2 = studentDao.findAll();     // a small print to check student 2 is indeed gone
        for (Student student : students2) {
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
        }
    }

    @Test
    void findEntity() {
        Student foundStudent = studentDao.findEntity(studentOne.getId());

        assertNotNull(foundStudent);
        assertEquals(studentOne.getName(), foundStudent.getName());
        assertEquals(studentOne.getEmail(), foundStudent.getEmail());
    }

    @Test
    void updateEntity() {
        Student student = studentDao.findEntity(studentThree.getId());

        assertNotNull(student);

        String newName = "Anders And";
        student.setName(newName);

        Student updatedStudent = studentDao.updateEntity(student, studentThree.getId());

        assertNotNull(updatedStudent);
        assertEquals(newName, updatedStudent.getName());
    }
}