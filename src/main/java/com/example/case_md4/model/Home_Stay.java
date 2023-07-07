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
    @Column(name = "status")
    private int status = 0;
    @ManyToOne
    private User user;
    @Transient
    private MultipartFile multipartFile;
}
