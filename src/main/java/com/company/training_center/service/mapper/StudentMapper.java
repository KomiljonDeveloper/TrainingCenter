package com.company.training_center.service.mapper;

import com.company.training_center.dto.StudentDto;
import com.company.training_center.modul.Student;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class StudentMapper  {
    @Mapping(target = "id",ignore = true)
    public abstract Student toEntity(StudentDto dto);
    public abstract StudentDto toDto(Student entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Student.class)
    public abstract Student update(StudentDto dto,@MappingTarget Student entity);

}
