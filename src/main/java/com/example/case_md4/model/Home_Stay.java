package com.example.case_md4.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Home_Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String depict;
    private String address;
    private String image;
    private double price;
    @ManyToOne
    private User user;
    @ManyToMany
    private Set<Status> status;
    @Transient
    private MultipartFile multipartFile;
}
