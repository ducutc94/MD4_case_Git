package com.example.case_md4.repository;

import com.example.case_md4.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByHomeStay_Id(Long id);
    List<Booking> findAllByUser_Id(Long id);
    @Query(value = "select datediff(:end_date,:star_date)",nativeQuery = true)
    int totalDay(@Param("end_date") LocalDate end_date,@Param("star_date") LocalDate star_date);
}
