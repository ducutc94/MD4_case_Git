package com.example.case_md4.model.DTO;

import com.example.case_md4.model.Action;
import com.example.case_md4.model.Home_Stay;
import com.example.case_md4.model.User;

import javax.persistence.*;
import java.time.LocalDate;

public class BookingDTO {
    private Long id;
    private LocalDate star_date;
    private LocalDate end_date;

    private int total_day;
    private double total_price;
    private Home_Stay homeStay;
    private User user;
    private Action action;
}
