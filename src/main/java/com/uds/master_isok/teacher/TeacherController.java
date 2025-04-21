package com.uds.master_isok.teacher;

import com.uds.master_isok.utils.payload.PagedResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
@Validated
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @GetMapping
    public ResponseEntity<PagedResponse<TeacherResponse>> getAllTeachers(
            @RequestParam(defaultValue = "0", required = false, name = "page") @Min(0) int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") @Min(1) @Max(100) int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, name = "sort") String[] sort) {

        Page<TeacherResponse> result = teacherService.getAllTeachers(page, size, search, sort);
        return ResponseEntity.ok(PagedResponse.fromPage(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PostMapping
    public ResponseEntity<Long> createTeacher(
            @Valid @RequestBody TeacherRequest dto) {

        Long id = teacherService.createTeacher(dto);
        return ResponseEntity
                .created(URI.create("/api/v1/teachers/" + id))
                .body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody TeacherRequest dto) {

        return ResponseEntity.ok(teacherService.updateTeacher(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }


}