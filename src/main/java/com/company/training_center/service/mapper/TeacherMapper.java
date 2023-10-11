package com.company.training_center.service.mapper;

import com.company.training_center.dto.TeacherDto;
import com.company.training_center.modul.Teacher;
import org.mapstruct.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TeacherMapper {
    @Mapping(target = "id",ignore = true)
    public abstract Teacher toEntity(TeacherDto dto);
    public abstract TeacherDto toDto(Teacher entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Teacher.class)
    public abstract Teacher update(TeacherDto dto,@MappingTarget Teacher entity);

}
