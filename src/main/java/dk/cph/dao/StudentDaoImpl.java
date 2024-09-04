package dk.cph.dao;

import dk.cph.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentDaoImpl implements GenericDAO<Student, Integer> {

    private static StudentDaoImpl instance;
    private static EntityManagerFactory emf;

    public static StudentDaoImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Student> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Student> query = em.createQuery("select q from Student q", Student.class);
            return query.getResultList();
        }
    }

    @Override
    public void persistEntity(Student entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }

    @Override
    public void removeEntity(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            Student entity = em.find(Student.class, id);
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }
    }

    @Override
    public Student findEntity(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Student.class, id);
        }
    }

    @Override
    public Student updateEntity(Student entity, Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Student updatedEntity = em.merge(entity);
            em.getTransaction().commit();
            return updatedEntity;
        }
    }
}
