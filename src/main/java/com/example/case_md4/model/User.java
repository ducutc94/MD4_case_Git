package com.example.case_md4.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Tên bị null!")
    @Size(min = 3, max = 50, message = "Tên phải từ 3 đến 50 kí tự!")
    private String username;
    @NotEmpty(message = "Thiếu password")
    @Size(min = 8, max = 50, message = "Tên phải từ 3 đến 200 kí tự!")
    private String password;
    private String phone;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
