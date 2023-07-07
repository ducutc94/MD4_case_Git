package com.example.case_md4.service;

import com.example.case_md4.model.Home_Stay;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHomeStayService extends IGeneralService<Home_Stay> {
    Home_Stay create(Home_Stay homeStay, MultipartFile file);
    Home_Stay update(Home_Stay homeStay, MultipartFile file,Long id);
    List<Home_Stay> search(String name, Long min, Long max);
}
