package com.company.training_center.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {
    private String message;
    private boolean success;
    private int code;
    private T data;
    private List<ErrorDto> errors;
}
