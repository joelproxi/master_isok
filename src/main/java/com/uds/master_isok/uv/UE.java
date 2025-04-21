package com.uds.master_isok.uv;

import com.uds.master_isok.teacher.Teacher;
import com.uds.master_isok.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "ue",
        indexes = {
            @Index(name = "idx_ue_teacher_ue", columnList = "ue_id"),
            @Index(name = "idx_ue_title", columnList = "title")
        }
)
@AttributeOverride(name = "id", column = @Column(name = "ue_id"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UE extends BaseEntity {
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer credits;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    private enum Semester {
        SEMESTER_1, SEMESTER_2, SEMESTER_3, SEMESTER_4
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ue_teachers",
            joinColumns = @JoinColumn(name = "ue_id", referencedColumnName = "ue_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    )
    @Builder.Default
    private Set<Teacher> teachers = new HashSet<>();



}
