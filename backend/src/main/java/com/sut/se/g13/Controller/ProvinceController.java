package com.sut.se.g13.Controller;
import com.sut.se.g13.Entity.Province;
import com.sut.se.g13.Repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
//import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProvinceController {

    @Autowired
    private final ProvinceRepository provinceRepository;

    public ProvinceController(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @GetMapping("/province")
    public Collection<Province> provinces() {
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }
}