package com.company.training_center.repository;

import com.company.training_center.modul.Bio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BioRepository extends JpaRepository<Bio,Integer> {
    Optional<Bio> findByBioIdAndDeletedAtIsNull(Integer id);
}
