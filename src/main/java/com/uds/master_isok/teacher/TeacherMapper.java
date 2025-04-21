package com.uds.master_isok.teacher;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(source = "id", target = "teacher_id")
    @Mapping(source = "auditMetadata.createdAt", target = "createdAt")
    @Mapping(source = "auditMetadata.updatedAt", target = "updatedAt")
    TeacherResponse toResponse(Teacher teacher);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "auditMetadata", ignore = true)
    Teacher toEntity(TeacherRequest request);
}
