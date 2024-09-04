package dk.cph.dto;


import dk.cph.model.Course;
import dk.cph.model.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class StudentDTO {

    private String name;
    private String email;
    private Set<CourseDTO> courses = new HashSet<>();

    public StudentDTO(Student student) {
        this.name = student.getName();
        this.email = student.getEmail();
        for (Course course : student.getCourses()) {
            courses.add(new CourseDTO(course));
        }
        this.courses = courses;
    }

}
