package com.company.training_center.service.mapper;

import com.company.training_center.dto.TeamDto;
import com.company.training_center.modul.Team;
import org.hibernate.validator.internal.engine.groups.Group;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TeamMapper {
      @Mapping(target = "teamId",ignore = true)
      public abstract Team toEntity(TeamDto dto);
      public abstract TeamDto toDto(Team dto);
      @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Team.class)
      public abstract Team update(TeamDto dto,@MappingTarget Team team);
}
