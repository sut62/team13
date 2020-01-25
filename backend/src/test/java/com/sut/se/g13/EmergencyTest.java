package com.sut.se.g13;

import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//ถ้าจะเช็คทั้งหมด รวมcomboboxที่ดึงเพื่อนมาใช้ด้วย ต้องTestทีละอัน (อย่าลืมเปิด @NotNull ใน Entity)
@DataJpaTest
public class EmergencyTest {
    private Validator validator;

    @Autowired private EmergencyRepository emergencyRepository;
    @Autowired private SymptomRepository symptomRepository;
    @Autowired private AmbulanceRepository ambulanceRepository;
    @Autowired private AmbulanceBrandRepository ambulanceBrandRepository;
    @Autowired private AmbulanceTypeRepository ambulanceTypeRepository;
    @Autowired private ProvinceRepository provinceRepository;
    @Autowired private  DriverRegisRepository driverRegisRepository;
    @Autowired private  EducationalRepository educationalRepository;
    @Autowired private  GenderRepository genderRepository;
    @Autowired private  PositionDriverRepository positiondriverRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    //testEmergencywithCorrect แบบ  Correct ใส่ข้อมูลถูกต้องปกติ
    void B5814985_testEmergencywithCorrect() {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1l);
        java.util.Date date = new java.util.Date(2020-01-21);
    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1l);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1l);
    //     Province province = provinceRepository.findByProvinceid(1l); 

    //     Gender gender = genderRepository.findByGenderid(1l);
    //     Educational educational = educationalRepository.findByEducationalid(1l);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1l);

    // Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1l);

    // DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1l);

       emergency.setEmDate(date);
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        // emergency.setAmbulance(am);
        // emergency.setDriverRegis(dr);


        emergencyRepository.saveAndFlush(emergency);
        Optional<Emergency> check = emergencyRepository.findById(emergency.getEmergencyId());
        assertEquals("Chayanon Piakhuntod", check.get().getPatientName());
        assertEquals("123 ถ.เมือง อ.เมือง จ.เมือง", check.get().getPatientAddress());
        assertEquals("0902345678", check.get().getPatientTel());
        assertEquals("เป็นหวัด", check.get().getNote());
        assertEquals(symptom, check.get().getSymptom());
        // assertEquals(am, check.get().getAmbulance());
        // assertEquals(dr, check.get().getDriverRegis());
    }
    @Test
    //ป้องกันการใส่ค่า null ของ EmDate 
    //testEmergencywithEmDateNotNull
    void B5814985_testEmergencywithEmDateNotNull() { 
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        java.util.Date date = new java.util.Date(2020-01-21);

        
    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
    //     Province province = provinceRepository.findByProvinceid(1); 
    
    //     Gender gender = genderRepository.findByGenderid(1);
    //     Educational educational = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);


    // Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    // DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

        emergency.setEmDate(null);
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        // emergency.setAmbulance(am);
        // emergency.setDriverRegis(dr);


        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("emDate", message.getPropertyPath().toString());
    }

    @Test
    //ป้องกันการใส่ค่า null ของ PatientName 
    //testEmergencywithPatientNameNotNull
    void B5814985_testEmergencywithPatientNameNotNull() {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
    //     Province province = provinceRepository.findByProvinceid(1); 

    //     Gender gender = genderRepository.findByGenderid(1);
    //     Educational educational = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    //     Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    //     DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = this.driverRegisRepository.findByDriverregisid(1);

       emergency.setEmDate(date);
        emergency.setPatientName(null);
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        // emergency.setAmbulance(am);
        // emergency.setDriverRegis(dr);


        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("patientName", message.getPropertyPath().toString());
    }

    @Test
    //ป้องกันการใส่ค่า null ของ PatientAddress
    //testEmergencywithPatientAddressNotNull
    void B5814985_testEmergencywithPatientAddressNotNull() {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
    //     Province province = provinceRepository.findByProvinceid(1); 

    //     Gender gender = genderRepository.findByGenderid(1);
    //     Educational educational = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    //     Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    //     DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);
       
       emergency.setEmDate(date);
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress(null);
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        // emergency.setAmbulance(am);
        // emergency.setDriverRegis(dr);


        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("patientAddress", message.getPropertyPath().toString());
    }

    @Test
    //ป้องกันการใส่ค่า null ของ PatientTel
    //testEmergencywithPatientAddressNotNull
    void B5814985_testEmergencywithPatientTelNotNull() {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
    //     Province province = provinceRepository.findByProvinceid(1); 

    //     Gender gender = genderRepository.findByGenderid(1);
    //     Educational educational = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    //     Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    //     DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

       emergency.setEmDate(date);
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel(null);
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        // emergency.setAmbulance(am);
        // emergency.setDriverRegis(dr);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("patientTel", message.getPropertyPath().toString());
    }

    @Test
    //ป้องกันการใส่ค่า null ของ Note
    //testEmergencywithNoteNotNull
    void B5814985_testEmergencywithNoteNotNull() {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
    //     Province province = provinceRepository.findByProvinceid(1); 

    //     Gender gender = genderRepository.findByGenderid(1);
    //     Educational educational = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    //     Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    //     DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);
       
       emergency.setEmDate(date);
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("0902345678");
        emergency.setNote(null);
        emergency.setSymptom(symptom);
        // emergency.setAmbulance(am);
        // emergency.setDriverRegis(dr);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("note", message.getPropertyPath().toString());
    }
    @Test
    //ป้องกันการใส่ค่า null ของ Symptom
    //testEmergencywithSymptomNotNull
    void B5814985_testEmergencywithSymptomNotNull() {
        Emergency emergency = new Emergency();

        java.util.Date date = new java.util.Date(2020-01-21);
    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
    //     Province province = provinceRepository.findByProvinceid(1); 

    //     Gender gender = genderRepository.findByGenderid(1);
    //     Educational educational = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    //     Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    //     DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);
       
       emergency.setEmDate(date);
       emergency.setPatientName("Chayanon Piakhuntod");
       emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
       emergency.setPatientTel("0902345678");
       emergency.setNote("เป็นหวัด");
       emergency.setSymptom(null);
    //    emergency.setAmbulance(am);
    //    emergency.setDriverRegis(dr);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("symptom", message.getPropertyPath().toString());
    }

    // @Test
    // //ป้องกันการใส่ค่า null ของ Ambulance
    // //testEmergencywithAddAmbulanceNotNull
    // void B5814985_testEmergencywithAddAmbulanceNotNull() {
    //     Emergency emergency = new Emergency();
    //     Symptom symptom = symptomRepository.findBySymptomid(1);
    //     java.util.Date date = new java.util.Date(2020-01-21);

    // //     Province province = provinceRepository.findByProvinceid(1); 

    // //     Gender gender = genderRepository.findByGenderid(1);
    // //     Educational educational = educationalRepository.findByEducationalid(1);
    // //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    // //     DriverRegis driverRegis = new DriverRegis();
    // //    driverRegis.setDrivername("BennyBenBen");
    // //    driverRegis.setIdcard("1349900799556");
    // //    driverRegis.setTelephone("0927378868");
    // //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    // //    driverRegis.setGenderid(gender);
    // //    driverRegis.setProvinceid(province);
    // //    driverRegis.setEducationalid(educational);
    // //    driverRegis.setPositionid(positiondriver);
    // //    driverRegisRepository.save(driverRegis);
    // //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);
       
    //    emergency.setEmDate(date);
    //    emergency.setPatientName("Chayanon Piakhuntod");
    //    emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
    //    emergency.setPatientTel("0902345678");
    //    emergency.setNote("เป็นหวัด");
    //    emergency.setSymptom(symptom);
    // //    emergency.setAmbulance(null);
    // //    emergency.setDriverRegis(dr);

    //     Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

    //     assertEquals(1, result.size());

    //     ConstraintViolation<Emergency> message = result.iterator().next();
    //     assertEquals("must not be null", message.getMessage());
    //     assertEquals("ambulance", message.getPropertyPath().toString());
    // }
    // @Test
    //  //ป้องกันการใส่ค่า null ของ DriverRegis
    // //testEmergencywithAddDriverRegisNotNull
    // void B5814985_testEmergencywithAddDriverRegisNotNull() {
    //     Emergency emergency = new Emergency();
    //     Symptom symptom = symptomRepository.findBySymptomid(1);

    //     java.util.Date date = new java.util.Date(2020-01-21);
    //     // AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     // AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
    //     // Province province = provinceRepository.findByProvinceid(1); 

    //     // Ambulance ambulance = new Ambulance();
    //     // ambulance.setEnginenum("JS150MM03731");
    //     // ambulance.setAmbulancemodel("Dmax");
    //     // ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     // ambulance.setLicenseplate("1กว 4451");
    //     // ambulance.setAdddate(date);
    //     // ambulance.setBrandid(brand);
    //     // ambulance.setTypeid(type);
    //     // ambulance.setProvinceid(province);
    //     // ambulanceRepository.save(ambulance);
    //     // Ambulance am = ambulanceRepository.findByAmbulanceid(1);
       
    //     emergency.setEmDate(date);
    //    emergency.setPatientName("Chayanon Piakhuntod");
    //    emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
    //    emergency.setPatientTel("0902345678");
    //    emergency.setNote("เป็นหวัด");
    //    emergency.setSymptom(symptom);
    // //    emergency.setAmbulance(am);
    // //    emergency.setDriverRegis(null);

    //     Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

    //     assertEquals(1, result.size());

    //     ConstraintViolation<Emergency> message = result.iterator().next();
    //     assertEquals("must not be null", message.getMessage());
    //     assertEquals("driverRegis", message.getPropertyPath().toString());
    // }

    @Test
     //เช็คชื่อคนไข้ ห้ามมากกว่า 30 อักษร
    //testPatientNameNotMoreThan30String
    void B5814985_testPatientNameNotMoreThan30String() {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
    //     Province province = provinceRepository.findByProvinceid(1); 

    //     Gender gender = genderRepository.findByGenderid(1);
    //     Educational educational = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    //     Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    //     DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

       emergency.setEmDate(date);
        emergency.setPatientName("Chayanon PiakhuntodXXXHardUnderBlade");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        // emergency.setAmbulance(am);
        // emergency.setDriverRegis(dr);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Emergency> v = result.iterator().next();
        assertEquals("size must be between 0 and 30", v.getMessage());
        assertEquals("patientName", v.getPropertyPath().toString());
    }

    @Test
     //เช็คที่อยู่คนไข้ ห้ามมากกว่า 50 อักษร
    //testPatientAddressNotMoreThan50String
    void B5814985_testPatientAddressNotMoreThan50String() {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
    //     Province province = provinceRepository.findByProvinceid(1); 

    //     Gender gender = genderRepository.findByGenderid(1);
    //     Educational educational = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    //     Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    //     DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

       emergency.setEmDate(date);
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("445 ม.17 หมู่บ้านโสภณ 4 ต.แสนสุข อ.วารินชำราบ 34190");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        // emergency.setAmbulance(am);
        // emergency.setDriverRegis(dr);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Emergency> v = result.iterator().next();
        assertEquals("size must be between 0 and 50", v.getMessage());
        assertEquals("patientAddress", v.getPropertyPath().toString());
    }

    @Test
    //เช็คเบอร์โทรคนไข้ ห้ามมากกว่า 10 อักษร
    //testPatientTelmustbe10String
    void B5814985_testPatientTelmustbe10String() {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
    //     Province province = provinceRepository.findByProvinceid(1); 

    //     Gender gender = genderRepository.findByGenderid(1);
    //     Educational educational = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    //     Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    //     DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

       emergency.setEmDate(date);
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("09273788688");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        // emergency.setAmbulance(am);
        // emergency.setDriverRegis(dr);


        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Emergency> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("patientTel", v.getPropertyPath().toString());
    }

    @Test
    //เช็คหมายเหตุคนไข้ ห้ามมากกว่า 30 อักษร
    //testPatientTelmustbe30String
    void B5814985_testNoteNotMoreThan30String() {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
        // AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        // AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        // Province province = provinceRepository.findByProvinceid(1); 

        // Gender gender = genderRepository.findByGenderid(1);
        // Educational educational = educationalRepository.findByEducationalid(1);
        // PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    //     Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    //     DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

       emergency.setEmDate(date);
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด หัวร้อน ปวดหัว อยากตาย อกหัก");
        emergency.setSymptom(symptom);
        // emergency.setAmbulance(am);
        // emergency.setDriverRegis(dr);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Emergency> v = result.iterator().next();
        assertEquals("size must be between 0 and 30", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

   @Test 
   //เช็คเบอร์โทรศัำท์ไม่ซำ้กัน
   //testPatientTelBeUnique
    void B5814985_testPatientTelBeUnique() throws ParseException{
    // สร้าง Emergency object
        Emergency emergency1 = new Emergency();
        Symptom symptom1 = symptomRepository.findBySymptomid(1);

        java.util.Date date1 = new java.util.Date(2020-01-21);
    //     AmbulanceBrand brand1 = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type1 = ambulanceTypeRepository.findByTypeid(1);
    //     Province province1 = provinceRepository.findByProvinceid(1); 

    //     Gender gender1 = genderRepository.findByGenderid(1);
    //     Educational educational1 = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver1 = positiondriverRepository.findByPositionDriverid(1);

    //     Ambulance ambulance1 = new Ambulance();
    //     ambulance1.setEnginenum("JS150MM03731");
    //     ambulance1.setAmbulancemodel("Dmax");
    //     ambulance1.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance1.setLicenseplate("1กว 4451");
    //     ambulance1.setAdddate(date1);
    //     ambulance1.setBrandid(brand1);
    //     ambulance1.setTypeid(type1);
    //     ambulance1.setProvinceid(province1);
    //     ambulanceRepository.save(ambulance1);
    //     Ambulance am1 = ambulanceRepository.findByAmbulanceid(1);

    //     DriverRegis driverRegis1 = new DriverRegis();
    //    driverRegis1.setDrivername("BennyBenBen");
    //    driverRegis1.setIdcard("1349900799556");
    //    driverRegis1.setTelephone("0927378868");
    //    driverRegis1.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis1.setGenderid(gender1);
    //    driverRegis1.setProvinceid(province1);
    //    driverRegis1.setEducationalid(educational1);
    //    driverRegis1.setPositionid(positiondriver1);
    //    driverRegisRepository.save(driverRegis1);
    //    DriverRegis dr1 = driverRegisRepository.findByDriverregisid(1);

        emergency1.setEmDate(date1);
        emergency1.setPatientName("Chayanon Piakhuntod");
        emergency1.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency1.setPatientTel("0902345678");
        emergency1.setNote("เป็นหวัด");
        emergency1.setSymptom(symptom1);
        // emergency1.setAmbulance(am1);
        // emergency1.setDriverRegis(dr1);

        emergencyRepository.saveAndFlush(emergency1);
    // DataIntegrityViolationException จะถูก throw
    assertThrows(DataIntegrityViolationException.class, () -> {
    // สร้าง emergency object ตัวที่ 2

    Emergency emergency2 = new Emergency();
    Symptom symptom2 = symptomRepository.findBySymptomid(1);

    java.util.Date date2 = new java.util.Date(2020-01-21);
//     AmbulanceBrand brand2 = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type2 = ambulanceTypeRepository.findByTypeid(1);
//     Province province2 = provinceRepository.findByProvinceid(1); 

//     Gender gender2 = genderRepository.findByGenderid(1);
//     Educational educational2 = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver2 = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance2 = new Ambulance();
//     ambulance2.setEnginenum("JS150MM03731");
//     ambulance2.setAmbulancemodel("Dmax");
//     ambulance2.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance2.setLicenseplate("1กว 4451");
//     ambulance2.setAdddate(date2);
//     ambulance2.setBrandid(brand2);
//     ambulance2.setTypeid(type2);
//     ambulance2.setProvinceid(province2);
//     ambulanceRepository.save(ambulance2);
//     Ambulance am2 = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis2 = new DriverRegis();
//    driverRegis2.setDrivername("BennyBenBen");
//    driverRegis2.setIdcard("1349900799556");
//    driverRegis2.setTelephone("0927378868");
//    driverRegis2.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis2.setGenderid(gender2);
//    driverRegis2.setProvinceid(province2);
//    driverRegis2.setEducationalid(educational2);
//    driverRegis2.setPositionid(positiondriver2);
//    driverRegisRepository.save(driverRegis2);
//    DriverRegis dr2 = driverRegisRepository.findByDriverregisid(1);


   emergency2.setEmDate(date2);
    emergency2.setPatientName("Chayanon Piakhuntod");
    emergency2.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
    emergency2.setPatientTel("0902345678");
    emergency2.setNote("เป็นหวัด");
    emergency2.setSymptom(symptom2);
    // emergency2.setAmbulance(am2);
    // emergency2.setDriverRegis(dr2);

    emergencyRepository.saveAndFlush(emergency2);
    });

    }
   

}