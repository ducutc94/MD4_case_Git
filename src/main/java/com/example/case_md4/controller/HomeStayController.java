package com.example.case_md4.controller;

import com.example.case_md4.model.Home_Stay;
import com.example.case_md4.service.IHomeStayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/homestays")
public class HomeStayController {
    @Autowired
    private IHomeStayService iHomeStayService;
    @GetMapping
    public ResponseEntity<Iterable<Home_Stay>> findAll(){
        List<Home_Stay> homeStays = (List<Home_Stay>) iHomeStayService.findAll();
        if (homeStays.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(homeStays,HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Home_Stay>> findOne(@PathVariable Long id){
        Optional<Home_Stay> homeStayOptional = iHomeStayService.findOne(id);
        if (homeStayOptional.isPresent()){
            return new ResponseEntity<>(homeStayOptional,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Home_Stay> create(@RequestPart("file")MultipartFile multipartFile,
                                            @RequestPart("home_stay") Home_Stay homeStay){
        return new ResponseEntity<>(iHomeStayService.create(homeStay,multipartFile),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Home_Stay> update(@RequestPart("file")MultipartFile multipartFile,
                                            @RequestPart("home_stay") Home_Stay homeStay,@PathVariable Long id){
        if (iHomeStayService.findOne(id).isPresent()){
            return new ResponseEntity<>(iHomeStayService.update(homeStay,multipartFile,id),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (iHomeStayService.findOne(id).isPresent()){
            iHomeStayService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<Home_Stay>> search(@RequestParam(value = "name",required = false,defaultValue = "") String name,
                                                  @RequestParam(value = "min",required = false,defaultValue = "0") Long min,
                                                  @RequestParam(value = "max",required = false,defaultValue = "9999999999") Long max){
        return new ResponseEntity<>(iHomeStayService.search(name,min,max),HttpStatus.OK);
    }
}
