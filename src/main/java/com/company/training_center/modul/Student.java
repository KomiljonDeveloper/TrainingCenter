package com.company.training_center.modul;

import com.company.training_center.dto.PaymentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class
Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "phone_number",unique = true)
    private String phoneNumber;
    @Column(name = "username",unique = true)
    private String username;
    private String password;
    private String school;
    private String birthday;
    @OneToOne(mappedBy = "student")
    private Payment payment;
    @ManyToMany
    @JoinTable(
            name = "Student_Teams",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> team_s = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
