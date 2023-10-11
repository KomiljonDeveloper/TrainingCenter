package com.company.training_center.controller;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleFileCRUD;
import com.company.training_center.service.BioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("bio")
@RequiredArgsConstructor
public class BioController implements SimpleFileCRUD<MultipartFile> {

     private final BioService bioService;

    @Override
    @PostMapping
    public ResponseEntity<?> upload(@RequestParam(value = "id") Integer id ,@RequestParam(value = "bio") MultipartFile file) {
        return this.bioService.upload(id,file);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> download(@RequestParam(value = "id") Integer id) {
        return this.bioService.download(id);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(@RequestParam(value = "bio") MultipartFile file,@RequestParam("id") Integer id) {
        return this.bioService.update(file, id);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam(value = "id") Integer id) {
        return this.bioService.delete(id);
    }
}
