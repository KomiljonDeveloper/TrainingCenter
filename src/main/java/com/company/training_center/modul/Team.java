package com.company.training_center.modul;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team")
public class Team {
    @Id
    @SequenceGenerator(name = "team_id_seq",sequenceName = "team_id_sequence",allocationSize = 1)
    @GeneratedValue(generator = "team_id_seq")
    private Integer teamId;
    private String teamName;
    private String level;
    @ManyToMany(mappedBy = "teams")
    private Set<Student> students = new HashSet<>();
    @ManyToMany(mappedBy = "team_s")
    private Set<Teacher> teacher_s = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
