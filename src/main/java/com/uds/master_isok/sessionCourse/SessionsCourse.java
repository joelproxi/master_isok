package com.uds.master_isok.sessionCourse;


import java.time.LocalTime;

import com.uds.master_isok.teacher.Teacher;
import com.uds.master_isok.utils.entities.BaseEntity;
import com.uds.master_isok.uv.UE;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_session")
@AttributeOverride(name = "id", column = @Column(name = "courseSession_id"))
public class SessionsCourse extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "kf_course_session"))
    private UE ue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "fk_session_teacher"))
    private Teacher teacher;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    public UE getUe() {
        return ue;
    }

    public void setUe(UE ue) {
        this.ue = ue;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public SessionsCourse(Long id, Integer version, UE ue, Teacher teacher, LocalTime startTime, LocalTime endTime) {
        super(id, version);
        this.ue = ue;
        this.teacher = teacher;
        this.startTime = startTime;
        this.endTime = endTime;
    }



}
