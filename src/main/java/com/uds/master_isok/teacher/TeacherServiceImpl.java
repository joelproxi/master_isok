package com.uds.master_isok.teacher;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Override
    public Long createTeacher(TeacherRequest teacherRequest) {
        return 0L;
    }

    @Override
    public TeacherResponse getTeacher(Long id) {
        return null;
    }

    @Override
    public Long updateTeacher(Long id, TeacherRequest teacherRequest) {
        return 0L;
    }

    @Override
    public void deleteTeacher(Long id) {

    }

    @Override
    public List<TeacherResponse> getAllTeachers() {
        return List.of();
    }
}
