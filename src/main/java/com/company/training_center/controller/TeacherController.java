package com.company.training_center.controller;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.dto.TeacherDto;
import com.company.training_center.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(
            tags = "Post",
            summary = "Bu methodga teacherlar haqida ma'lumotni o'zi kifoya!",
            description = "Bu method teacher ma'lumotlarini databasega joylash uchun ishlatiladi"
    )
    @PostMapping
    public ResponseDto<TeacherDto> create(@RequestBody @Valid TeacherDto dto) {
        return this.teacherService.create(dto);
    }

    @Override
    @Operation(
            tags = "Get",
            summary = "Bu methodga teacherlarni istalgan ma'lumoti orqali search qilishingiz mumkin!",
            description = "Bu method Teacher ma'lumotlarini databasedan tortib olib kelish uchun ishlatiladi!"
    )
    @GetMapping
    public ResponseDto<Page<TeacherDto>> get(@RequestParam Map<String,String> params) {
        return this.teacherService.get(params);
    }

    @Override
    @Operation(
            tags = "Update",
            summary = "Bu methodga o'zgartirmoqchi bo'lgan teacher id(id url orqali / belgisi bilan yoziladi)sini va o'zgartirmoqchi bo'lgan teacher ma'lumotlarini berib yuboriladi!",
            description = "Bu method databasedagi teacher ma'lumotlarini o'zgartirish uchun ishlatiladi!"
    )
    @PutMapping("/{id}")
    public ResponseDto<TeacherDto> update(@RequestBody TeacherDto dto, @PathVariable Integer id) {
        return this.teacherService.update(dto,id);
    }

    @Override
    @Operation(
            tags = "Delete",
            summary = "Bu methodga o'chirmoqchi bo'lgan teacherni id(id url orqali (?id=5) belgisi bilan yoziladi)sini yuborishni o'zi kifoya!",
            description = "Bu method databasedagi teacher ma'lumotlarini o'chirish uchun ishlatiladi!"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<TeacherDto> delete(@PathVariable Integer id) {
        return this.teacherService.delete(id);
    }
}
