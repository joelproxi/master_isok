package com.uds.master_isok.teacher;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;

import java.time.Instant;

@Builder
public record TeacherResponse(
        Long teacherId,
        String biography,
        String publications ,
        String photoUrl ,
        String firstName ,
        String lastName ,
        String email,
        Instant updatedAt,
        Instant createdAt
){
}
