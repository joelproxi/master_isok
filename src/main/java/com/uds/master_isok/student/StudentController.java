package com.uds.master_isok.student;


import java.net.URI;

import com.uds.master_isok.file.FileController;
import com.uds.master_isok.file.FileStorageService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import com.uds.master_isok.utils.payload.PagedResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController extends FileController<Student> {

    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final FileStorageService fileStorageService;

    public StudentController(
            StudentService studentService,
            StudentRepository studentRepository,
            FileStorageService fileStorageService) {
        super(studentRepository, fileStorageService, "student");
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        this.fileStorageService = fileStorageService;
    }

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

    @PostMapping
    public ResponseEntity<Long> updateStudent(
            @Valid @RequestBody StudentRequest dto) {

        Long id = studentService.createStudent(dto);
        return ResponseEntity
                .created(URI.create("/api/v1/students/" + id))
                .body(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Long> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest dto) {
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<?> uploadPhoto(@PathVariable Long id,
                                         @RequestParam("file") MultipartFile file) {
        return super.uploadPhoto(id, file);
    }


}
