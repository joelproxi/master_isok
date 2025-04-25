package com.uds.master_isok.teacher;

import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(source = "id", target = "teacherId")
    @Mapping(source = "auditMetadata.createdAt", target = "createdAt")
    @Mapping(source = "auditMetadata.updatedAt", target = "updatedAt")
    TeacherResponse toDto(Teacher teacher);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "auditMetadata", ignore = true)
    @Mapping(target = "ues", ignore = true)
    Teacher toEntity(TeacherRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(TeacherRequest dto, @MappingTarget Teacher entity);
}
