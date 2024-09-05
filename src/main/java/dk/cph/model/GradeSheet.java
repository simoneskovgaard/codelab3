package dk.cph.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "grade_sheet")
public class GradeSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "course_name", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private CourseName courseName;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private GradeScale grade;

    @OneToOne
    @MapsId
    private Student student;

    public GradeSheet(CourseName courseName, GradeScale grade) {
        this.courseName = courseName;
        this.grade = grade;
    }

    public void addStudent(Student student) {
        if (student != null) {
            this.student = student;
        }
    }
}
