package dk.cph.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseName courseName;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    private Teacher teacher;


    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    public void addStudent(Student student) {
        if (student != null) {
            this.students.add(student);
            student.getCourses().add(this);

        }
    }
        public void addTeacher (Teacher teacher){
            if (this.teacher == null) {
                teacher.addCourse(this);
                this.teacher = teacher;
            }

        }
    }


