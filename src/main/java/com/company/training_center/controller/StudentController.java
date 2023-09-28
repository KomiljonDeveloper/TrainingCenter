package com.company.training_center.controller;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.dto.StudentDto;
import com.company.training_center.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController implements SimpleCrUD<StudentDto,Integer> {

    private final StudentService studentService;

    @Override
    @PostMapping
    public ResponseDto<StudentDto> create(@RequestBody @Valid StudentDto dto) {
        return this.studentService.create(dto);
    }

    @Override
    @GetMapping
    public ResponseDto<Page<StudentDto>> get(@RequestParam Map<String, String> params) {
        return this.studentService.get(params);
    }


    @Override
    @PutMapping("/{id}")
    public ResponseDto<StudentDto> update(@RequestBody @Valid StudentDto dto, @PathVariable Integer id) {
        return this.studentService.update(dto, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseDto<StudentDto> delete(@PathVariable Integer id) {
        return this.studentService.delete(id);
    }
}
