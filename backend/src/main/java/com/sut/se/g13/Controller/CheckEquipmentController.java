package com.sut.se.g13.Controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

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

    @GetMapping("/ambulance")
    public Collection<Ambulance> Ambulances() {
        return ambulanceRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/status")
    public Collection<Status> Statuses() {
        return statusRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/Nurse")
    public Collection<Nurse> Nurses() {
        return nurseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/checkEquipment/{id_ambulance}/{id_nurse}/{id_status}/{defibrillator}/{sphygmomanometer}/{respirator}/{oxygenmachine}/{suction}/{motionDevice}/{medicalSprayer}")
    public CheckEquipment newCheckEquipment (CheckEquipment  newCheckEquipment ,
                               @PathVariable long id_ambulance,
                               @PathVariable long id_nurse,
                               @PathVariable long id_status,
                               @PathVariable Boolean defibrillator,
                               @PathVariable Boolean sphygmomanometer,
                               @PathVariable Boolean respirator,
                               @PathVariable Boolean oxygenmachine,
                               @PathVariable Boolean suction,
                               @PathVariable Boolean motionDevice,
                               @PathVariable Boolean medicalSprayer
    ) {
        //CheckEquipment newCheckEquipment = new CheckEquipment();

        Ambulance ambulance = ambulanceRepository.findById(id_ambulance);
        Status status = statusRepository.findById(id_status);
        Nurse nurse = nurseRepository.findById(id_nurse);

        newCheckEquipment.setCheckDate(new Date());
        newCheckEquipment.setAmbulance(ambulance);
        newCheckEquipment.setNursename(nurse);
        newCheckEquipment.setStatus(status);
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