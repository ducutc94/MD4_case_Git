package com.example.case_md4.repository;

import com.example.case_md4.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByHomeStay_Id(Long id);
}
