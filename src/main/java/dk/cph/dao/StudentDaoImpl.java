package dk.cph.dao;

import dk.cph.model.Student;
import jakarta.persistence.EntityManagerFactory;
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
        return List.of();
    }

    @Override
    public void persistEntity(Student entity) {

    }

    @Override
    public void removeEntity(Integer id) {

    }

    @Override
    public Student findEntity(Integer id) {
        return null;
    }

    @Override
    public Student updateEntity(Student entity, Integer id) {
        return null;
    }
}
