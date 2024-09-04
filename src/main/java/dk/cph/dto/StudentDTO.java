package dk.cph.dto;

import dk.cph.model.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class StudentDTO {

    private String name;
    private String email;
    private Set<Course> courses = new HashSet<>();

    public StudentDTO(String name, String email, Set<Course> courses) {
        this.name = name;
        this.email = email;
        this.courses = courses;
    }
}
