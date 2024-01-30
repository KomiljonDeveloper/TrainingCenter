package com.company.training_center.controller;

import com.company.training_center.dto.TeamDto;
import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(
            tags = "Post",
            summary = "Bu methodga Guruhlar haqida ma'lumotni o'zi kifoya!",
            description = "Bu method guruh ma'lumotlarini databasega joylash uchun ishlatiladi"
    )
    @PostMapping
    public ResponseDto<TeamDto> create(@RequestBody @Valid TeamDto dto) {
        return this.teamservice.create(dto);
    }

    @Override
    @Operation(
            tags = "Get",
            summary = "Bu methodga guruhlarni istalgan ma'lumoti orqali search qilishingiz mumkin!",
            description = "Bu method guruh ma'lumotlarini databasedan tortib olib kelish uchun ishlatiladi!"
    )
    @GetMapping
    public ResponseDto<Page<TeamDto>> get(@RequestParam Map<String,String> params) {
        return this.teamservice.get(params);
    }

    @Override
    @Operation(
            tags = "Update",
            summary = "Bu methodga o'zgartirmoqchi bo'lgan guruh id(id url orqali / belgisi bilan yoziladi)sini va o'zgartirmoqchi bo'lgan guruh ma'lumotlarini berib yuboriladi!",
            description = "Bu method databasedagi guruh ma'lumotlarini o'zgartirish uchun ishlatiladi!"
    )
    @PutMapping
    public ResponseDto<TeamDto> update(@RequestBody TeamDto dto, @RequestParam(value = "id") Integer id) {
        return this.teamservice.update(dto,id);
    }

    @Override
    @Operation(
            tags = "Delete",
            summary = "Bu methodga o'chirmoqchi bo'lgan guruhni id(id url orqali (?id=5) belgisi bilan yoziladi)sini yuborishni o'zi kifoya!",
            description = "Bu method databasedagi guruh ma'lumotlarini o'chirish uchun ishlatiladi!"
    )
    @DeleteMapping
    public ResponseDto<TeamDto> delete(@RequestParam(value = "id") Integer id) {
        return this.teamservice.delete(id);
    }
}
