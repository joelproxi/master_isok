package com.uds.master_isok.teacher;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uds.master_isok.file.FileController;
import com.uds.master_isok.file.FileStorageService;
import com.uds.master_isok.utils.payload.PagedResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/teachers")
public class TeacherController extends FileController<Teacher> {

    private final TeacherService teacherService;

    public TeacherController(TeacherRepository repository,
                             FileStorageService fileStorageService,
                             TeacherService teacherService) {
        super(repository, fileStorageService, "teacher");
        this.teacherService = teacherService;
    }

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

    @PostMapping("/{id}/photo")
    public ResponseEntity<?> uploadPhoto(@PathVariable Long id,
                                         @RequestParam("file") MultipartFile file) {
        return super.uploadPhoto(id, file);
    }


}