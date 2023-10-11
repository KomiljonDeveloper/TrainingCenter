package com.company.training_center.repository;

import com.company.training_center.modul.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Query(
            value = "select * from student as s where " +
                    "s.id = coalesce(:id,s.id) and " +
                    "s.first_name ilike coalesce(concat('%',:firstname,'%'),s.first_name) and " +
                    "s.last_name ilike coalesce(concat('%',:lastname,'%'),s.last_name) and " +
                    "s.email ilike coalesce(concat('%',:email,'%'),s.email) and " +
                    "s.username ilike coalesce(concat('%',:username,'%'),s.username) and " +
                    "s.school ilike coalesce(concat('%',:school,'%'),s.school) and " +
                    "s.deleted_at is null",
            nativeQuery = true
    )
    Page<Student> searchByStudent(
            @Param(value = "id") Integer id,
            @Param(value = "firstname") String f_name,
            @Param(value = "lastname") String l_name,
            @Param(value = "email") String email,
            @Param(value = "username") String username,
            @Param(value = "school") String school,
            Pageable pageable
    );

    Optional<Student> findByIdAndDeletedAtIsNull(Integer integer);
}
