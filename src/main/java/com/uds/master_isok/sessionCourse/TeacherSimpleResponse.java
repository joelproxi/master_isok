package com.uds.master_isok.sessionCourse;

public record TeacherSimpleResponse(
    Long teacherId,
    String firstName,
    String lastName,
    String email
) {

}
