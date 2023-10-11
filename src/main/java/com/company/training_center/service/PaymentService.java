package com.company.training_center.service;

import com.company.training_center.assistant.PaymentAssistant;
import com.company.training_center.dto.PaymentDto;
import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.modul.Payment;
import com.company.training_center.repository.PaymentRepository;
import com.company.training_center.repository.StudentRepository;
import com.company.training_center.service.mapper.PaymentMapper;
import com.company.training_center.service.validation.PaymentValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService implements SimpleCrUD<PaymentDto, Integer> {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentValidation paymentValidation;
    private final StudentRepository studentRepository;
    private final PaymentAssistant paymentAssistant;


    @Override
    public ResponseDto<PaymentDto> create(PaymentDto dto) {
        try {
           return this.studentRepository.findByIdAndDeletedAtIsNull(dto.getStudentId()).map(student -> {
                Payment entity = this.paymentMapper.toEntity(dto);
                entity.setCreatedAt(LocalDateTime.now());
                entity.setStudent(student);
                this.paymentRepository.save(entity);
                return ResponseDto.<PaymentDto>builder()
                        .message("OK")
                        .success(true)
                        .data(this.paymentMapper.toDto(entity))
                        .build();
            }).orElse(ResponseDto.<PaymentDto>builder()
                    .message("Student is not found!")
                    .code(-1)
                    .build());
        } catch (Exception e) {
            return ResponseDto.<PaymentDto>builder()
                    .message("Error : " + e.getMessage())
                    .code(-2)
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<PaymentDto>> get(Map<String, String> params) {
        try {
            Page<PaymentDto> map = this.paymentAssistant.assistantMethod(params).map(this.paymentMapper::toDto);
            if (map.isEmpty()) {
                return ResponseDto.<Page<PaymentDto>>builder()
                        .code(-1)
                        .message("Payments are not found!")
                        .build();
            } else {
                return ResponseDto.<Page<PaymentDto>>builder()
                        .success(true)
                        .message("OK")
                        .data(map)
                        .build();
            }
        }catch (Exception e){
            return ResponseDto.<Page<PaymentDto>>builder()
                    .code(-2)
                    .message("Error : "+e.getMessage())
                    .build();
        }
    }


    @Override
    public ResponseDto<PaymentDto> update(PaymentDto dto, Integer id) {

        try {
       return this.paymentRepository.findByIdAndDeletedAtIsNull(id).map(payment -> {
            payment.setUpdatedAt(LocalDateTime.now());
            return ResponseDto.<PaymentDto>builder()
                    .message("OK")
                    .success(true)
                    .data(this.paymentMapper.toDto(this.paymentRepository.save(this.paymentMapper.update(payment,dto))))
                    .build();
        }).orElse(
                ResponseDto.<PaymentDto>builder()
                        .message("Payment is not found!")
                        .code(-1)
                        .build()
       );

    }catch (Exception e){
            return ResponseDto.<PaymentDto>builder()
                    .code(-2)
                    .message("Error : "+e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<PaymentDto> delete(Integer id) {
        return this.paymentRepository.findByIdAndDeletedAtIsNull(id).map(payment -> {
            payment.setDeletedAt(LocalDateTime.now());
            return ResponseDto.<PaymentDto>builder()
                    .message("Ok")
                    .success(true)
                    .data(this.paymentMapper.toDto(this.paymentRepository.save(payment)))
                    .build();
        }).orElse(
                ResponseDto.<PaymentDto>builder()
                        .message("Payment is not found!")
                        .code(-1)
                        .build()
        );
    }
}
