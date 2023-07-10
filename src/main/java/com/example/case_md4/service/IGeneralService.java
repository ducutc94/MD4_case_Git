package com.example.case_md4.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService<E> {
    Iterable<E> findAll();
    Page<E> findAll(Pageable pageable);
    Optional<E> findOne(Long id);
    E save(E e);
    void remove(Long id);
}
