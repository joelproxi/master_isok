package com.uds.master_isok.sessionCourse;


import com.uds.master_isok.teacher.Teacher;
import com.uds.master_isok.utils.entities.Person;
import com.uds.master_isok.uv.UE;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "course_session")
@AttributeOverride(name = "id", column = @Column(name = "courseSession_id"))
public class SessionsCourse extends Person {

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



}
