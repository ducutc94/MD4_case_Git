package com.example.case_md4.service.impl;

import com.example.case_md4.model.Booking;
import com.example.case_md4.model.Status;
import com.example.case_md4.repository.IBookingRepository;
import com.example.case_md4.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private IBookingRepository iBookingRepository;

    @Override
    public Iterable<Booking> findAll() {
        return iBookingRepository.findAll();
    }

    @Override
    public Optional<Booking> findOne(Long id) {
        return iBookingRepository.findById(id);
    }

    @Override
    public Booking save(Booking booking) {
        LocalDate minDate;
        LocalDate maxDate;
        List<Booking> bookingList = (List<Booking>) findAll();
        List<Booking> listBookingById = findAllByHomeStay_Id(booking.getHomeStay().getId());
        minDate = minDate(listBookingById);
        maxDate = maxDate(listBookingById);
        if(maxDate !=null && minDate != null){
            for (Booking b : bookingList) {
                if (b.getHomeStay().getId() == booking.getHomeStay().getId()) {
                    if (booking.getStar_date().isBefore(booking.getEnd_date())) {
                        if (booking.getEnd_date().isBefore(minDate) || booking.getStar_date().isAfter(maxDate)) {
                       return   iBookingRepository.save(booking);
                        }else {
                            return null;
                        }

                    }else {
                        return null;
                    }

                }
            }
        }else if(booking.getStar_date().isBefore(booking.getEnd_date())){
            return iBookingRepository.save(booking);
        }else {
            return null;
        }
        return null;

    }

    @Override
    public void remove(Long id) {
        Optional<Booking> bookingOptional = findOne(id);
        if (bookingOptional.isPresent()) {
            iBookingRepository.deleteById(id);
        }
    }

    @Override
    public List<Booking> findAllByHomeStay_Id(Long id) {
        return iBookingRepository.findAllByHomeStay_Id(id);
    }

    @Override
    public LocalDate minDate(List<Booking> bookingList) {
        LocalDate minDate = null;
        if(!bookingList.isEmpty()){
            minDate = bookingList.get(0).getStar_date();
            for (int i = 1; i < bookingList.size(); i++) {
                if (bookingList.get(i).getStar_date().isBefore(minDate)) {
                    minDate = bookingList.get(i).getStar_date();
                }
            }
        }
        else {
            return null;
        }

        return minDate;
    }

    @Override
    public LocalDate maxDate(List<Booking> bookingList) {
        LocalDate maxDate =null;
        if(!bookingList.isEmpty()){
            maxDate = bookingList.get(0).getEnd_date();
            for (int i = 1; i < bookingList.size(); i++) {
                if (bookingList.get(i).getStar_date().isAfter(maxDate)) {
                    maxDate = bookingList.get(i).getStar_date();
                }
            }
        }else {
            return  null;
        }

        return maxDate;
    }

}
