package com.company.training_center.repository;

import com.company.training_center.modul.Science;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScienceRepository extends JpaRepository<Science,Integer> {
}
