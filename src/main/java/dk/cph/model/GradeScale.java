package dk.cph.model;

import lombok.Getter;

@Getter
public enum GradeScale {
    A(12), B(10), C(7), D(4), E(2), F(0);

    private int value;

    GradeScale(int value) {this.value = value;}
}
