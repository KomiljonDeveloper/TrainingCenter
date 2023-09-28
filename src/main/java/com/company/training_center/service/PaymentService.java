package com.company.training_center.service;

import com.company.training_center.dto.PaymentDto;
import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService implements SimpleCrUD<PaymentDto,Integer> {
    @Override
    public ResponseDto<PaymentDto> create(PaymentDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Page<PaymentDto>> get(Map<String, String> params) {
        return null;
    }


    @Override
    public ResponseDto<PaymentDto> update(PaymentDto dto, Integer id) {
        return null;
    }

    @Override
    public ResponseDto<PaymentDto> delete(Integer id) {
        return null;
    }
}
