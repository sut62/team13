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
import java.util.stream.Collectors;
import java.util.Optional;
import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;
import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AmbulanceController {

    @Autowired
    private AmbulanceRepository ambulanceRepository;
    @Autowired
    private AmbulanceBrandRepository ambulancebrandRepository;
    @Autowired
    private AmbulanceTypeRepository ambulancetypeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    public AmbulanceController(AmbulanceRepository ambulanceRepository,
            AmbulanceBrandRepository ambulancebrandRepository, AmbulanceTypeRepository ambulancetypeRepository,
            ProvinceRepository provinceRepository) {
        this.ambulanceRepository = ambulanceRepository;
        this.ambulancebrandRepository = ambulancebrandRepository;
        this.ambulancetypeRepository = ambulancetypeRepository;
        this.provinceRepository = provinceRepository;
    }

    @GetMapping("/ambulance")
    public Collection<Ambulance> Ambulances() {
        return ambulanceRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/ambulance/{id}")
    public Optional<Ambulance> Ambulances(@PathVariable Long id) {
        Optional<Ambulance> ambulance = ambulanceRepository.findById(id);
        return ambulance;
    }

    @GetMapping("/ambulancebrand")
    public Collection<AmbulanceBrand> AmbulanceBrands() {
        return ambulancebrandRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/ambulancetype")
    public Collection<AmbulanceType> AmbulanceTypes() {
        return ambulancetypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/province")
    public Collection<Province> province() {
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/ambulance/{bradid}/{ambulancemodel}/{typeid}/{ambulancenum}/{enginenum}/{licenseplate}/{provinceid}")
    public Ambulance newAmbulance(Ambulance newAmbulance, @PathVariable long bradid,
            @PathVariable String ambulancemodel, @PathVariable long typeid, @PathVariable String ambulancenum,
            @PathVariable String enginenum, @PathVariable String licenseplate, @PathVariable long provinceid)
            throws ParseException {

        AmbulanceBrand brand = ambulancebrandRepository.findByBrandid(bradid);
        AmbulanceType type = ambulancetypeRepository.findByTypeid(typeid);
        Province province = provinceRepository.findByProvinceid(provinceid);

        newAmbulance.setBrandid(brand);
        newAmbulance.setTypeid(type);
        newAmbulance.setProvinceid(province);
        newAmbulance.setAmbulancemodel(ambulancemodel);
        newAmbulance.setAmbulancenum(ambulancenum);
        newAmbulance.setEnginenum(enginenum);
        newAmbulance.setLicenseplate(licenseplate);

        return ambulanceRepository.save(newAmbulance);
    }
}