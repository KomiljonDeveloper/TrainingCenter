package com.company.training_center.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
// TODO : @NotNull: CharSequence, Collection, Map yoki Array obyekti null emas, lekin bo'sh bo'lishi mumkin.
// TODO : @NotEmpty: CharSequence, Collection, Map yoki Array obyekti null emas va hajmi > 0.
// TODO : @NotBlank: satr null emas va kesilgan uzunlik noldan katta.
public class TeamDto {
    private Integer teamId;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String teamName;
    private Set<StudentDto> students;
    @NotBlank(message = "The object is not null and the trimmed length is greater than zero.")
    private String level;
    @NotNull(message = "This field cannot be empty!")
    private Integer teacherId;
    @NotNull(message = "This field cannot be empty!")
    private Integer scienceId;
}
