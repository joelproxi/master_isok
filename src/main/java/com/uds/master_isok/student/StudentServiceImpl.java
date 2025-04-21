package com.uds.master_isok.student;

import com.uds.master_isok.exceptions.DuplicateResourceException;
import com.uds.master_isok.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
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
        return studentRepository.findById(id).map(studentMapper::toDto).orElseThrow(
                () -> new ResourceNotFoundException("Student not found with id: " + id)
        );
    }

    @Override
    public Long createStudent(StudentRequest dto) {
        validateEmailUniqueness(dto.email());

        Student student = studentMapper.toEntity(dto);
        Student savedStudent = studentRepository.save(student);

        log.info("Created student with id: " + savedStudent.getId());
        return savedStudent.getId();
    }

    @Override
    public Long updateStudent(Long id, StudentRequest dto) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student not found with id: " + id)
        );
        if (StringUtils.hasText(dto.email()) && !dto.email().equalsIgnoreCase(existingStudent.getEmail())) {
            validateEmailUniqueness(dto.email());
        }

        studentMapper.updateFromDto(dto, existingStudent);
        log.info("Updated student with ID: {}", id);
        return existingStudent.getId();
    }

    @Override
    public void deleteStudent(Long id) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student not found with id: " + id)
        );
        studentRepository.delete(existingStudent);
        log.info("Deleted student with ID: {}", id);
    }

    private void validateEmailUniqueness(String email) {
        if (studentRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateResourceException("Email " + email + " is already taken");
        }
    }


}
