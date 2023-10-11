package com.company.training_center.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
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
public class ScienceDto {
    private Integer scienceId;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String scienceName;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String room;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String time;
}
