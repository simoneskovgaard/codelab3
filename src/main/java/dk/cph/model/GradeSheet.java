package dk.cph.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "gradesheet")
public class GradeSheet {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id; //Having it's own id isn't needed in a one-to-one relationship where it contains a foreign key and uses it as its primary key

    @Enumerated(EnumType.STRING)
    private CourseName courseName;

    @Enumerated(EnumType.STRING)
    private GradeScale gradeScale;

    @Id
    @OneToOne
//    @MapsId //Having this doesn't make a difference, might be due to some default settings
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
