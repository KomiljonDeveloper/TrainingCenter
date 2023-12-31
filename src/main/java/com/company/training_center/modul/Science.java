package com.company.training_center.modul;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "science")
public class Science {
    @Id
    @SequenceGenerator(name = "science_id_seq",sequenceName = "science_id_sequence",allocationSize = 1)
    @GeneratedValue(generator = "science_id_seq")
    private Integer id;
    private String scienceName;
    private String room;
    private LocalDateTime time;
    @ManyToMany(mappedBy = "sciences")
    private Set<Student> students = new HashSet<>();

}
