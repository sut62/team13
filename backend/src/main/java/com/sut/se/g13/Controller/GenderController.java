package com.sut.se.g13.Controller;
import com.sut.se.g13.Entity.Gender;
import com.sut.se.g13.Repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
//import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class GenderController {

    @Autowired
    private final GenderRepository genderRepository;

    public GenderController(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @GetMapping("/gender")
    public Collection<Gender> Genders() {
        return genderRepository.findAll().stream().collect(Collectors.toList());
    }
}