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
public class NurseDeletedController {

    @Autowired
    private final NurseDeletedRepository nursedeletedRepository;
  

    public NurseDeletedController( NurseDeletedRepository nursedeletedRepository) {
        this.nursedeletedRepository = nursedeletedRepository;
    }
    
    @PostMapping("/nursedeleted/{employee}/{nurse}")
    public NurseDeleted newLogin(NurseDeleted newNurseDeleted, @PathVariable final String employee, @PathVariable Long nurse) throws ParseException {
        
        newNurseDeleted.setEmployee(employee);
        newNurseDeleted.setNursedeletedid(nurse);
        newNurseDeleted.setNursedeletedtime(new Date());

        return nursedeletedRepository.save(newNurseDeleted);
    }
}