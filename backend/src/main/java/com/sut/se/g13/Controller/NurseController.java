package com.sut.se.g13.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;
import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class NurseController {

    @Autowired
    private  NurseRepository nurseRepository;
    @Autowired
    private  TierRepository tierRepository;
    @Autowired
    private  GenderRepository genderRepository;
    @Autowired
    private  EducationalRepository educationalRepository;
    @Autowired
    private  ProvinceRepository provinceRepository;


    public NurseController(NurseRepository nurseRepository,TierRepository tierRepository,
    GenderRepository genderRepository,EducationalRepository educationalRepository,
    ProvinceRepository provinceRepository) {
        
        this.tierRepository = tierRepository;
        this.genderRepository = genderRepository;
        this.educationalRepository = educationalRepository;
        this.provinceRepository = provinceRepository;
    }

    @GetMapping("/nurse")
    public Collection<Nurse> Nurses() {
        return nurseRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/tier")
    public Collection<Tier> Tiers() {
        return tierRepository.findAll().stream().collect(Collectors.toList());
    }

    @DeleteMapping("nurse/{id}")
    public ResponseEntity<String> deleteNurses(@PathVariable("id") long id) {
		System.out.println("Delete Nurse with ID = " + id + "...");
		nurseRepository.deleteById(id);
        return new ResponseEntity<>("Nurse has been deleted!", HttpStatus.OK);
    }

    @PostMapping("/nurse/{nursename}/{genderid}/{date}/{address}/{provinceid}/{educationalid}/{tierid}/{telephone}/{email}")
    public Nurse newNurse(Nurse newNurse,
        @PathVariable String nursename,
        @PathVariable Long genderid,
        @PathVariable String date,
        @PathVariable String address,
        @PathVariable Long provinceid,
        @PathVariable Long educationalid,
        @PathVariable Long tierid,
        @PathVariable String telephone,
        @PathVariable String email) 
        throws ParseException {

        Gender gender = genderRepository.findByGenderid(genderid);
        Province province = provinceRepository.findByProvinceid(provinceid);
        Educational educational = educationalRepository.findByEducationalid(educationalid);
        Tier tier = tierRepository.findByTierid(tierid);
       
        DateFormat newDate = new SimpleDateFormat("yyyy-MM-dd");
        Date d = newDate.parse(date); 
       
        newNurse.setNursename(nursename);
        newNurse.setGenderid(gender);
        newNurse.setBirthday(d);
        newNurse.setAddress(address);
        newNurse.setProvinceid(province);
        newNurse.setEducationalid(educational);
        newNurse.setTierid(tier);
        newNurse.setTelephone(telephone);
        newNurse.setEmail(email);
        newNurse.setNowdate(new Date());

        return nurseRepository.save(newNurse);
    }

    
    

}