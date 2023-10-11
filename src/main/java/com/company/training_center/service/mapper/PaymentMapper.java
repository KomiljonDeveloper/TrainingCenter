package com.company.training_center.service.mapper;

import com.company.training_center.dto.PaymentDto;
import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleCrUD;
import com.company.training_center.modul.Payment;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class PaymentMapper {
    public abstract PaymentDto toDto(Payment entity);
    @Mapping(target = "id",source = "paymentId",ignore = true)
    public abstract Payment toEntity(PaymentDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Payment.class)
    public abstract Payment update(@MappingTarget Payment entity, PaymentDto dto);

}
