package com.company.training_center.service;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.ScienceDto;
import com.company.training_center.dto.SimpleCrUD;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScienceService implements SimpleCrUD<ScienceDto,Integer> {
    @Override
    public ResponseDto<ScienceDto> create(ScienceDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Page<ScienceDto>> get(Map<String, String> params) {
        return null;
    }

    @Override
    public ResponseDto<ScienceDto> update(ScienceDto dto, Integer id) {
        return null;
    }

    @Override
    public ResponseDto<ScienceDto> delete(Integer id) {
        return null;
    }
}
