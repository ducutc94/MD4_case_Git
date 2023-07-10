package com.example.case_md4.repository;

import com.example.case_md4.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByHomeStay_Id(Long id);
    Page<Booking> findAllByUser_Id(Long id,Pageable pageable);
    Page<Booking> findAll(Pageable pageable);

    @Query(value = "select datediff(:endDate,:startDate)",nativeQuery = true)
    int totalDate(@Param("endDate")LocalDate endDate,
                  @Param("startDate")LocalDate startDate);
}
