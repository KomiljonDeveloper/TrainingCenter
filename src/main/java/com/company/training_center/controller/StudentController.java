package com.company.training_center.controller;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.dto.StudentDto;
import com.company.training_center.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(
            tags = "Post",
            summary = "Bu methodga studentlar haqida ma'lumotni o'zi kifoya!",
            description = "Bu method student ma'lumotlarini databasega joylash uchun ishlatiladi"
    )
    @PostMapping
    public ResponseDto<StudentDto> create(@RequestBody @Valid StudentDto dto) {
        return this.studentService.create(dto);
    }

    @Override
    @Operation(
            tags = "Get",
            summary = "Bu methodga studentlarni istalgan ma'lumoti orqali search qilishingiz mumkin!",
            description = "Bu method Student ma'lumotlarini databasedan tortib olib kelish uchun ishlatiladi!"
    )
    @GetMapping
    public ResponseDto<Page<StudentDto>> get(@RequestParam Map<String, String> params) {
        return this.studentService.get(params);
    }


    @Override
    @Operation(
            tags = "Update",
            summary = "Bu methodga o'zgartirmoqchi bo'lgan student  id(id url orqali / belgisi bilan yoziladi)sini va o'zgartirmoqchi bo'lgan student ma'lumotlarini berib yuboriladi!",
            description = "Bu method databasedagi student ma'lumotlarini o'zgartirish uchun ishlatiladi!"
    )
    @PutMapping("/{id}")
    public ResponseDto<StudentDto> update(@RequestBody StudentDto dto, @PathVariable Integer id) {
        return this.studentService.update(dto, id);
    }

    @Override
    @Operation(
            tags = "Delete",
            summary = "Bu methodga o'chirmoqchi bo'lgan studentni id(id url orqali (?id=5) belgisi bilan yoziladi)sini yuborishni o'zi kifoya!",
            description = "Bu method databasedagi student ma'lumotlarini o'chirish uchun ishlatiladi!"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<StudentDto> delete(@PathVariable Integer id) {
        return this.studentService.delete(id);
    }
}
