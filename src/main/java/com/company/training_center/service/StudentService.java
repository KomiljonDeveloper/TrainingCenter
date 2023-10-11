package com.company.training_center.service;

import com.company.training_center.assistant.StudentAssistant;
import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.dto.StudentDto;
import com.company.training_center.modul.Student;
import com.company.training_center.repository.ScienceRepository;
import com.company.training_center.repository.StudentRepository;
import com.company.training_center.repository.TeamRepository;
import com.company.training_center.service.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService implements SimpleCrUD<StudentDto, Integer> {

    private final StudentMapper studentMapper;
    private final StudentAssistant studentAssistant;
    private final StudentRepository studentRepository;
    private final TeamRepository teamRepository;

    @Override
    public ResponseDto<StudentDto> create(StudentDto dto) {
        try {
            return this.teamRepository.findAllByTeamIdAndDeletedAtIsNull(dto.getTeamId()).map(team -> {
                Student entity = this.studentMapper.toEntity(dto);
                entity.setCreatedAt(LocalDateTime.now());
                entity.setTeam_s(team);
                this.studentRepository.save(entity);
                return ResponseDto.<StudentDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.studentMapper.toDto(entity))
                        .build();
            }).orElse(ResponseDto.<StudentDto>builder()
                    .code(-1)
                    .message("Team is not found!")
                    .build());
        } catch (Exception e) {
            return ResponseDto.<StudentDto>builder()
                    .code(-2)
                    .message("Error : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<StudentDto>> get(Map<String, String> params) {
        Page<StudentDto> map = this.studentAssistant.assistantMethod(params).map(this.studentMapper::toDto);
        if (map.isEmpty()) {
            return ResponseDto.<Page<StudentDto>>builder()
                    .code(-1)
                    .message("Student are not found!")
                    .build();
        }
        return ResponseDto.<Page<StudentDto>>builder()
                .message("OK")
                .success(true)
                .data(map)
                .build();
    }

    @Override
    public ResponseDto<StudentDto> update(StudentDto dto, Integer id) {
        try {
            return this.studentRepository.findByIdAndDeletedAtIsNull(id).map(student -> {
                student.setUpdatedAt(LocalDateTime.now());
                return ResponseDto.<StudentDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.studentMapper.toDto(this.studentRepository.save(this.studentMapper.update(dto, student))))
                        .build();
            }).orElse(ResponseDto.<StudentDto>builder()
                    .code(-1)
                    .message("Student is not found!")
                    .build());
        } catch (Exception e) {
            return ResponseDto.<StudentDto>builder()
                    .message("Error : " + e.getMessage())
                    .code(-2)
                    .build();

        }

    }

    @Override
    public ResponseDto<StudentDto> delete(Integer id) {
        return this.studentRepository.findByIdAndDeletedAtIsNull(id).map(student -> {
            student.setDeletedAt(LocalDateTime.now());
            this.studentRepository.save(student);
            return ResponseDto.<StudentDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.studentMapper.toDto(student))
                    .build();
        }).orElse(ResponseDto.<StudentDto>builder()
                .message("User is not found!")
                .code(-2)
                .build());
    }
}
