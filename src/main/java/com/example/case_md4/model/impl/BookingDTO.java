package com.example.case_md4.model.impl;

import java.time.LocalDate;

public class BookingDTO {
    private Long id;
    private LocalDate star_date;
    private LocalDate end_date;

    public BookingDTO(Long id, LocalDate star_date, LocalDate end_date) {
        this.id = id;
        this.star_date = star_date;
        this.end_date = end_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStar_date() {
        return star_date;
    }

    public void setStar_date(LocalDate star_date) {
        this.star_date = star_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
}
