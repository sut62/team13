package com.sut.se.g13.Controller;
import com.sut.se.g13.Entity.Educational;
import com.sut.se.g13.Repository.EducationalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
//import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EducationalController {

    @Autowired
    private final EducationalRepository educationalRepository;

    public EducationalController(EducationalRepository educationalRepository) {
        this.educationalRepository = educationalRepository;
    }

    @GetMapping("/educational")
    public Collection<Educational> educationals() {
        return educationalRepository.findAll().stream().collect(Collectors.toList());
    }
}