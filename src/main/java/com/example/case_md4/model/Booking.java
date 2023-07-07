package com.example.case_md4.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate star_date;
    private LocalDate end_date;
    private int isBill =1;
    @Transient
    private int total_day;
    @Transient
    private double total_price;
    @ManyToOne
    private Home_Stay homeStay;
    @ManyToOne
    private User user;
    @ManyToOne
    private Action action;

}
