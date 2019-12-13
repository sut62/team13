package com.sut.se.g13.Controller;
import com.sut.se.g13.Entity.DriverRegis;
import com.sut.se.g13.Repository.DriverRegisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DriverRegisController {

    @Autowired
    private final DriverRegisRepository driverRegisRepository;

    public DriverRegisController(DriverRegisRepository driverRegisRepository) {
        this.driverRegisRepository = driverRegisRepository;
    }

    @GetMapping("/driverRegis")
    public Collection<DriverRegis> DriverRegises() {
        return driverRegisRepository.findAll().stream().collect(Collectors.toList());
    }

}