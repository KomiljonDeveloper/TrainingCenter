package com.company.training_center.repository;

import com.company.training_center.modul.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    Optional<Payment> findByIdAndDeletedAtIsNull(Integer integer);

    @Query(
            value = "select * from payment as p where " +
                    "p.id = coalesce(:id,p.id) and " +
                    "p.month ilike coalesce(concat('%',:month,'%'),p.month) and " +
                    "p.amount = coalesce(:amount,p.amount) and " +
                    "p.deleted_at is null ",

            nativeQuery = true
    )
    Page<Payment> searchByPayment(
            @Param(value = "id") Integer id,
            @Param(value = "month") String month,
            @Param(value = "amount") Double amount,
            Pageable pageable
    );
}
