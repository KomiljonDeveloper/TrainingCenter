package com.company.training_center.dto;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface SimpleCrUD<T,V> {
    ResponseDto<T> create(T dto);
    ResponseDto<Page<T>> get(Map<String,String> params);
    ResponseDto<T> update(T dto,V id);
    ResponseDto<T> delete(V id);

}
