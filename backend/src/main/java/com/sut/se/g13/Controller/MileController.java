package com.sut.se.g13.Controller;
import com.sut.se.g13.Entity.Mile;
import com.sut.se.g13.Repository.MileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;

import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MileController {

    @Autowired
    private final MileRepository mileRepository;

    public MileController(MileRepository mileRepository) {
        this.mileRepository = mileRepository;
    }

    @GetMapping("/mile")
    public Collection<Mile> Miles() {
        return mileRepository.findAll().stream().collect(Collectors.toList());
    }
}