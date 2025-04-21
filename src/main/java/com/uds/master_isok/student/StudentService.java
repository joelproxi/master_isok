package com.uds.master_isok.student;

import org.springframework.data.domain.Page;

public interface StudentService {
    Page<StudentResponse> getAllStudents(int page, int size, String search, String... sort);

    StudentResponse getStudent(Long id);

    Long createStudent(StudentRequest dto);

    Long updateStudent(Long id, StudentRequest dto);

    void deleteStudent(Long id);
}
