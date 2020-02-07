package com.sut.se.g13.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.Date;

import java.util.stream.Collectors;

import java.text.ParseException;

import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CheckEquipmentController {
    @Autowired
    private final CheckEquipmentRepository checkEquipmentRepository;
    @Autowired
    private AmbulanceRepository ambulanceRepository;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private StatusRepository statusRepository;

    CheckEquipmentController(CheckEquipmentRepository  checkEquipmentRepository) {
        this.checkEquipmentRepository = checkEquipmentRepository;
    }

    @GetMapping("/checkEquipment")
    public Collection<CheckEquipment> CheckEquipment() {
        return checkEquipmentRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/checkEquipment/{id_ambulance}/{id_nurse}/{id_status}/{note}/{defibrillator}/{sphygmomanometer}/{respirator}/{oxygenmachine}/{suction}/{motionDevice}/{medicalSprayer}")
    public CheckEquipment newCheckEquipment (CheckEquipment  newCheckEquipment ,
                               @PathVariable long id_ambulance,
                               @PathVariable long id_nurse,
                               @PathVariable long id_status,
                               @PathVariable String note,
                               @PathVariable Boolean defibrillator,
                               @PathVariable Boolean sphygmomanometer,
                               @PathVariable Boolean respirator,
                               @PathVariable Boolean oxygenmachine,
                               @PathVariable Boolean suction,
                               @PathVariable Boolean motionDevice,
                               @PathVariable Boolean medicalSprayer
    ) throws ParseException {
        //CheckEquipment newCheckEquipment = new CheckEquipment();

        Ambulance ambulance = ambulanceRepository.findByAmbulanceid(id_ambulance);
        Status status = statusRepository.findByStatusid(id_status);
        Nurse nurse = nurseRepository.findByNurseid(id_nurse);

        newCheckEquipment.setCheckDate(new Date());
        newCheckEquipment.setAmbulance(ambulance);
        newCheckEquipment.setNurse(nurse);
        newCheckEquipment.setStatus(status);
        newCheckEquipment.setNote(note);
        newCheckEquipment.setDefibrillator(defibrillator);
        newCheckEquipment.setSphygmomanometer(sphygmomanometer);
        newCheckEquipment.setRespirator(respirator);
        newCheckEquipment.setOxygenmachine(oxygenmachine);
        newCheckEquipment.setSuction(suction);
        newCheckEquipment.setMotionDevice(motionDevice);
        newCheckEquipment.setMedicalSprayer(medicalSprayer);

        return checkEquipmentRepository.save(newCheckEquipment);//บันทึก Objcet ชื่อ CheckEquipment

    }
}