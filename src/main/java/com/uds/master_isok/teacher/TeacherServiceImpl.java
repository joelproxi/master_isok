package com.uds.master_isok.teacher;

import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.uds.master_isok.exceptions.DuplicateResourceException;
import com.uds.master_isok.exceptions.ResourceNotFoundException;
import static com.uds.master_isok.utils.AppConstants.ID;
import static com.uds.master_isok.utils.AppConstants.TEACHER;
import com.uds.master_isok.utils.validation.PaginationValidator;


@Service
public class TeacherServiceImpl implements TeacherService {

    private static final Logger log = Logger.getLogger(TeacherServiceImpl.class.getName());
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final PaginationValidator paginationValidator;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper, PaginationValidator paginationValidator) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.paginationValidator = paginationValidator;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TeacherResponse> getAllTeachers(int page, int size, String search, String... sort) {
        //paginationValidator.validateParameters(page, size, sort);
        return teacherRepository.findAllPaginated(
                StringUtils.hasText(search) ? search.trim() : null,
                page,
                size,
                sort
        ).map(teacherMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherResponse getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .map(teacherMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(TEACHER, ID, id));
    }

    @Override
    @Transactional
    public Long createTeacher(TeacherRequest dto) {
        validateEmailUniqueness(dto.email());

        Teacher teacher = teacherMapper.toEntity(dto);
        Teacher savedTeacher = teacherRepository.save(teacher);

        log.info(String.format("Created teacher with ID: %d", savedTeacher.getId()));
        return savedTeacher.getId();
    }

    @Override
    @Transactional
    public Long updateTeacher(Long id, TeacherRequest dto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TEACHER, ID, id));

        if (StringUtils.hasText(dto.email()) && !dto.email().equalsIgnoreCase(teacher.getEmail())) {
            validateEmailUniqueness(dto.email());
        }

        teacherMapper.updateFromDto(dto, teacher);

        log.info(String.format("Updated teacher with ID: %d", teacher.getId()));
        return teacher.getId();
    }

    @Override
    @Transactional
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TEACHER, ID, id));

        teacherRepository.delete(teacher);
        log.info(String.format("Deleted teacher with ID: %d", id));

    }

    private void validateEmailUniqueness(String email) {
        if (teacherRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateResourceException("Email " + email + " is already taken");
        }
    }
}