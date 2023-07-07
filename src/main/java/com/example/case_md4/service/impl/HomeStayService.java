package com.example.case_md4.service.impl;

import com.example.case_md4.model.Home_Stay;
import com.example.case_md4.repository.IHomeStayRepository;
import com.example.case_md4.service.IHomeStayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
public class HomeStayService implements IHomeStayService {
    @Autowired
    private IHomeStayRepository iHomeStayRepository;
    @Value("${upload-path}")
    private String upLoad;

    @Override
    public Iterable<Home_Stay> findAll() {
        return iHomeStayRepository.findAll();
    }

    @Override
    public Optional<Home_Stay> findOne(Long id) {
        return iHomeStayRepository.findById(id);
    }

    @Override
    public Home_Stay save(Home_Stay homeStay) {
        return iHomeStayRepository.save(homeStay);
    }

    @Override
    public void remove(Long id) {
        iHomeStayRepository.deleteById(id);
    }

    @Override
    public Home_Stay create(Home_Stay homeStay, MultipartFile file) {
        String imagePart;
        try {
            if (file != null && !file.isEmpty()) {
                imagePart = file.getOriginalFilename();
                FileCopyUtils.copy(file.getBytes(), new File(upLoad + imagePart));
                homeStay.setImage(imagePart);
            } else {
                homeStay.setImage("C:\\Users\\Tien\\Desktop\\Codegym\\picture");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return iHomeStayRepository.save(homeStay);
    }

    @Override
    public Home_Stay update(Home_Stay homeStay, MultipartFile file, Long id) {
        String imagePart;
        Optional<Home_Stay> homeStayOptional = iHomeStayRepository.findById(id);
        if (homeStayOptional.isPresent()) {
            homeStay.setId(id);
            try {
                if (file != null && !file.isEmpty()) {
                    imagePart = file.getOriginalFilename();
                    FileCopyUtils.copy(file.getBytes(), new File(upLoad + imagePart));
                    homeStay.setImage(imagePart);
                } else {
                    homeStay.setImage(homeStayOptional.get().getImage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return iHomeStayRepository.save(homeStay);
    }

    @Override
    public List<Home_Stay> search(String name, Long min, Long max) {
        return iHomeStayRepository.search('%'+name+'%',min,max);
    }
}
