package com.uds.master_isok.teacher;

import java.util.List;

public interface TeacherService {
    Long createTeacher(TeacherRequest teacherRequest);

    TeacherResponse getTeacher(Long id);

    Long updateTeacher(Long id, TeacherRequest teacherRequest);

    void deleteTeacher(Long id);

    List<TeacherResponse> getAllTeachers();
}
