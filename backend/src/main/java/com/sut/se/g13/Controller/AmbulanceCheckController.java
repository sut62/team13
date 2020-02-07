package com.sut.se.g13.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;

import java.util.stream.Collectors;


import com.sut.se.g13.Entity.AmbulanceCheck;
import com.sut.se.g13.Entity.Ambulance;
import com.sut.se.g13.Entity.DriverRegis;
import com.sut.se.g13.Entity.Mile;
import com.sut.se.g13.Entity.Status;
import com.sut.se.g13.Repository.AmbulanceCheckRepository;
import com.sut.se.g13.Repository.AmbulanceRepository;
import com.sut.se.g13.Repository.DriverRegisRepository;
import com.sut.se.g13.Repository.MileRepository;
import com.sut.se.g13.Repository.StatusRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AmbulanceCheckController {
    @Autowired
    private final AmbulanceCheckRepository ambulanceCheckRepository;
    @Autowired
    private AmbulanceRepository ambulanceRepository;
    @Autowired
    private DriverRegisRepository driverRegisRepository;
    @Autowired
    private MileRepository mileRepository;
    @Autowired
    private StatusRepository statusRepository;

    AmbulanceCheckController(AmbulanceCheckRepository  ambulanceCheckRepository) {
        this.ambulanceCheckRepository = ambulanceCheckRepository;
    }

    @GetMapping("/ambulanceCheck")
    public Collection<AmbulanceCheck> AmbulanceChecks() {
        return ambulanceCheckRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/ambulanceCheck/{id_ambulance}/{id_driverRegis}/{id_mile}/{id_status}/{battery}/{light}/{wheel}/{suspension}/{brakeFluid}/{engineOil}/{radiator}/{toolSet}/{note}")
    public AmbulanceCheck newAmbulanceCheck (AmbulanceCheck  newAmbulanceCheck ,
                               @PathVariable long id_ambulance,
                               @PathVariable long id_status,
                               @PathVariable long id_driverRegis,
                               @PathVariable long id_mile,
                               @PathVariable Boolean battery,
                               @PathVariable Boolean light,
                               @PathVariable Boolean wheel,
                               @PathVariable Boolean suspension,
                               @PathVariable Boolean brakeFluid,
                               @PathVariable Boolean engineOil,
                               @PathVariable Boolean radiator,
                               @PathVariable Boolean toolSet,
                               @PathVariable String note
    ) {
        //AmbulanceCheck newAmbulanceCheck = new AmbulanceCheck();

        Ambulance ambulance = ambulanceRepository.findByAmbulanceid(id_ambulance);
        Status status = statusRepository.findByStatusid(id_status);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(id_driverRegis);
        Mile mile = mileRepository.findByMileid(id_mile);

        newAmbulanceCheck.setCheckDate(new Date());
        newAmbulanceCheck.setDriverRegis(driverRegis);
        newAmbulanceCheck.setAmbulance(ambulance);
        newAmbulanceCheck.setMile(mile);
        newAmbulanceCheck.setStatus(status);
        newAmbulanceCheck.setBattery(battery);
        newAmbulanceCheck.setLight(light);
        newAmbulanceCheck.setWheel(wheel);
        newAmbulanceCheck.setSuspension(suspension);
        newAmbulanceCheck.setBrakeFluid(brakeFluid);
        newAmbulanceCheck.setEngineOil(engineOil);
        newAmbulanceCheck.setRadiator(radiator);
        newAmbulanceCheck.setToolSet(toolSet);
        newAmbulanceCheck.setNote(note);

        return ambulanceCheckRepository.save(newAmbulanceCheck);//บันทึก Objcet ชื่อ AmbulanceCheck

    }
}