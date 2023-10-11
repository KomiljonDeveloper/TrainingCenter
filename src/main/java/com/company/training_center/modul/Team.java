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
    @ManyToMany(mappedBy = "team_s")
    private Set<Student> students = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "Teacher_Team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> teachers = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "Science_Team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "science_id")
    )
    private Set<Science> sciences = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
