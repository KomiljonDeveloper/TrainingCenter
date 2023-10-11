package com.company.training_center.service.validation;

import org.hibernate.validator.internal.properties.Field;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
public class TeacherValidation {
    public boolean checkEmail(String email) {
        return false;
    }
}
