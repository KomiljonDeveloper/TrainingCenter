package com.company.training_center.controller;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.dto.TeacherDto;
import com.company.training_center.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("teacher")
@RequiredArgsConstructor
public class TeacherController implements SimpleCrUD<TeacherDto,Integer> {

    private final TeacherService teacherService;

    @Override
    @PostMapping
    public ResponseDto<TeacherDto> create(@RequestBody @Valid TeacherDto dto) {
        return this.teacherService.create(dto);
    }

    @Override
    @GetMapping
    public ResponseDto<Page<TeacherDto>> get(@RequestParam Map<String,String> params) {
        return this.teacherService.get(params);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseDto<TeacherDto> update(@RequestBody TeacherDto dto, @PathVariable Integer id) {
        return this.teacherService.update(dto,id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseDto<TeacherDto> delete(@PathVariable Integer id) {
        return this.teacherService.delete(id);
    }
}
