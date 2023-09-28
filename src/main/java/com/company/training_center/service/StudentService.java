package com.company.training_center.service;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.dto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentService implements SimpleCrUD<StudentDto,Integer> {
    @Override
    public ResponseDto<StudentDto> create(StudentDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Page<StudentDto>> get(Map<String, String> params) {
        return null;
    }

    @Override
    public ResponseDto<StudentDto> update(StudentDto dto, Integer id) {
        return null;
    }

    @Override
    public ResponseDto<StudentDto> delete(Integer id) {
        return null;
    }
}
