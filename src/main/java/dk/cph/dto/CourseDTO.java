package dk.cph.dto;

import dk.cph.model.Course;
import dk.cph.model.CourseName;
import dk.cph.model.Student;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CourseDTO {

    private CourseName name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<StudentDTO> students;
    private TeacherDTO teacher;

    public CourseDTO(Course course) {
        this.name = course.getCourseName();
        this.startDate = course.getStartDate();
        this.endDate = course.getEndDate();
        for (Student student : course.getStudents()) { //ideally this would be a convertToDTO method on the class course, and same goes for the rest of the DTOs
            students.add(new StudentDTO(student));
        }
        this.students = students;
        this.teacher = new TeacherDTO(course.getTeacher());
    }

}
