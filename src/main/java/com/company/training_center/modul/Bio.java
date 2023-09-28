package com.company.training_center.modul;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Bio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bioId;
    private String bioName;
    private String ext;
    private byte [] data;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bio_id")
    private Teacher teacher;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
