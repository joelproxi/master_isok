package com.uds.master_isok.sessionCourse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    Page<CoursesResponse> fetchAllCourses(int page, int size, String search, String... sort);
    Long createCourse(CoursesRequest dto);
    Long updateCourse(Long id, CoursesRequest dto);
    void deleteCourse(Long id);
    CoursesResponse getCourseById(Long id);
}
