package com.example.case_md4.controller;

import com.example.case_md4.model.Booking;
import com.example.case_md4.model.User;
import com.example.case_md4.service.IBookingService;
import com.example.case_md4.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<Booking>> findALL(){
        List<Booking> bookingList = (List<Booking>) iBookingService.findAll();
        if(bookingList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(bookingList,HttpStatus.OK);
        }
    }
    @GetMapping("/page")
    public ResponseEntity<Page<Booking>> findAllByPage(@PageableDefault(value = 6)Pageable pageable){
            return new ResponseEntity<>(iBookingService.findAll(pageable),HttpStatus.OK);
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
    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(@RequestBody Booking booking,@PathVariable Long id){
        Optional<Booking> bookingOptional = iBookingService.findOne(id);

        if(bookingOptional.isPresent()){
            booking.setId(id);
            if(iBookingService.save(booking)!=null){
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/total/{id}")
    public ResponseEntity<Void> total(@PathVariable Long id){
        Optional<Booking> bookingOptional = iBookingService.findOne(id);
        if(bookingOptional.isPresent()){
            bookingOptional.get().setIsBill(0);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/find-by-user/{id}")
    public ResponseEntity<Page<Booking>> findByUser(@PathVariable Long id,@PageableDefault(value = 6)Pageable pageable){
        User user = userService.findOne(id);
        if(user != null){
            return new ResponseEntity<>(iBookingService.findAllByUser_Id(id,pageable),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Booking> updateIsBill(@PathVariable Long id){
        Optional<Booking> bookingOptional = iBookingService.findOne(id);

        if(bookingOptional.isPresent()){
          Booking booking =  bookingOptional.get();
          booking.setId(id);
          booking.setIsBill(0);
          iBookingService.updateIsBill(booking);
                return new ResponseEntity<>(booking,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
    }
    @GetMapping("/page-admin")
    public ResponseEntity<Page<Booking>> listAdmin(@PageableDefault(value = 6)Pageable pageable){
        return new ResponseEntity<>(iBookingService.listAdmin(pageable),HttpStatus.OK);
    }


}
