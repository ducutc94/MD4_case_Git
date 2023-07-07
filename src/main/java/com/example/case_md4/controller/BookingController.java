package com.example.case_md4.controller;

import com.example.case_md4.model.Booking;
import com.example.case_md4.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private IBookingService iBookingService;
    @GetMapping
    public ResponseEntity<Iterable<Booking>> findAll(){
        List<Booking> bookingList = (List<Booking>) iBookingService.findAll();
        if(bookingList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(bookingList,HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Booking>> findOne(@PathVariable("id") Long id){
        Optional<Booking> bookingOptional = iBookingService.findOne(id);
        if(bookingOptional.isPresent()){
            return new ResponseEntity<>(bookingOptional,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping
    public ResponseEntity<Booking> save(@RequestBody Booking booking){
        Booking booking1 =iBookingService.save(booking);
        if(booking1 !=null){
            return new ResponseEntity<>(booking1,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        iBookingService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
