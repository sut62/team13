package com.sut.se.g13.Controller;
import com.sut.se.g13.Entity.Status;
import com.sut.se.g13.Repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class StatusController {

    @Autowired
    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping("/status")
    public Collection<Status> Statuses() {
        return statusRepository.findAll().stream().collect(Collectors.toList());
    }
}