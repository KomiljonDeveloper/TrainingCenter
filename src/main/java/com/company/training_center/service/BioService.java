package com.company.training_center.service;

import com.company.training_center.dto.SimpleFileCRUD;
import com.company.training_center.modul.Bio;
import com.company.training_center.repository.BioRepository;
import com.company.training_center.repository.TeacherRepository;
import com.company.training_center.util.BioUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BioService implements SimpleFileCRUD<MultipartFile> {
    private final BioRepository bioRepository;
    private final TeacherRepository teacherRepository;
    @Override
    public ResponseEntity<?> upload(Integer id,MultipartFile file) {

         return  this.teacherRepository.findByIdAndDeletedAtIsNull(id).map(teacher -> {
                try {
                Bio build = Bio.builder()
                        .bioName(file.getOriginalFilename())
                        .ext(file.getContentType())
                        .data(BioUtils.compressBio(file.getBytes()))
                        .build();
                build.setCreatedAt(LocalDateTime.now());
                build.setTeacher(teacher);
                this.bioRepository.save(build);
                return ResponseEntity.status(HttpStatus.OK).body(String.format("Bio has been successfully installed : %s", build.getBioName()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to install bio");
        }
        }).orElse(ResponseEntity.badRequest().body("Teacher is not found!"));
    }

    @Override
    public ResponseEntity<?> download(Integer id) {
        return this.bioRepository.findByBioIdAndDeletedAtIsNull(id).map(bio -> {
            try {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.valueOf("image/png"))
                        .body(BioUtils.deCompressBio(bio.getData()));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
            }
        }).orElse(ResponseEntity.status(HttpStatus.OK).body("Bio is not found!"));
    }

    @Override
    public ResponseEntity<?> update(MultipartFile file, Integer id) {
        return this.bioRepository.findByBioIdAndDeletedAtIsNull(id).map(bio -> {
            try {
            bio.setBioName(file.getOriginalFilename());
            bio.setExt(file.getContentType());
            bio.setData(BioUtils.compressBio(file.getBytes()));
            bio.setUpdatedAt(LocalDateTime.now());
            this.bioRepository.save(bio);
            return ResponseEntity.status(HttpStatus.OK).body("Bio successfully updating!");
            } catch (IOException e) {
                return ResponseEntity.badRequest().body("Error while converting : " + e.getMessage());
            }
        }).orElse(ResponseEntity.badRequest().body("Bio is not found!"));
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
       return   this.bioRepository.findByBioIdAndDeletedAtIsNull(id).map(bio -> {
            bio.setDeletedAt(LocalDateTime.now());
            this.bioRepository.save(bio);
            return ResponseEntity.status(HttpStatus.OK).body("Bio is successfully deleted!");
        }).orElse(ResponseEntity.badRequest().body("Bio is not found!"));
    }
}
