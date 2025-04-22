package com.uds.master_isok.file;

import com.uds.master_isok.utils.entities.Person;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public abstract class FileController<T extends Person> {
    private final JpaRepository<T, Long> repository;
    private final FileStorageService fileStorageService;
    private final String entityType;

    protected FileController(JpaRepository<T, Long> repository,
                             FileStorageService fileStorageService,
                             String entityType) {
        this.repository = repository;
        this.fileStorageService = fileStorageService;
        this.entityType = entityType.toLowerCase();
    }

    public ResponseEntity<?> uploadPhoto(Long id, MultipartFile file) {
        try {
            T entity = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(entityType + " not found"));

            String fileKey = "%ss/%d/photo/%s".formatted(entityType, id, file.getOriginalFilename());
            String fileUrl = fileStorageService.uploadFile(file, fileKey);

            entity.setPhotoUrl(fileUrl);
            repository.save(entity);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
