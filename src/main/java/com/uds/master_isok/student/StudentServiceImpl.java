package com.uds.master_isok.student;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public Page<StudentResponse> getAllTeachers(int page, int size, String search, String... sort) {
        return null;
    }

    @Override
    public StudentResponse getTeacherById(Long id) {
        return null;
    }

    @Override
    public Long createTeacher(StudentRequest dto) {
        return 0L;
    }

    @Override
    public Long updateTeacher(Long id, StudentRequest dto) {
        return 0L;
    }

    @Override
    public void deleteTeacher(Long id) {

    }
}
