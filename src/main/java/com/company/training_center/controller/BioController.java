package com.company.training_center.controller;

import com.company.training_center.dto.ResponseDto;
import com.company.training_center.dto.SimpleFileCRUD;
import com.company.training_center.service.BioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
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
    @Operation(
            tags = "Post",
            summary = "Bu methodga teacher id raqami hamda imageni berib yuboriladi va image nameni \"bio\" deb yozing!",
            description = "Bu method Imagelarni databasega joylash uchun ishlatiladi!"
    )
    @PostMapping
    public ResponseEntity<?> upload(@RequestParam(value = "id") Integer id ,@RequestParam(value = "bio") MultipartFile file) {
        return this.bioService.upload(id,file);
    }

    @Override
    @Operation(
            tags = "Get",
            summary = "Bu methodga bio ya'ni rasmni idsini berib yuboriladi!",
            description = "Bu method Imagelarni databasedan tortib olib kelish uchun ishlatiladi!"
    )
    @GetMapping
    public ResponseEntity<?> download(@RequestParam(value = "id") Integer id) {
        return this.bioService.download(id);
    }

    @Override
    @Operation(
            tags = "Update",
            summary = "Bu methodga o'zgartirmoqchi bo'lgan bio ya'ni rasmni id(id url orqali (?id=5) belgisi bilan yoziladi)sini va o'zgartirmoqchi bo'lgan rasimni berib yuboriladi!",
            description = "Bu method databasedagi Imagelarni o'zgartirish uchun ishlatiladi!"
    )
    @PutMapping
    public ResponseEntity<?> update(@RequestParam(value = "bio") MultipartFile file,@RequestParam("id") Integer id) {
        return this.bioService.update(file, id);
    }

    @Override
    @Operation(
            tags = "Delete",
            summary = "Bu methodga o'chirmoqchi bo'lgan bio ya'ni rasmni id(id url orqali (?id=5) belgisi bilan yoziladi)sini yuborishni o'zi kifoya!",
            description = "Bu method databasedagi Imagelarni o'chirish uchun ishlatiladi!"
    )
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam(value = "id") Integer id) {
        return this.bioService.delete(id);
    }
}
