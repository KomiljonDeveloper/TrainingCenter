package com.company.training_center.repository;

import com.company.training_center.modul.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Set<Teacher> findAllByIdAndDeletedAtIsNull(Integer id);
    Optional<Teacher> findByIdAndDeletedAtIsNull(Integer id);

    @Query(
            value = "select t from Teacher as t where " +
                    "t.id = coalesce(:id,t.id) and " +
                    "t.firstName ilike coalesce(concat('%',:firstname,'%'),t.firstName) and " +
                    "t.lastName ilike coalesce(concat('%',:lastname,'%'),t.lastName) and " +
                    "t.fatherName ilike coalesce(concat('%',:father,'%'),t.fatherName) and " +
                    "t.username ilike  coalesce(concat('%',:username,'%'),t.username) and " +
                    "t.phoneNumber ilike coalesce(concat('%',:phone,'%'),t.phoneNumber) and " +
                    "t.deletedAt is null"
    )
    Page<Teacher> searchTeacherBasic(
            @Param(value = "id") Integer id,
            @Param(value = "firstname") String firstname,
            @Param(value = "lastname") String lastname,
            @Param(value = "father") String fatherName,
            @Param(value = "username") String username,
            @Param(value = "phone") String phone,
            Pageable pageable
    );
}
