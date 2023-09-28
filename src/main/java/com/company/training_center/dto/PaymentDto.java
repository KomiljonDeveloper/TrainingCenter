package com.company.training_center.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {
    private Integer paymentId;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String month;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private Double amount;

}
