package com.example.case_md4.service;

import com.example.case_md4.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService extends  IGeneralService<Booking> {
    List<Booking> findAllByHomeStay_Id(Long id);
    LocalDate minDate(List<Booking> bookingList);
    LocalDate maxDate(List<Booking> bookingList);
    Page<Booking> findAllByUser_Id(Long id,Pageable pageable);
    Page<Booking> findAll(Pageable pageable);
    int totalDate(LocalDate endDate,LocalDate startDate);
    Booking updateIsBill(Booking booking);
    Booking totalDatePrice(Booking booking);
    List<Booking> bookingList(List<Booking> bookingList);
    Page<Booking> listAdmin(Pageable pageable);
    List<Booking> listSort(List<Booking> bookingList);

}
