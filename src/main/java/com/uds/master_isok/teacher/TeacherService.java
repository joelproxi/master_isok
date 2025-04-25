package com.uds.master_isok.teacher;

import org.springframework.data.domain.Page;


public interface TeacherService {
    Page<TeacherResponse> getAllTeachers(int page, int size, String search, String... sort);

    TeacherResponse getTeacherById(Long id);

    Long createTeacher(TeacherRequest dto);

    Long updateTeacher(Long id, TeacherRequest dto);

    void deleteTeacher(Long id);
}
