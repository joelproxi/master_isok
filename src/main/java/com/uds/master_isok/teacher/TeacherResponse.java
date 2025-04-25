package com.uds.master_isok.teacher;

import java.time.Instant;


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
