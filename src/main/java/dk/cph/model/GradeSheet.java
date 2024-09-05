package dk.cph.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "gradesheet")
public class GradeSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private CourseName courseName;

    @Enumerated(EnumType.STRING)
    private GradeScale gradeScale;

    @OneToOne
    @MapsId
    private Student student;

    public void addStudent(Student student) {
        if (student != null) {
            this.student = student;
        }
    }

    public GradeSheet(CourseName courseName, GradeScale gradeScale) {
        this.courseName = courseName;
        this.gradeScale = gradeScale;
    }
}
