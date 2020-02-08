package com.sut.se.g13.Controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Collection;
import java.util.Date;

import java.util.stream.Collectors;

import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;
import java.text.ParseException;



import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmergencyController {
    @Autowired private EmergencyRepository emergencyRepository;
    @Autowired private AmbulanceRepository ambulanceRepository;
    @Autowired private DriverRegisRepository driverRegisRepository;
    @Autowired private NurseRepository nurseRepository;
    @Autowired private SymptomRepository symptomRepository;

    EmergencyController(EmergencyRepository emergencyRepository, AmbulanceRepository ambulanceRepository, DriverRegisRepository driverRegisRepository,
            NurseRepository nurseRepository, SymptomRepository symptomRepository) {
        this.emergencyRepository = emergencyRepository;
        this.ambulanceRepository = ambulanceRepository;
        this.driverRegisRepository = driverRegisRepository;
        this.nurseRepository = nurseRepository;
        this.symptomRepository = symptomRepository;
    }

    @GetMapping("/emergency")
    public Collection<Emergency> Emergencys() {
        return emergencyRepository.findAll().stream().collect(Collectors.toList());
    }
    

    @GetMapping("/symptom")
    public Collection<Symptom> Symptoms() {
        return symptomRepository.findAll().stream().collect(Collectors.toList());
    }

    @DeleteMapping("/emergency/{id}")
	public ResponseEntity<String> deleteEmergencys(@PathVariable("id") long id) {
		System.out.println("Delete Emergency with ID = " + id + "...");
		emergencyRepository.deleteById(id);
		return new ResponseEntity<>("Emergency has been deleted!", HttpStatus.OK);
	}

    @PostMapping("/emergency/{patientName}/{patientAddress}/{patientTel}/{symptomid}/{note}/{ambulanceid}/{driverregisid}/{nurseid}")
    public Emergency newEmergency(Emergency newEmergency , 
            @PathVariable String patientName ,
            @PathVariable String patientAddress , 
            @PathVariable String patientTel ,
            @PathVariable long symptomid , 
            @PathVariable String note , 
            @PathVariable long ambulanceid ,
            @PathVariable long driverregisid ,
            @PathVariable long nurseid
        ) throws ParseException {
        
        Symptom symptom = symptomRepository.findBySymptomid(symptomid);
        Ambulance ambulance = ambulanceRepository.findByAmbulanceid(ambulanceid);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(driverregisid);
        Nurse nurse = nurseRepository.findByNurseid(nurseid);
        

        newEmergency.setEmDate(new Date());
        newEmergency.setPatientName(patientName);
        newEmergency.setPatientAddress(patientAddress);
        newEmergency.setPatientTel(patientTel);
        newEmergency.setSymptom(symptom);
        newEmergency.setNote(note);
        newEmergency.setAmbulance(ambulance);
        newEmergency.setDriverRegis(driverRegis);
        newEmergency.setNurse(nurse);

        return emergencyRepository.save(newEmergency);// บันทึก Objcet ชื่อ emergency

    }
}