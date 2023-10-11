package com.company.training_center.service.mapper;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.ScienceDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.modul.Science;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ScienceMapper {
    @Mapping(target = "id",ignore = true)
    public abstract Science toEntity(ScienceDto dto);

    public abstract ScienceDto toDto(Science entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Science.class)
    public abstract Science update(ScienceDto dto,@MappingTarget Science entity);

}
