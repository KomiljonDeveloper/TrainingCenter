package com.company.training_center.controller;

import com.company.training_center.dto.PaymentDto;
import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("payment")
public record PaymentController(PaymentService paymentService) implements SimpleCrUD<PaymentDto,Integer> {

    @Override
    @Operation(
            tags = "Post",
            summary = "Bu methodga Payment haqida ma'lumotni o'zi kifoya!",
            description = "Bu method Payment ma'lumotlarini databasega joylash uchun ishlatiladi"
    )
    @PostMapping
    public ResponseDto<PaymentDto> create(@RequestBody @Valid PaymentDto dto) {
        return this.paymentService.create(dto);
    }

    @Override
    @Operation(
            tags = "Get",
            summary = "Bu methodga Paymentni istalgan ma'lumoti orqali search qilishingiz mumkin!",
            description = "Bu method Payment ma'lumotlarini databasedan tortib olib kelish uchun ishlatiladi!"
    )
    @GetMapping
    public ResponseDto<Page<PaymentDto>> get(@RequestParam Map<String, String> params) {
        return this.paymentService.get(params);
    }


    @Override
    @Operation(
            tags = "Update",
            summary = "Bu methodga o'zgartirmoqchi bo'lgan payment  id(id url orqali / belgisi bilan yoziladi)sini va o'zgartirmoqchi bo'lgan payment ma'lumotlarini berib yuboriladi!",
            description = "Bu method databasedagi Payment ma'lumotlarini o'zgartirish uchun ishlatiladi!"
    )
    @PutMapping("/{id}")
    public ResponseDto<PaymentDto> update(@RequestBody PaymentDto dto,@PathVariable Integer id) {
        return this.paymentService.update(dto, id);
    }

    @Override
    @Operation(
            tags = "Delete",
            summary = "Bu methodga o'chirmoqchi bo'lgan paymentni id(id url orqali / belgisi bilan yoziladi)sini yuborishni o'zi kifoya!",
            description = "Bu method databasedagi payment ma'lumotlarini o'chirish uchun ishlatiladi!"
    )
    @DeleteMapping("/{id}")
    public ResponseDto<PaymentDto> delete(@PathVariable Integer id) {
        return this.paymentService.delete(id);
    }
}
