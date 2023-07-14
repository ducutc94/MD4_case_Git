package com.example.case_md4.service.impl;

import com.example.case_md4.model.Booking;
import com.example.case_md4.model.Home_Stay;
import com.example.case_md4.repository.IBookingRepository;
import com.example.case_md4.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private IBookingRepository iBookingRepository;
    @Autowired
    private HomeStayService homeStayService;

    @Override
    public Iterable<Booking> findAll() {
        List<Booking> bookingList = bookingList(iBookingRepository.findAll());
        if (bookingList.isEmpty()) {
            return null;
        } else {
            return bookingList;
        }

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
        listBookingById.sort(Comparator.comparing(o->o.getStar_date()));
        minDate = minDate(listBookingById);
        maxDate = maxDate(listBookingById);

        if (maxDate != null && minDate != null) {
            if(booking.getStar_date().isBefore(booking.getEnd_date())){
                if(listBookingById.size()<=1){
                    if(booking.getStar_date().isAfter(maxDate) || booking.getEnd_date().isBefore(maxDate)){
                        return iBookingRepository.save(booking);
                    }
                }else {
                    for (int i = 0; i < listBookingById.size(); i++) {
                           if(booking.getEnd_date().isBefore(listBookingById.get(i+1).getStar_date())&&
                            booking.getStar_date().isAfter(listBookingById.get(0).getEnd_date())){
                               return iBookingRepository.save(booking);
                           }
                       }return null;
                }

            }else {
                return null;
            }
//            for (Booking b : bookingList) {
//                if (b.getHomeStay().getId() == booking.getHomeStay().getId()) {
//                        for (int i = 0; i < listBookingById.size(); i++) {
//                            if(booking.getEnd_date().isBefore(listBookingById.get(i+1).getStar_date())&&
//                            booking.getStar_date().isAfter(listBookingById.get(0).getEnd_date())){
//                                return iBookingRepository.save(booking);
//                            }
//                        }return null;
//
//                } else if (booking.getStar_date().isBefore(booking.getEnd_date())) {
//                    return iBookingRepository.save(booking);
//                }
//            }
        }
        else if (booking.getStar_date().isBefore(booking.getEnd_date())) {
            return iBookingRepository.save(booking);
        } else {
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
        if (!bookingList.isEmpty()) {
            minDate = bookingList.get(0).getStar_date();
            for (int i = 1; i < bookingList.size(); i++) {
                if (bookingList.get(i).getStar_date().isBefore(minDate)) {
                    minDate = bookingList.get(i).getStar_date();
                }
            }
        } else {
            return null;
        }

        return minDate;
    }

    @Override
    public LocalDate maxDate(List<Booking> bookingList) {
        LocalDate maxDate = null;
        if (!bookingList.isEmpty()) {
            maxDate = bookingList.get(0).getEnd_date();
            for (int i = 1; i < bookingList.size(); i++) {
                if (bookingList.get(i).getStar_date().isAfter(maxDate)) {
                    maxDate = bookingList.get(i).getStar_date();
                }
            }
        } else {
            return null;
        }
        return maxDate;
    }

    @Override
    public Page<Booking> findAllByUser_Id(Long id, Pageable pageable) {
        return iBookingRepository.findAllByUser_Id(id, pageable);
    }

    @Override
    public Page<Booking> findAll(Pageable pageable) {
        return iBookingRepository.findAll(pageable);
    }

    @Override
    public int totalDate(LocalDate endDate, LocalDate startDate) {
        return iBookingRepository.totalDate(endDate, startDate);
    }


    @Override
    public Booking updateIsBill(Booking booking) {
        return iBookingRepository.save(booking);
    }

    @Override
    public Booking totalDatePrice(Booking booking) {
        int totalDate = totalDate(booking.getEnd_date(), booking.getStar_date());
        Home_Stay homeStay = homeStayService.findOne(booking.getHomeStay().getId()).get();
        double totalPrice = totalDate * homeStay.getPrice();
        booking.setTotal_price(totalPrice);
        booking.setTotal_day(totalDate);
        return booking;
    }

    @Override
    public List<Booking> bookingList(List<Booking> bookingList) {
        List<Booking> bookingList1AfterTotal = new ArrayList<>();
        for (Booking b : bookingList) {
            bookingList1AfterTotal.add(totalDatePrice(b));
        }
        return bookingList1AfterTotal;
    }

    @Override
    public Page<Booking> listAdmin(Pageable pageable) {
        return iBookingRepository.findAllBookingPage(pageable);
    }

    @Override
    public List<Booking> listSort(List<Booking> bookingList) {

        return null;
    }

    @Override
    public Booking update(Booking booking) {
        return iBookingRepository.save(booking);
    }

}
