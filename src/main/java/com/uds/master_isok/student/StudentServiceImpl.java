package com.uds.master_isok.student;

import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.uds.master_isok.exceptions.DuplicateResourceException;
import com.uds.master_isok.exceptions.ResourceNotFoundException;
import static com.uds.master_isok.utils.AppConstants.ID;
import static com.uds.master_isok.utils.AppConstants.STUDENT;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private static final Logger log = Logger.getLogger(StudentServiceImpl.class.getName());

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
                () -> new ResourceNotFoundException(STUDENT, ID, id)
        );
    }

    @Override
    public Long createStudent(StudentRequest dto) {
        validateEmailUniqueness(dto.email());

        Student student = studentMapper.toEntity(dto);
        Student savedStudent = studentRepository.save(student);

        log.info(String.format("Created student with ID: %d", savedStudent.getId()));
        return savedStudent.getId();
    }

    @Override
    public Long updateStudent(Long id, StudentRequest dto) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(
                () -> new  ResourceNotFoundException(STUDENT, ID, id)
        );
        if (StringUtils.hasText(dto.email()) && !dto.email().equalsIgnoreCase(existingStudent.getEmail())) {
            validateEmailUniqueness(dto.email());
        }

        studentMapper.updateFromDto(dto, existingStudent);
        log.info(String.format("Updated student with ID: %d", existingStudent.getId()));
        return existingStudent.getId();
    }

    @Override
    public void deleteStudent(Long id) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(STUDENT, ID, id)
        );
        studentRepository.delete(existingStudent);
        log.info(String.format("Deleted student with ID: %d", existingStudent.getId()));
    }

    private void validateEmailUniqueness(String email) {
        if (studentRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateResourceException("Email " + email + " is already taken");
        }
        log.info(String.format("Validated email uniqueness for: %s", email));
    }


}
