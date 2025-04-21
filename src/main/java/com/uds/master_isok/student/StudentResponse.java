package com.uds.master_isok.student;

import java.time.Instant;

public record StudentResponse(
        Long studentId,
        String firstName,
        String lastName,
        String email,
        String dateOfBirth,
        Instant updatedAt,
        Instant createdAt
) {
}
