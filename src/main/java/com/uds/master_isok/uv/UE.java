package com.uds.master_isok.uv;

import java.util.HashSet;
import java.util.Set;

import com.uds.master_isok.teacher.Teacher;
import com.uds.master_isok.utils.entities.BaseEntity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "ue",
    indexes = {
        @Index(name = "idx_ue_teacher_ue", columnList = "ue_id"),
        @Index(name = "idx_ue_title", columnList = "title")
    }
)
@AttributeOverride(name = "id", column = @Column(name = "ue_id"))
public class UE extends BaseEntity {
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer credits;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    public UE(Integer credits, String description, Semester semester, String title) {
        this.credits = credits;
        this.description = description;
        this.semester = semester;
        this.title = title;
    }

    public UE() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public enum Semester {
        SEMESTER_1, SEMESTER_2, SEMESTER_3, SEMESTER_4
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ue_teachers",
            joinColumns = @JoinColumn(name = "ue_id", referencedColumnName = "ue_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    )

    private Set<Teacher> teachers = new HashSet<>();



}
