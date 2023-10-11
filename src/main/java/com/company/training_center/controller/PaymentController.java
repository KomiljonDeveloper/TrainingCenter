package com.company.training_center.controller;

import com.company.training_center.dto.PaymentDto;
import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("payment")
public record PaymentController(PaymentService paymentService) implements SimpleCrUD<PaymentDto,Integer> {

    @Override
    @PostMapping
    public ResponseDto<PaymentDto> create(@RequestBody @Valid PaymentDto dto) {
        return this.paymentService.create(dto);
    }

    @Override
    @GetMapping
    public ResponseDto<Page<PaymentDto>> get(@RequestParam Map<String, String> params) {
        return this.paymentService.get(params);
    }


    @Override
    @PutMapping("/{id}")
    public ResponseDto<PaymentDto> update(@RequestBody PaymentDto dto,@PathVariable Integer id) {
        return this.paymentService.update(dto, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseDto<PaymentDto> delete(@PathVariable Integer id) {
        return this.paymentService.delete(id);
    }
}
