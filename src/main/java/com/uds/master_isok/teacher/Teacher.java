package com.uds.master_isok.teacher;


import java.util.List;

import com.uds.master_isok.utils.entities.AuditMetadata;
import com.uds.master_isok.utils.entities.Person;
import com.uds.master_isok.uv.UE;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "teacher",
        indexes = @Index(
                name = "idx_teacher_email",
                columnList = "email"
        ), uniqueConstraints = @UniqueConstraint(
                name = "uc_teacher_email", columnNames = "email"))
@SequenceGenerator(name = "teacher_seq", sequenceName = "teacher_id_seq", allocationSize = 3)
@AttributeOverride(name = "id", column = @Column(name = "teacher_id"))
public class Teacher extends Person {
    
    @Column(nullable = true, columnDefinition = "TEXT")
    private String biography;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String publications;

    @ManyToMany(mappedBy = "teachers")
    private List<UE> ues;

    public String getBiography() {
        return biography;
}

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPublications() {
        return publications;
    }

    public void setPublications(String publications) {
        this.publications = publications;
    }

    public List<UE> getUes() {
        return ues;
    }

    public void setUes(List<UE> ues) {
        this.ues = ues;
    }
    
    public Teacher(String biography, String publications, List<UE> ues, AuditMetadata auditMetadata, String email, String firstName, String lastName, String photoUrl) {
        super(auditMetadata, email, firstName, lastName, photoUrl);
        this.biography = biography;
        this.publications = publications;
        this.ues = ues;
    }

}

