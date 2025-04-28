package com.uds.master_isok.sessionCourse;

import com.uds.master_isok.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.uds.master_isok.utils.AppConstants.COURSE_SESSION;
import static com.uds.master_isok.utils.AppConstants.ID;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<CoursesResponse> fetchAllCourses(int page, int size, String search, String... sort) {
        return null;
    }

    @Override
    public Long createCourse(CoursesRequest dto) {
        return 0L;
    }

    @Override
    public Long updateCourse(Long id, CoursesRequest dto) {
        return 0L;
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
        return null;
    }
}
