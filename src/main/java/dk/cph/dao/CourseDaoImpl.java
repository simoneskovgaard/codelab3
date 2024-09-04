package dk.cph.dao;

import dk.cph.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseDaoImpl implements GenericDAO<Course, Integer> {

    private static CourseDaoImpl instance;
    private static EntityManagerFactory emf;

    public static CourseDaoImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CourseDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Course> findAll() {
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("from Course", Course.class).getResultList();
        }
    }

    @Override
    public void persistEntity(Course entity) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }

    @Override
    public void removeEntity(Integer id) {
        try(EntityManager em = emf.createEntityManager()) {
            Course entity = em.find(Course.class, id);
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }
    }

    @Override
    public Course findEntity(Integer id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Course.class, id);
        }
    }

    @Override
    public Course updateEntity(Course entity, Integer id) {
        try(EntityManager em = emf.createEntityManager()) {
            Course entity2 = em.find(Course.class, id);
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity2;
        }
    }
}
