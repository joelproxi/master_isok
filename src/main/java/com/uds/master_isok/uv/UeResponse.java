package com.uds.master_isok.uv;

import java.time.Instant;
import java.util.Set;

public record UeResponse(
    Long ueId,
    String title,
    String description,
    Integer credits,
    String semester,
    String code,
    Instant createdAt,
    Instant updatedAt,
    Set<TeacherSimpleResponse> teachers
) {}

record TeacherSimpleResponse(    
    Long teacherId,
    String firstName,
    String lastName,
    String email
) {}