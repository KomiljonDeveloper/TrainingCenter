package com.company.training_center.service;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.dto.TeacherDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TeacherService implements SimpleCrUD<TeacherDto,Integer> {
    @Override
    public ResponseDto<TeacherDto> create(TeacherDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Page<TeacherDto>> get(Map<String, String> params) {
        return null;
    }


    @Override
    public ResponseDto<TeacherDto> update(TeacherDto dto, Integer id) {
        return null;
    }

    @Override
    public ResponseDto<TeacherDto> delete(Integer id) {
        return null;
    }
}
