package com.uds.master_isok.sessionCourse;

import com.uds.master_isok.utils.payload.PagedResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/courses")
public class CourseController {


    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    ResponseEntity<PagedResponse<CoursesResponse>> getAllCourses(
            @RequestParam(defaultValue = "0", required = false, name = "page")  int page,
            @RequestParam(defaultValue = "10", required = false, name = "size")  int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, name = "sort") String[] sort) {
        Page<CoursesResponse> result = courseService.fetchAllCourses(page, size, search, sort);
        return new ResponseEntity<>(PagedResponse.fromPage(result), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Long> createCourse(@Valid @RequestBody CoursesRequest dto){
        Long id = courseService.createCourse(dto);
        return ResponseEntity.created(URI.create("/api/v1/courses/" + id)).body(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<Long> updateCourse(@PathVariable Long id,  @Valid @RequestBody CoursesRequest dto){
        Long courseId = courseService.updateCourse(id, dto);
        return ResponseEntity.ok(courseId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursesResponse> getCourse(@PathVariable Long id) {
        CoursesResponse coursesResponse = courseService.getCourseById(id);
        return new ResponseEntity<>(coursesResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
