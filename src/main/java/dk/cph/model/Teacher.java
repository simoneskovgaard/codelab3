package dk.cph.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "teachers")
@NoArgsConstructor
@Getter
@Setter
@ToString
@NamedQuery(name = "Teacher.deleteAll", query = "DELETE FROM Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private CourseName name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "zoom", unique = true)
    private String zoom;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;

    void addCourse(Course course) {
        courses.add(course);
    }

}
