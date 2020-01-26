package com.sut.se.g13.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

import java.util.stream.Collectors;

import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProvinceController {
    @Autowired
    private ProvinceRepository provinceRepository;

    public ProvinceController(
            ProvinceRepository provinceRepository) {
        
        this.provinceRepository = provinceRepository;
    }

    @GetMapping("/province")
    public Collection<Province> provinces() {
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }

}