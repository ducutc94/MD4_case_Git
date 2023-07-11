package com.example.case_md4.service.impl;

import com.example.case_md4.model.User;
import com.example.case_md4.model.UserPrinciple;
import com.example.case_md4.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    public User findByUsername(String username) {
        return iUserRepository.findAllByUsername(username);
    }

    public boolean add(User user) {
        User user1 =findByUsername(user.getUsername());
        if(user1 != null){
            return false;
        }else {
            iUserRepository.save(user);
          return true;
        }
    }

    public UserDetails loadUserByUsername(String username) {
        List<User> users = iUserRepository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username)) {
                return UserPrinciple.build(user);
            }
        }
        return null;
    }

    public User findOne(Long id) {
        List<User> users = iUserRepository.findAll();
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
}