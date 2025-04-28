package com.uds.master_isok.sessionCourse;

import com.uds.master_isok.teacher.Teacher;
import com.uds.master_isok.uv.UE;
import org.springframework.stereotype.Component;

@Component
public class CoursesMapper {
    
    CoursesResponse toDto(Courses Courses) {
        UeSimpleResponse ueSimpleResponse = new UeSimpleResponse(
                Courses.getUe().getId(),
                Courses.getUe().getTitle(),
                Courses.getUe().getCredits(),
                Courses.getUe().getSemester().name(),
                Courses.getUe().getcode()
        );
        
        TeacherSimpleResponse teacherSimpleResponse = new TeacherSimpleResponse(
                Courses.getTeacher().getId(),
                Courses.getTeacher().getFirstName(),
                Courses.getTeacher().getLastName(),
                Courses.getTeacher().getEmail()
                
        );

        return getCoursesResponse(Courses, ueSimpleResponse, teacherSimpleResponse);
    }

    private static CoursesResponse getCoursesResponse(
            Courses Courses, UeSimpleResponse ueSimpleResponse, TeacherSimpleResponse teacherSimpleResponse) {
        CoursesResponse CoursesResponse = new CoursesResponse();
        CoursesResponse.setCourseSessionId(Courses.getId());
        CoursesResponse.setSessionDate(Courses.getSessionDate());
        CoursesResponse.setEndTime(Courses.getEndTime());
        CoursesResponse.setStartTime(Courses.getStartTime());
        CoursesResponse.setUe(ueSimpleResponse);
        CoursesResponse.setTeacher(teacherSimpleResponse);
        return CoursesResponse;
    }

    public Courses toEntity(CoursesRequest dto, UE ue, Teacher teacher) {
        Courses course = new Courses();
        course.setUe(ue);
        course.setTeacher(teacher);
        course.setSessionDate(dto.sessionDate());
        course.setEndTime(dto.endTime());
        course.setStartTime(dto.startTime());
        return course;
    }
}
