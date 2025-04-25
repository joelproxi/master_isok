package com.uds.master_isok.uv;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.uds.master_isok.uv.UE.Semester;

@Component
public class UeMapper {

    UeResponse toDto(UE ue){
        return new UeResponse(
                ue.getId(),
                ue.getTitle(),
                ue.getDescription(),
                ue.getCredits(),
                ue.getSemester().name(),
                ue.getcode(),
                ue.getAuditMetadata().getCreatedAt(),
                ue.getAuditMetadata().getUpdatedAt(),
                ue.getTeachers().stream()
                        .map(teacher -> new TeacherSimpleResponse(
                                teacher.getId(),
                                teacher.getFirstName(),
                                teacher.getLastName(),
                                teacher.getEmail()
                        ))
                        .collect(Collectors.toSet())
        );
    };


    UE toEntity(UeRequest entity){
        UE ue = new UE();
        ue.setTitle(entity.title());
        ue.setDescription(entity.description());
        ue.setCredits(entity.credits());
        ue.setCode(entity.code());
        ue.setSemester(Semester.valueOf(entity.semester().name().toUpperCase()));
        return ue;
    }
}
