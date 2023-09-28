package com.company.training_center.repository;

import com.company.training_center.modul.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {

    Optional<Team> findByTeamIdAndDeletedAtIsNull(Integer id);

    @Query(
            value = "select * from team as t where " +
                    "t.team_id = coalesce(:id,t.team_id) and " +
                    "t.team_name ilike coalesce(concat('%',:name,'%'),t.team_name) and " +
                    "t.level ilike coalesce(concat('%',:level,'%'),t.level) and " +
                    "t.deleted_at is null",
            countQuery = "select count(*) from team",
            nativeQuery = true
    )
    Page<Team> searchByBasic(
            @Param(value = "id") Integer id,
            @Param(value = "name") String name,
            @Param(value = "level") String level,
            Pageable pageable
    );
}
