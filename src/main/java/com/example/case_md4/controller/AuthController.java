package com.example.case_md4.controller;

import com.example.case_md4.jwt.service.JwtResponse;
import com.example.case_md4.jwt.service.JwtService;
import com.example.case_md4.model.Role;
import com.example.case_md4.model.User;
import com.example.case_md4.service.impl.RoleService;
import com.example.case_md4.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User userInfo = userService.findByUsername(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(userInfo.getId(), jwt,
                userInfo.getUsername(), userInfo.getUsername(), userDetails.getAuthorities()));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        Set<Role> roleSet = new HashSet<>();
        List<Role> roleList = (List<Role>) roleService.findAll();
        Role role = roleList.get(1);
        roleSet.add(role);
        user.setRoles(roleSet);
        boolean check = userService.add(user);
        if (check) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
