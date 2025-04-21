package com.uds.master_isok.student;

import com.uds.master_isok.utils.Person;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Table(name = "student",
        indexes = {
            @Index(name = "idx_student_email", columnList = "email"),
                @Index(name = "idx_student_first_name", columnList = "firstName"),
                @Index(name = "idx_student_last_name", columnList = "lastName"),
        },
        uniqueConstraints = @UniqueConstraint(
                name = "uc_student_email",
                columnNames = "email"))
@SequenceGenerator(name = "student_seq", sequenceName = "student_id_seq", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "student_id"))
@Entity
@SuperBuilder
@NoArgsConstructor()
public class Student extends Person {

}
