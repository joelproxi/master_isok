package com.uds.master_isok.student;


import com.uds.master_isok.utils.payload.PagedResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<PagedResponse<StudentResponse>> getAllTeachers(
            @RequestParam(defaultValue = "0", required = false, name = "page") @Min(0) int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") @Min(1) @Max(100) int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, name = "sort") String[] sort) {

        Page<StudentResponse> result = studentService.getAllStudents(page, size, search, sort);
        return ResponseEntity.ok(PagedResponse.fromPage(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

}
