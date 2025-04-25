package com.uds.master_isok.uv;

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

import com.uds.master_isok.utils.payload.PagedResponse;

import jakarta.validation.Valid;

@RequestMapping("/ues")
@RestController
public class UeController {
    private final UeService ueService;

    public UeController(UeService ueService) {
        this.ueService = ueService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<UeResponse>> getAllUes(
            @RequestParam(defaultValue = "0", required = false, name = "page")  int page,
            @RequestParam(defaultValue = "10", required = false, name = "size")  int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, name = "sort") String[] sort) {
        Page<UeResponse> result = ueService.getAllUes(page, size, search, sort);
        return ResponseEntity.ok(PagedResponse.fromPage(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UeResponse> getUeById(@PathVariable Long id) {
        return ResponseEntity.ok(ueService.getUeById(id));
    }

    @PostMapping
    public ResponseEntity<Long> createUe(
            @Valid @RequestBody UeRequest dto) {
        Long id = ueService.createUe(dto);
        return ResponseEntity
                .created(URI.create("/api/v1/ues/" + id))
                .body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUe(
            @PathVariable Long id,
            @Valid @RequestBody UeRequest dto) {
        Long updatedId = ueService.updateUe(id, dto);
        return ResponseEntity.ok(updatedId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUe(@PathVariable Long id) {
        ueService.deleteUe(id);
    }
}
