package com.company.training_center.repository;

import com.company.training_center.modul.Science;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ScienceRepository extends JpaRepository<Science,Integer> {

       Optional<Science> findByIdAndDeletedAtIsNull(Integer integer);

       Set<Science> findAllByIdAndDeletedAtIsNull(Integer integer);
       @Query(
               value = "select * from science as s where s.id = coalesce(:id,s.id) and " +
                       "s.science_name ilike coalesce(concat('%',:name,'%'),s.science_name) and " +
                       "s.room ilike coalesce(concat('%',:room,'%'),s.room) and " +
                       "s.deleted_at is null",
               nativeQuery = true
       )
       Page<Science> searchScience(
               @Param(value = "id") Integer id,
               @Param(value = "name") String name,
               @Param(value = "room") String room,
               Pageable pageable
       );
}
