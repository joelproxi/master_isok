package com.uds.master_isok.student;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public Page<StudentResponse> getAllStudents(int page, int size, String search, String... sort) {
        return studentRepository.findAllPaginated(
                StringUtils.hasText(search) ? search.trim() : null,
                page,
                size,
                sort
        ).map(studentMapper::toDto);

    }

    @Override
    public StudentResponse getStudent(Long id) {
        return null;
    }

    @Override
    public Long createStudent(StudentRequest dto) {
        return 0L;
    }

    @Override
    public Long updateStudent(Long id, StudentRequest dto) {
        return 0L;
    }

    @Override
    public void deleteStudent(Long id) {

    }


}
