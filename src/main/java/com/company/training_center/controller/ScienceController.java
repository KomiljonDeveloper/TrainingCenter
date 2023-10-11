package com.company.training_center.controller;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.ScienceDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.service.ScienceService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("science")
public record ScienceController(ScienceService scienceService) implements SimpleCrUD<ScienceDto,Integer> {
    @Override
    @PostMapping
    public ResponseDto<ScienceDto> create(@RequestBody @Valid ScienceDto dto) {
        return this.scienceService.create(dto);
    }

    @Override
    @GetMapping
    public ResponseDto<Page<ScienceDto>> get(@RequestParam Map<String, String> params) {
        return this.scienceService.get(params);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseDto<ScienceDto> update(@RequestBody ScienceDto dto, @PathVariable Integer id) {
        return this.scienceService.update(dto, id);
    }

    @Override
    @DeleteMapping
    public ResponseDto<ScienceDto> delete(@RequestParam(value = "id") Integer id) {
        return this.scienceService.delete(id);
    }
}
