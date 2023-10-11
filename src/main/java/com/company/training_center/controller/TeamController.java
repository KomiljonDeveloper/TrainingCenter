package com.company.training_center.controller;

import com.company.training_center.dto.TeamDto;
import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("team")
@RequiredArgsConstructor
public class TeamController implements SimpleCrUD<TeamDto, Integer> {

    private final TeamService teamservice;

    @Override
    @PostMapping
    public ResponseDto<TeamDto> create(@RequestBody @Valid TeamDto dto) {
        return this.teamservice.create(dto);
    }

    @Override
    @GetMapping
    public ResponseDto<Page<TeamDto>> get(@RequestParam Map<String,String> params) {
        return this.teamservice.get(params);
    }

    @Override
    @PutMapping
    public ResponseDto<TeamDto> update(@RequestBody TeamDto dto, @RequestParam(value = "id") Integer id) {
        return this.teamservice.update(dto,id);
    }

    @Override
    @DeleteMapping
    public ResponseDto<TeamDto> delete(@RequestParam(value = "id") Integer id) {
        return this.teamservice.delete(id);
    }
}
