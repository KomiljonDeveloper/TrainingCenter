package com.company.training_center.controller;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.ScienceDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.service.ScienceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("science")
public record ScienceController(ScienceService scienceService) implements SimpleCrUD<ScienceDto,Integer> {
    @Override
    @Operation(
            tags = "Post",
            summary = "Bu methodga fanlar haqida ma'lumotni o'zi kifoya!",
            description = "Bu method Fan ma'lumotlarini databasega joylash uchun ishlatiladi"
    )
    @PostMapping
    public ResponseDto<ScienceDto> create(@RequestBody @Valid ScienceDto dto) {
        return this.scienceService.create(dto);
    }

    @Override
    @Operation(
            tags = "Get",
            summary = "Bu methodga fanlarni istalgan ma'lumoti orqali search qilishingiz mumkin!",
            description = "Bu method Fan ma'lumotlarini databasedan tortib olib kelish uchun ishlatiladi!"
    )
    @GetMapping
    public ResponseDto<Page<ScienceDto>> get(@RequestParam Map<String, String> params) {
        return this.scienceService.get(params);
    }

    @Override
    @Operation(
            tags = "Update",
            summary = "Bu methodga o'zgartirmoqchi bo'lgan fan  id(id url orqali / belgisi bilan yoziladi)sini va o'zgartirmoqchi bo'lgan fan ma'lumotlarini berib yuboriladi!",
            description = "Bu method databasedagi fan ma'lumotlarini o'zgartirish uchun ishlatiladi!"
    )
    @PutMapping("/{id}")
    public ResponseDto<ScienceDto> update(@RequestBody ScienceDto dto, @PathVariable Integer id) {
        return this.scienceService.update(dto, id);
    }

    @Override
    @Operation(
            tags = "Delete",
            summary = "Bu methodga o'chirmoqchi bo'lgan fanni id(id url orqali (?id=5) belgisi bilan yoziladi)sini yuborishni o'zi kifoya!",
            description = "Bu method databasedagi fan ma'lumotlarini o'chirish uchun ishlatiladi!"
    )
    @DeleteMapping
    public ResponseDto<ScienceDto> delete(@RequestParam(value = "id") Integer id) {
        return this.scienceService.delete(id);
    }
}
