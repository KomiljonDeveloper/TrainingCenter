package com.company.training_center.dto;

import org.springframework.http.ResponseEntity;

public interface SimpleFileCRUD<V> {
    ResponseEntity<?> upload(V file);
    ResponseEntity<?> download(Integer id);
    ResponseEntity<?> update(V file,Integer id);
    ResponseEntity<?> delete(Integer id);
}
