package com.sut.se.g13.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;
import java.text.ParseException;
import java.util.Date;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AmbulanceDeletedController {

    @Autowired
    private final AmbulanceDeletedRepository ambulanceDeletedRepository;
  

    public AmbulanceDeletedController( AmbulanceDeletedRepository ambulanceDeletedRepository) {
        this.ambulanceDeletedRepository = ambulanceDeletedRepository;
    }
    
    @PostMapping("/ambulancedeleted/{employee}/{amubulance}")
    public AmbulanceDeleted newLogin(AmbulanceDeleted newAmbulanceDeleted, @PathVariable final String employee, @PathVariable Long amubulance) throws ParseException {
        
        newAmbulanceDeleted.setEmployee(employee);
        newAmbulanceDeleted.setAmbulance(amubulance);
        newAmbulanceDeleted.setDeletedtime(new Date());

        return ambulanceDeletedRepository.save(newAmbulanceDeleted);
    }
}