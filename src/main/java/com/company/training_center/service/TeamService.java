package com.company.training_center.service;

import com.company.training_center.assistant.TeamAssistant;
import com.company.training_center.dto.TeamDto;
import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.modul.Science;
import com.company.training_center.modul.Teacher;
import com.company.training_center.modul.Team;
import com.company.training_center.repository.ScienceRepository;
import com.company.training_center.repository.TeacherRepository;
import com.company.training_center.repository.TeamRepository;
import com.company.training_center.service.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeamService implements SimpleCrUD<TeamDto,Integer> {


    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final TeamAssistant teamAssistant;
    private final TeacherRepository teacherRepository;
    private final ScienceRepository scienceRepository;


    @Override
    public ResponseDto<TeamDto> create(TeamDto dto) {
            try {
                Team team = this.teamMapper.toEntity(dto);
                team.setCreatedAt(LocalDateTime.now());
                team.setSciences(this.scienceRepository.findAllByIdAndDeletedAtIsNull(dto.getScienceId()));
                team.setTeachers(this.teacherRepository.findAllByIdAndDeletedAtIsNull(dto.getTeacherId()));
                this.teamRepository.save(team);
                return ResponseDto.<TeamDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.teamMapper.toDto(team))
                        .build();


            }catch (Exception e){
                return  ResponseDto.<TeamDto>builder()
                        .message("Error : "+e.getMessage())
                        .code(-2)
                        .build();
            }

    }

    @Override
    public ResponseDto<Page<TeamDto>> get(Map<String, String> params) {
        try {
            Page<TeamDto> map = this.teamAssistant.assistantMethod(params).map(this.teamMapper::toDto);
            if (map.isEmpty()){
                return ResponseDto.<Page<TeamDto>>builder()
                        .message("Team is not found!")
                        .code(-1)
                        .build();
            }
            return ResponseDto.<Page<TeamDto>>builder()
                    .message("OK")
                    .success(true)
                    .data(map)
                    .build();
        }catch (Exception e){
            return ResponseDto.<Page<TeamDto>>builder()
                    .message("Error message : "+e.getMessage())
                    .code(-2)
                    .build();
        }
    }


    @Override
    public ResponseDto<TeamDto> update(TeamDto dto, Integer id) {
        return this.teamRepository.findByTeamIdAndDeletedAtIsNull(id).map(team -> {
            try {
                team.setUpdatedAt(LocalDateTime.now());
                return ResponseDto.<TeamDto>builder()
                        .message("OK")
                        .success(true)
                        .data(this.teamMapper.toDto(
                                this.teamRepository.save(
                                        this.teamMapper.update(dto, team))))
                        .build();
            }catch (Exception e){
                return ResponseDto.<TeamDto>builder()
                        .message("Error : "+e.getMessage())
                        .code(-2)
                        .build();
            }
        }).orElse(ResponseDto.<TeamDto>builder()
                        .message("Team is not found!")
                        .code(-1)
                .build());
    }

    @Override
    public ResponseDto<TeamDto> delete(Integer id) {
        return this.teamRepository.findByTeamIdAndDeletedAtIsNull(id).map(team -> {
            team.setDeletedAt(LocalDateTime.now());
            this.teamRepository.save(team);
            return ResponseDto.<TeamDto>builder()
                    .success(true)
                    .message("Team is deleted!")
                    .build();
        }).orElse(ResponseDto.<TeamDto>builder()
                        .message("Team is not found!")
                        .code(-1)
                .build());
    }
}
