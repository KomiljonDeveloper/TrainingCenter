package com.company.training_center.service;

import com.company.training_center.assistant.ScienceAssistant;
import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.ScienceDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.modul.Science;
import com.company.training_center.repository.ScienceRepository;
import com.company.training_center.service.mapper.ScienceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScienceService implements SimpleCrUD<ScienceDto, Integer> {

    private final ScienceRepository scienceRepository;
    private final ScienceMapper scienceMapper;
    private final ScienceAssistant scienceAssistant;

    @Override
    public ResponseDto<ScienceDto> create(ScienceDto dto) {
        try {
            Science entity = this.scienceMapper.toEntity(dto);
            entity.setCreatedAt(LocalDateTime.now());
            this.scienceRepository.save(entity);
            return ResponseDto.<ScienceDto>builder()
                    .message("Ok")
                    .success(true)
                    .data(this.scienceMapper.toDto(entity))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ScienceDto>builder()
                    .message("Error : " + e.getMessage())
                    .code(-2)
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<ScienceDto>> get(Map<String, String> params) {
        try {
            Page<ScienceDto> map = this.scienceAssistant.assistantMethod(params).map(this.scienceMapper::toDto);
            if (!map.isEmpty()) {
                return ResponseDto.<Page<ScienceDto>>builder()
                        .message("Ok")
                        .success(true)
                        .data(map)
                        .build();
            } else {
                return ResponseDto.<Page<ScienceDto>>builder()
                        .message("Science is not found!")
                        .code(-1)
                        .build();
            }
        } catch (Exception e) {
            return ResponseDto.<Page<ScienceDto>>builder()
                    .message("Error : " + e.getMessage())
                    .code(-2)
                    .build();
        }

    }

    @Override
    public ResponseDto<ScienceDto> update(ScienceDto dto, Integer id) {
        try {
            return this.scienceRepository.findByIdAndDeletedAtIsNull(id).map(science -> {
                science.setUpdatedAt(LocalDateTime.now());
                return ResponseDto.<ScienceDto>builder()
                        .message("Ok")
                        .success(true)
                        .data(this.scienceMapper.toDto(this.scienceRepository.save(this.scienceMapper.update(dto, science))))
                        .build();
            }).orElse(ResponseDto.<ScienceDto>builder()
                    .message("Science is not found!")
                    .code(-1)
                    .build());
        } catch (Exception e) {
            return ResponseDto.<ScienceDto>builder()
                    .message("Error : " + e.getMessage())
                    .code(-2)
                    .build();
        }
    }

    @Override
    public ResponseDto<ScienceDto> delete(Integer id) {
        return this.scienceRepository.findByIdAndDeletedAtIsNull(id).map(science -> {
            science.setDeletedAt(LocalDateTime.now());
            this.scienceRepository.save(science);
            return ResponseDto.<ScienceDto>builder()
                    .message("Ok")
                    .success(true)
                    .data(this.scienceMapper.toDto(science))
                    .build();
        }).orElse(ResponseDto.<ScienceDto>builder()
                .message("Science is not found!")
                .code(-2)
                .build());
    }
}
