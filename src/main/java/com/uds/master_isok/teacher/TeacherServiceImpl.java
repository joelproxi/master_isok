package com.uds.master_isok.teacher;

import com.uds.master_isok.exceptions.DuplicateResourceException;
import com.uds.master_isok.exceptions.ResourceNotFoundException;
import com.uds.master_isok.utils.validation.PaginationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static com.uds.master_isok.utils.AppConstants.ID;
import static com.uds.master_isok.utils.AppConstants.TEACHER;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

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

        log.info("Created teacher with ID: {}", savedTeacher.getId());
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

        log.info("Updated teacher with ID: {}", id);
        return teacher.getId();
    }

    @Override
    @Transactional
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TEACHER, ID, id));

        teacherRepository.delete(teacher);
        log.info("Deleted teacher with ID: {}", id);
    }

    private void validateEmailUniqueness(String email) {
        if (teacherRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateResourceException("Email " + email + " is already taken");
        }
    }
}