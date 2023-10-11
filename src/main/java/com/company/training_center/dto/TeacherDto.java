package com.company.training_center.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDto {
    private Integer teacherId;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String firstName;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String lastName;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String fatherName;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    @Size(max = 13,min = 13,message = "This field can be length equal 13")
    private String phoneNumber;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String username;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String password;

}
