package com.uds.master_isok.student;


import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(source = "id", target = "studentId")
    @Mapping(source = "auditMetadata.createdAt", target = "createdAt")
    @Mapping(source = "auditMetadata.updatedAt", target = "updatedAt")
    StudentResponse toDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "auditMetadata", ignore = true)
    Student toEntity(StudentRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(StudentRequest dto, @MappingTarget Student entity);

}
