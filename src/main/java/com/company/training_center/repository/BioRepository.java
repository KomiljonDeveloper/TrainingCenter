package com.company.training_center.repository;

import com.company.training_center.modul.Bio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BioRepository extends JpaRepository<Bio,Integer> {
    @Query(
            value = "select * from bio where teacher_id = :id",
            nativeQuery = true
    )
    Bio findByBio(@Param(value = "id") Integer id);

    Optional<Bio> findByBioIdAndDeletedAtIsNull(Integer id);
}
