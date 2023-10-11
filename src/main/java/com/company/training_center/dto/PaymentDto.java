package com.company.training_center.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {
    private Integer paymentId;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String month;
    @NotNull(message = "The object is not null and the trimmed length is greater than zero.")
    private Double amount;
    @NotNull(message = "The object is not null and the trimmed length is greater than zero.")
    private Integer studentId;
}
