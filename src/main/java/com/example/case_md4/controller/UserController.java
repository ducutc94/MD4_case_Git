package com.example.case_md4.controller;

import com.example.case_md4.model.User;
import com.example.case_md4.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id){
        User user = userService.findOne(id);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Validated User user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            throw new Exception("/404");
        }
        User checkUser = userService.findOne(id);
        if(checkUser!=null){
            user.setId(id);
            user.setUsername(checkUser.getUsername());
            user.setPassword(checkUser.getPassword());
            user.setRoles(checkUser.getRoles());
            userService.add(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
        }
    }
}
