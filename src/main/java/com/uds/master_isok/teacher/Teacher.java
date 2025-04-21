package com.uds.master_isok.teacher;


import com.uds.master_isok.utils.entities.Person;
import com.uds.master_isok.uv.UE;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "teacher",
        indexes = @Index(
                name = "idx_teacher_email",
                columnList = "email"
        ), uniqueConstraints = @UniqueConstraint(
                name = "uc_teacher_email", columnNames = "email"))
@SequenceGenerator(name = "teacher_seq", sequenceName = "teacher_id_seq", allocationSize = 3)
@AttributeOverride(name = "id", column = @Column(name = "teacher_id"))
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher extends Person {
    @Column(nullable = true, columnDefinition = "TEXT")
    private String biography;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String publications;

    @ManyToMany(mappedBy = "teachers")
    private List<UE> ues;

}
