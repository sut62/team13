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


@DataJpaTest
public class EmergencyTest {
    private Validator validator;

    @Autowired private EmergencyRepository emergencyRepository;
    @Autowired private SymptomRepository symptomRepository;
    @Autowired private AmbulanceRepository ambulanceRepository;
    @Autowired private  DriverRegisRepository driverRegisRepository;
    @Autowired private NurseRepository nurseRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    //testEmergencywithCorrect แบบ  Correct ใส่ข้อมูลถูกต้องปกติ
    void B5814985_testEmergencywithCorrect() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        java.util.Date date = new java.util.Date(2020-01-21);

        emergency.setEmDate(date);
        emergency.setPatientName("Chalumechai Luangsakunthai");
        emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        emergency.setAmbulance(ambulance);
        emergency.setNurse(nurse);
        emergency.setDriverRegis(driverRegis);


        emergencyRepository.saveAndFlush(emergency);
        Optional<Emergency> check = emergencyRepository.findById(emergency.getEmergencyId());
        assertEquals("Chalumechai Luangsakunthai", check.get().getPatientName());
        assertEquals("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000", check.get().getPatientAddress());
        assertEquals("0902345678", check.get().getPatientTel());
        assertEquals("เป็นหวัด", check.get().getNote());
        assertEquals(symptom, check.get().getSymptom());
        assertEquals(ambulance, check.get().getAmbulance());
        assertEquals(nurse, check.get().getNurse());
        assertEquals(driverRegis, check.get().getDriverRegis());
    }
    @Test
    //ป้องกันการใส่ค่า null ของ EmDate 
    //testEmergencywithEmDateNotNull
    void B5814985_testEmergencywithEmDateNotNull() throws ParseException { 
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        emergency.setEmDate(null);
        emergency.setPatientName("Chalumechai Luangsakunthai");
        emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        emergency.setAmbulance(ambulance);
        emergency.setNurse(nurse);
        emergency.setDriverRegis(driverRegis);


        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("emDate", message.getPropertyPath().toString());
    }

    @Test
    //ป้องกันการใส่ค่า null ของ PatientName 
    //testEmergencywithPatientNameNotNull
    void B5814985_testEmergencywithPatientNameNotNull() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
    
        emergency.setEmDate(date);
        emergency.setPatientName(null);
        emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        emergency.setAmbulance(ambulance);
        emergency.setNurse(nurse);
        emergency.setDriverRegis(driverRegis);


        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("patientName", message.getPropertyPath().toString());
    }

    @Test
    //ป้องกันการใส่ค่า null ของ PatientAddress
    //testEmergencywithPatientAddressNotNull
    void B5814985_testEmergencywithPatientAddressNotNull() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
          
        emergency.setEmDate(date);
        emergency.setPatientName("Chalumechai Luangsakunthai");
        emergency.setPatientAddress(null);
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        emergency.setAmbulance(ambulance);
        emergency.setNurse(nurse);
        emergency.setDriverRegis(driverRegis);


        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("patientAddress", message.getPropertyPath().toString());
    }

    @Test
    //ป้องกันการใส่ค่า null ของ PatientTel
    //testEmergencywithPatientAddressNotNull
    void B5814985_testEmergencywithPatientTelNotNull() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
    
        emergency.setEmDate(date);
        emergency.setPatientName("Chalumechai Luangsakunthai");
        emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
        emergency.setPatientTel(null);
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        emergency.setAmbulance(ambulance);
        emergency.setNurse(nurse);
        emergency.setDriverRegis(driverRegis);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("patientTel", message.getPropertyPath().toString());
    }

    @Test
    //ป้องกันการใส่ค่า null ของ Note
    //testEmergencywithNoteNotBlank
    void B5814985_testEmergencywithNoteNotBlank() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);
        

        java.util.Date date = new java.util.Date(2020-01-21);
           
        emergency.setEmDate(date);
        emergency.setPatientName("Chalumechai Luangsakunthai");
        emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
        emergency.setPatientTel("0902345678");
        emergency.setNote(null);
        emergency.setSymptom(symptom);
        emergency.setAmbulance(ambulance);
        emergency.setNurse(nurse);
        emergency.setDriverRegis(driverRegis);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be blank", message.getMessage());
        assertEquals("note", message.getPropertyPath().toString());
    }
    @Test
    //ป้องกันการใส่ค่า null ของ Symptom
    //testEmergencywithSymptomNotNull
    void B5814985_testEmergencywithSymptomNotNull() throws ParseException {
        Emergency emergency = new Emergency();
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
       
       emergency.setEmDate(date);
       emergency.setPatientName("Chalumechai Luangsakunthai");
       emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
       emergency.setPatientTel("0902345678");
       emergency.setNote("เป็นหวัด");
       emergency.setSymptom(null);
       emergency.setAmbulance(ambulance);
       emergency.setNurse(nurse);
       emergency.setDriverRegis(driverRegis);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("symptom", message.getPropertyPath().toString());
    }

    @Test
    //ป้องกันการใส่ค่า null ของ Ambulance
    //testEmergencywithAddAmbulanceNotNull
    void B5814985_testEmergencywithAddAmbulanceNotNull() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        java.util.Date date = new java.util.Date(2020-01-21);

       emergency.setEmDate(date);
       emergency.setPatientName("Chalumechai Luangsakunthai");
       emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
       emergency.setPatientTel("0902345678");
       emergency.setNote("เป็นหวัด");
       emergency.setSymptom(symptom);
       emergency.setAmbulance(null);
       emergency.setNurse(nurse);
       emergency.setDriverRegis(driverRegis);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("ambulance", message.getPropertyPath().toString());
    }
    @Test
     //ป้องกันการใส่ค่า null ของ DriverRegis
    //testEmergencywithAddDriverRegisNotNull
    void B5814985_testEmergencywithAddDriverRegisNotNull() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
        
       emergency.setEmDate(date);
       emergency.setPatientName("Chalumechai Luangsakunthai");
       emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
       emergency.setPatientTel("0902345678");
       emergency.setNote("เป็นหวัด");
       emergency.setSymptom(symptom);
       emergency.setAmbulance(ambulance);
       emergency.setNurse(nurse);
       emergency.setDriverRegis(null);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("driverRegis", message.getPropertyPath().toString());
    }
    @Test
     //ป้องกันการใส่ค่า null ของ Nurse
    //testEmergencywithAddNurseNotNull
    void B5814985_testEmergencywithAddNurseNotNull() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
        
       emergency.setEmDate(date);
       emergency.setPatientName("Chalumechai Luangsakunthai");
       emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
       emergency.setPatientTel("0902345678");
       emergency.setNote("เป็นหวัด");
       emergency.setSymptom(symptom);
       emergency.setAmbulance(ambulance);
       emergency.setNurse(null);
       emergency.setDriverRegis(driverRegis);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("nurse", message.getPropertyPath().toString());
    }
    @Test
    //เช็คชื่อคนไข้ ห้ามน้อยกว่ากว่า 3 อักษร
   //testPatientNameNotLessThan3String
   void B5814985_testPatientNameNotLessThan3String() throws ParseException {
       Emergency emergency = new Emergency();
       Symptom symptom = symptomRepository.findBySymptomid(1);
       Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
       Nurse nurse = nurseRepository.findByNurseid(1);
       DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

       java.util.Date date = new java.util.Date(2020-01-21);
   
       emergency.setEmDate(date);
       emergency.setPatientName("Ch");
       emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
       emergency.setPatientTel("0902345678");
       emergency.setNote("เป็นหวัด");
       emergency.setSymptom(symptom);
       emergency.setAmbulance(ambulance);
       emergency.setNurse(nurse);
       emergency.setDriverRegis(driverRegis);

       Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Emergency> v = result.iterator().next();
       assertEquals("size must be between 3 and 30", v.getMessage());
       assertEquals("patientName", v.getPropertyPath().toString());
   }

    @Test
     //เช็คชื่อคนไข้ ห้ามมากกว่า 30 อักษร
    //testPatientNameNotMoreThan30String
    void B5814985_testPatientNameNotMoreThan30String() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
    
        emergency.setEmDate(date);
        emergency.setPatientName("Chalumechai LuangsakunthaiXXXHardUnderBlade");
        emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        emergency.setAmbulance(ambulance);
        emergency.setNurse(nurse);
        emergency.setDriverRegis(driverRegis);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Emergency> v = result.iterator().next();
        assertEquals("size must be between 3 and 30", v.getMessage());
        assertEquals("patientName", v.getPropertyPath().toString());
    }
    @Test
    //เช็คที่อยู่คนไข้ ห้ามน้อยกว่า 3 อักษร
   //testPatientAddressNotMoreThan3String
   void B5814985_testPatientAddressNotLessThan3String() throws ParseException {
       Emergency emergency = new Emergency();
       Symptom symptom = symptomRepository.findBySymptomid(1);
       Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
       Nurse nurse = nurseRepository.findByNurseid(1);
       DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

       java.util.Date date = new java.util.Date(2020-01-21);

       emergency.setEmDate(date);
       emergency.setPatientName("Chalumechai Luangsakunthai");
       emergency.setPatientAddress("44");
       emergency.setPatientTel("0902345678");
       emergency.setNote("เป็นหวัด");
       emergency.setSymptom(symptom);
       emergency.setAmbulance(ambulance);
       emergency.setNurse(nurse);
       emergency.setDriverRegis(driverRegis);

       Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Emergency> v = result.iterator().next();
       assertEquals("size must be between 3 and 70", v.getMessage());
       assertEquals("patientAddress", v.getPropertyPath().toString());
   }

    @Test
     //เช็คที่อยู่คนไข้ ห้ามมากกว่า 70 อักษร
    //testPatientAddressNotMoreThan70String
    void B5814985_testPatientAddressNotMoreThan70String() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        java.util.Date date = new java.util.Date(2020-01-21);

        emergency.setEmDate(date);
        emergency.setPatientName("Chalumechai Luangsakunthai");
        emergency.setPatientAddress("445 ม.17 หมู่บ้านโสภณ 4 ต.แสนสุข อ.วารินชำราบ 34190XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        emergency.setAmbulance(ambulance);
        emergency.setNurse(nurse);
        emergency.setDriverRegis(driverRegis);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Emergency> v = result.iterator().next();
        assertEquals("size must be between 3 and 70", v.getMessage());
        assertEquals("patientAddress", v.getPropertyPath().toString());
    }

    @Test
    //เช็คเบอร์โทรคนไข้ ห้ามมากกว่า 10 อักษร
    //testPatientTelmustbe10String
    void B5814985_testPatientTelmustbe10String() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
    
       emergency.setEmDate(date);
        emergency.setPatientName("Chalumechai Luangsakunthai");
        emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
        emergency.setPatientTel("092737886889");
        emergency.setNote("เป็นหวัด");
        emergency.setSymptom(symptom);
        emergency.setAmbulance(ambulance);
        emergency.setNurse(nurse);
        emergency.setDriverRegis(driverRegis);


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
    //testPatientNotemustbe30String
    void B5814985_testNoteNotMoreThan30String() throws ParseException {
        Emergency emergency = new Emergency();
        Symptom symptom = symptomRepository.findBySymptomid(1);
        Ambulance ambulance  = ambulanceRepository.findByAmbulanceid(1);
        Nurse nurse = nurseRepository.findByNurseid(1);
        DriverRegis driverRegis = driverRegisRepository.findByDriverregisid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
        
        emergency.setEmDate(date);
        emergency.setPatientName("Chalumechai Luangsakunthai");
        emergency.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด หัวร้อน ปวดหัว อยากตาย อกหัก");
        emergency.setSymptom(symptom);
        emergency.setAmbulance(ambulance);
        emergency.setNurse(nurse);
        emergency.setDriverRegis(driverRegis);

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
   void B5814985_testPatientTelMustBeUnique() throws ParseException{
    // สร้าง emergency object
    Emergency emergency1 = new Emergency();
    Symptom symptom1 = symptomRepository.findBySymptomid(1);
    Ambulance ambulance1  = ambulanceRepository.findByAmbulanceid(1);
    Nurse nurse1 = nurseRepository.findByNurseid(1);
    DriverRegis driverRegis1 = driverRegisRepository.findByDriverregisid(1);
    
    java.util.Date date1 = new java.util.Date(2020-01-21);


    emergency1.setEmDate(date1);
    emergency1.setPatientName("Chalumechai Luangsakunthai");
    emergency1.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
    emergency1.setPatientTel("0902345678");
    emergency1.setNote("เป็นหวัด");
    emergency1.setSymptom(symptom1);
    emergency1.setAmbulance(ambulance1);
    emergency1.setNurse(nurse1);
    emergency1.setDriverRegis(driverRegis1);

    emergencyRepository.saveAndFlush(emergency1);

    // DataIntegrityViolationException จะถูก throw
    assertThrows(DataIntegrityViolationException.class, () -> {
    // สร้าง emergency object ตัวที่ 2

    Emergency emergency2 = new Emergency();
    Symptom symptom2 = symptomRepository.findBySymptomid(1);
    Ambulance ambulance2  = ambulanceRepository.findByAmbulanceid(1);
    Nurse nurse2 = nurseRepository.findByNurseid(1);
    DriverRegis driverRegis2 = driverRegisRepository.findByDriverregisid(1);
    
    java.util.Date date2 = new java.util.Date(2020-01-21);


    emergency2.setEmDate(date2);
    emergency2.setPatientName("Chalumechai Luangsakunthai");
    emergency2.setPatientAddress("123 ถ.เมือง ต.ในเมือง อ.เมือง จ.กรุงเทพ 100000");
    emergency2.setPatientTel("0902345678");
    emergency2.setNote("เป็นหวัด");
    emergency2.setSymptom(symptom2);
    emergency2.setAmbulance(ambulance2);
    emergency2.setNurse(nurse2);
    emergency2.setDriverRegis(driverRegis2);

    emergencyRepository.saveAndFlush(emergency2);

    });
    }
}