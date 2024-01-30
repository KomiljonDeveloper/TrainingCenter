package com.company.training_center.service;

import com.company.training_center.assistant.TeacherAssistant;
import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.dto.TeacherDto;
import com.company.training_center.modul.Teacher;
import com.company.training_center.repository.BioRepository;
import com.company.training_center.repository.TeacherRepository;
import com.company.training_center.service.mapper.TeacherMapper;
import com.company.training_center.service.validation.TeacherValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeacherService implements SimpleCrUD<TeacherDto, Integer> {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final TeacherAssistant teacherAssistant;
    private final BioRepository bioRepository;


    @Override
    public ResponseDto<TeacherDto> create(TeacherDto dto) {
        try {
            Teacher entity = this.teacherMapper.toEntity(dto);
            entity.setCreatedAt(LocalDateTime.now());
            this.teacherRepository.save(entity);
            return ResponseDto.<TeacherDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.teacherMapper.toDto(entity))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<TeacherDto>builder()
                    .message("Error : " + e.getMessage())
                    .code(-2)
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<TeacherDto>> get(Map<String, String> params) {
        try {
            Page<TeacherDto> map = this.teacherAssistant.assistantMethod(params).map(this.teacherMapper::toDto);

            if (map.isEmpty()) {
                return ResponseDto.<Page<TeacherDto>>builder()
                        .code(-1)
                        .message("Teachers are not found!")
                        .build();
            }
            return ResponseDto.<Page<TeacherDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(map)
                    .build();

        } catch (Exception e) {
            return ResponseDto.<Page<TeacherDto>>builder()
                    .code(-2)
                    .message("Error : " + e.getMessage())
                    .build();
        }
    }


    @Override
    public ResponseDto<TeacherDto> update(TeacherDto dto, Integer id) {
        try {
            return this.teacherRepository.findByIdAndDeletedAtIsNull(id).map(teacher -> {
                this.bioRepository.findByBio(id).setDeletedAt(LocalDateTime.now());
                teacher.setUpdatedAt(LocalDateTime.now());
                return ResponseDto.<TeacherDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.teacherMapper.toDto(this.teacherRepository.save(this.teacherMapper.update(dto, teacher))))
                        .build();
            }).orElse(ResponseDto.<TeacherDto>builder()
                    .code(-1)
                    .message("Teachers are not found!")
                    .build());
        } catch (Exception e) {
            return ResponseDto.<TeacherDto>builder()
                    .message("Error : " + e.getMessage())
                    .code(-2)
                    .build();
        }
    }

    @Override
    public ResponseDto<TeacherDto> delete(Integer id) {
        return this.teacherRepository.findByIdAndDeletedAtIsNull(id).map(teacher -> {
            this.bioRepository.findByBio(id).setDeletedAt(LocalDateTime.now());
            teacher.setDeletedAt(LocalDateTime.now());
            return ResponseDto.<TeacherDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.teacherMapper.toDto(this.teacherRepository.save(teacher)))
                    .build();
        }).orElse(ResponseDto.<TeacherDto>builder()
                .code(-1)
                .message("Teachers are not found!")
                .build());
    }
}
