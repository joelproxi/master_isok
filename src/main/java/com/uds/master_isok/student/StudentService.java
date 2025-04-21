package com.uds.master_isok.student;

import org.springframework.data.domain.Page;

public interface StudentService {
    Page<StudentResponse> getAllTeachers(int page, int size, String search, String... sort);

    StudentResponse getTeacherById(Long id);

    Long createTeacher(StudentRequest dto);

    Long updateTeacher(Long id, StudentRequest dto);

    void deleteTeacher(Long id);
}
