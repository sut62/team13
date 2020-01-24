package com.sut.se.g13.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.Optional;
import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;
import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DriverRegisController {

    @Autowired
    private  DriverRegisRepository driverRegisRepository;
    @Autowired
    private  EducationalRepository educationalRepository;
    @Autowired
    private  GenderRepository genderRepository;
    @Autowired
    private  PositionDriverRepository positiondriverRepository;
    @Autowired
    private  ProvinceRepository provinceRepository;


    public DriverRegisController(DriverRegisRepository driverRegisRepository,EducationalRepository educationalRepository,
    GenderRepository genderRepository,PositionDriverRepository positiondriverRepository,ProvinceRepository provinceRepository) {
        
        this.driverRegisRepository = driverRegisRepository;
        this.educationalRepository = educationalRepository;
        this.genderRepository = genderRepository;
        this.positiondriverRepository = positiondriverRepository;
        this.provinceRepository = provinceRepository;
    }

    @GetMapping("/driverRegis")
    public Collection<DriverRegis> DriverRegists() {
        return driverRegisRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/driverregis/{id}")
     public Optional<DriverRegis> DriverRegists(@PathVariable Long id) {
         Optional<DriverRegis> driverregis = driverRegisRepository.findById(id);
         return driverregis;
     }

    @GetMapping("/positiondriver")
    public Collection<PositionDriver> positiondrivers() {
        return positiondriverRepository.findAll().stream().collect(Collectors.toList());
    }

    @DeleteMapping("/driverregis/{id}")
	public ResponseEntity<String> deleteDriverRegists(@PathVariable("id") long id) {
		System.out.println("Delete DriverRegis with ID = " + id + "...");
		driverRegisRepository.deleteById(id);
		return new ResponseEntity<>("DriverRegis has been deleted!", HttpStatus.OK);
	}

    @PostMapping("/driverRegis/{date}/{drivername}/{idcard}/{address}/{telephone}/{genderid}/{positionid}/{provinceid}/{educationalid}")
    public DriverRegis newDriverRegis(DriverRegis newDriverRegis, 
            @PathVariable String date,
            @PathVariable String drivername, 
            @PathVariable String idcard, 
            @PathVariable String address,
            @PathVariable String telephone, 
            @PathVariable Long genderid, 
            @PathVariable Long positionid,
            @PathVariable Long provinceid,
            @PathVariable Long educationalid)

        throws ParseException {
        Gender gender = genderRepository.findByGenderid(genderid);
        PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(positionid);
        Province province = provinceRepository.findByProvinceid(provinceid);
        Educational educational = educationalRepository.findByEducationalid(educationalid);
       
        DateFormat newDate = new SimpleDateFormat("yyyy-MM-dd");
        Date d = newDate.parse(date); 


        newDriverRegis.setBirthday(d);
        newDriverRegis.setDrivername(drivername);
        newDriverRegis.setIdcard(idcard);
        newDriverRegis.setAddress(address);
        newDriverRegis.setTelephone(telephone);
        newDriverRegis.setGenderid(gender);
        newDriverRegis.setPositionid(positiondriver);
        newDriverRegis.setProvinceid(province);
        newDriverRegis.setEducationalid(educational);
       
        return driverRegisRepository.save(newDriverRegis);
    }

    
    

}