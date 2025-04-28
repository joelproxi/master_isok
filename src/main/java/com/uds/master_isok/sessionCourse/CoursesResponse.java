package com.uds.master_isok.sessionCourse;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.hateoas.RepresentationModel;

public class CoursesResponse extends RepresentationModel<CoursesResponse> {
    private Long courseSessionId;
    private UeSimpleResponse ue;
    private TeacherSimpleResponse teacher;

    private LocalDate sessionDate;
    private LocalTime startTime;
    private LocalTime endTime;


    public CoursesResponse() {
    }

    public CoursesResponse(
        Long courseSessionId, UeSimpleResponse ue, TeacherSimpleResponse teacher,
                                  LocalDate sessionDate, LocalTime startTime, LocalTime endTime) {
        this.courseSessionId = courseSessionId;
        this.ue = ue;
        this.teacher = teacher;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getCourseSessionId() {
        return courseSessionId;
    }
    public void setCourseSessionId(Long courseSessionId) {
        this.courseSessionId = courseSessionId;
    }
    
    public LocalDate getSessionDate() {
        return sessionDate;
    }
    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
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

    public UeSimpleResponse getUe() {
        return ue;
    }

    public void setUe(UeSimpleResponse ue) {
        this.ue = ue;
    }

    public TeacherSimpleResponse getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherSimpleResponse teacher) {
        this.teacher = teacher;
    }
}


