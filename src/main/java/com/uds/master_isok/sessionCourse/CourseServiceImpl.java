package com.uds.master_isok.sessionCourse;

import com.uds.master_isok.exceptions.ResourceNotFoundException;
import com.uds.master_isok.teacher.Teacher;
import com.uds.master_isok.uv.UE;
import com.uds.master_isok.teacher.TeacherRepository;
import com.uds.master_isok.uv.UeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.uds.master_isok.utils.AppConstants.*;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CoursesMapper coursesMapper;
    private final TeacherRepository teacherRepository;
    private final UeRepository ueRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CoursesMapper coursesMapper, TeacherRepository teacherRepository, UeRepository ueRepository) {
        this.courseRepository = courseRepository;
        this.coursesMapper = coursesMapper;
        this.teacherRepository = teacherRepository;
        this.ueRepository = ueRepository;
    }

    @Override
    public Page<CoursesResponse> fetchAllCourses(int page, int size, String search, String... sort) {
        return courseRepository.findAllPaginated(
                search,
                page,
                size,
                sort
        ).map(coursesMapper::toDto);
    }

    @Override
    public Long createCourse(CoursesRequest dto) {
        Teacher teacher = teacherRepository.findById(dto.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException(TEACHER, ID, dto.teacherId()));
        UE ue = ueRepository.findById(dto.ueId())
                .orElseThrow(() -> new ResourceNotFoundException(UE, ID, dto.ueId()));
        Courses course =  coursesMapper.toEntity(dto, ue, teacher);
        Courses savedCourse = courseRepository.save(course);
        return savedCourse.getId();
    }

    @Override
    public Long updateCourse(Long id, CoursesRequest dto) {
        Teacher teacher = teacherRepository.findById(dto.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException(TEACHER, ID, dto.teacherId()));
        UE ue = ueRepository.findById(dto.ueId())
                .orElseThrow(() -> new ResourceNotFoundException(UE, ID, dto.ueId()));
        Courses existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(COURSE, ID, id));

        Courses courses = coursesMapper.toEntity(dto, ue, teacher);
        courses.setId(existingCourse.getId());
        Courses savedCourse = courseRepository.save(courses);
        return savedCourse.getId();
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.findById(id).ifPresentOrElse(
                courseRepository::delete,
                () -> {
                    throw new ResourceNotFoundException(COURSE_SESSION, ID, id);
                }
        );
    }

    @Override
    public CoursesResponse getCourseById(Long id) {
        return courseRepository.findById(id).map(coursesMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(COURSE, ID, id));
    }
}
