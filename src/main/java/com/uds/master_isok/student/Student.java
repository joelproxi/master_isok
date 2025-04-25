package com.uds.master_isok.student;

import java.time.LocalDate;

import com.uds.master_isok.utils.entities.AuditMetadata;
import com.uds.master_isok.utils.entities.Person;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


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
public class Student extends Person {

    private LocalDate dateOfBirth;
    private String idNumber ;



    public Student(Long id, Integer version, String firstName, String lastName, String email, String photoUrl,
        AuditMetadata auditMetadata, LocalDate dateOfBirth, String idNumber) {
    super(id, version, firstName, lastName, email, photoUrl, auditMetadata);
    this.dateOfBirth = dateOfBirth;
    this.idNumber = idNumber;
}


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }


}
