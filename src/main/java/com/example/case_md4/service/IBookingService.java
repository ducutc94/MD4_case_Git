package com.example.case_md4.service;

import com.example.case_md4.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService extends  IGeneralService<Booking> {
    List<Booking> findAllByHomeStay_Id(Long id);
    LocalDate minDate(List<Booking> bookingList);
    LocalDate maxDate(List<Booking> bookingList);
    List<Booking> findAllByUser_Id(Long id);
}
