package com.uds.master_isok.sessionCourse;

import java.time.LocalDate;
import java.time.LocalTime;

public record CoursesRequest(
    Long ueId,
     Long teacherId,
     LocalDate sessionDate,
     LocalTime startTime,
     LocalTime endTime) {
    

}
