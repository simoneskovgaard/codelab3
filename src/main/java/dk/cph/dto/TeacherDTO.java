package dk.cph.dto;

import dk.cph.model.Course;
import dk.cph.model.Student;
import dk.cph.model.Teacher;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
public class TeacherDTO {

    private String name;
    private String zoom;
    private Set<CourseDTO> courses = new HashSet<>();

    public TeacherDTO(Teacher teacher) {
        this.name = teacher.getName();
        this.zoom = teacher.getZoom();
        for (Course course : teacher.getCourses()) {
            courses.add(new CourseDTO(course));
        }
        this.courses = courses;
    }
}
