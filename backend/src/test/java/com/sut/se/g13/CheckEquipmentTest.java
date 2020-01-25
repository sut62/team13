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
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;


@DataJpaTest
public class CheckEquipmentTest {
    private Validator validator;

    @Autowired
    private CheckEquipmentRepository checkEquipmentRepository;
    @Autowired 
    private StatusRepository statusRepository;

    @Autowired private AmbulanceRepository ambulanceRepository;
    @Autowired private AmbulanceBrandRepository ambulanceBrandRepository;
    @Autowired private AmbulanceTypeRepository ambulanceTypeRepository;
    @Autowired private ProvinceRepository provinceRepository;

    @Autowired 
    private NurseRepository nurseRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5800841_testCheckEquipmentwithCorrect() throws ParseException {     //เช็คทั้งหมดของฟิวล์
        CheckEquipment checkEquipment= new CheckEquipment();
        Status status = statusRepository.findByStatusid(1);
        // Ambulance ambulance = ambulanceRepository.findByAmbulanceid(1); 
        // Nurse nurse = nurseRepository.findByNurseid(1);

        java.util.Date date = new java.util.Date(2020-01-21);
        checkEquipment.setNote("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        checkEquipment.setCheckDate(date);
        checkEquipment.setDefibrillator(true);
        checkEquipment.setSphygmomanometer(true);
        checkEquipment.setRespirator(true);
        checkEquipment.setOxygenmachine(true);
        checkEquipment.setSuction(true);
        checkEquipment.setMotionDevice(true);
        checkEquipment.setMedicalSprayer(true);
        checkEquipment.setStatus(status);

        
        checkEquipmentRepository.saveAndFlush(checkEquipment);


        Optional<CheckEquipment> check = checkEquipmentRepository.findById(checkEquipment.getId());
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", check.get().getNote());
        assertEquals(checkEquipment.getCheckDate(), check.get().getCheckDate());
        assertEquals(true, check.get().getDefibrillator());
        assertEquals(true, check.get().getSphygmomanometer());
        assertEquals(true, check.get().getRespirator());
        assertEquals(true, check.get().getOxygenmachine());
        assertEquals(true, check.get().getSuction());
        assertEquals(true, check.get().getMotionDevice());
        assertEquals(true, check.get().getMedicalSprayer());
        assertEquals(status, check.get().getStatus());
    }

    @Test
    void B5800841_testCheckEquipmentwithDefibrillatorNotNull() throws ParseException{
        CheckEquipment checkEquipment= new CheckEquipment();
        java.util.Date date = new java.util.Date(2020-01-21);

        checkEquipment.setNote("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        checkEquipment.setCheckDate(date);
        checkEquipment.setDefibrillator(null);
        checkEquipment.setSphygmomanometer(true);
        checkEquipment.setRespirator(true);
        checkEquipment.setOxygenmachine(true);
        checkEquipment.setSuction(true);
        checkEquipment.setMotionDevice(true);
        checkEquipment.setMedicalSprayer(true);

        Set<ConstraintViolation<CheckEquipment>> result = validator.validate(checkEquipment);

        assertEquals(1, result.size());

        ConstraintViolation<CheckEquipment> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("defibrillator", message.getPropertyPath().toString());
    }

    @Test
    void B5800841_testCheckEquipmentwithSphygmomanometerNotNull() throws ParseException{
        CheckEquipment checkEquipment= new CheckEquipment();
        java.util.Date date = new java.util.Date(2020-01-21);

        checkEquipment.setNote("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        checkEquipment.setCheckDate(date);
        checkEquipment.setDefibrillator(true);
        checkEquipment.setSphygmomanometer(null);
        checkEquipment.setRespirator(true);
        checkEquipment.setOxygenmachine(true);
        checkEquipment.setSuction(true);
        checkEquipment.setMotionDevice(true);
        checkEquipment.setMedicalSprayer(true);

        Set<ConstraintViolation<CheckEquipment>> result = validator.validate(checkEquipment);

        assertEquals(1, result.size());

        ConstraintViolation<CheckEquipment> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("sphygmomanometer", message.getPropertyPath().toString());
    }

    @Test
    void B5800841_testCheckEquipmentwithRespiratorNotNull() throws ParseException{
        CheckEquipment checkEquipment= new CheckEquipment();
        java.util.Date date = new java.util.Date(2020-01-21);

        checkEquipment.setNote("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        checkEquipment.setCheckDate(date);
        checkEquipment.setDefibrillator(true);
        checkEquipment.setSphygmomanometer(true);
        checkEquipment.setRespirator(null);
        checkEquipment.setOxygenmachine(true);
        checkEquipment.setSuction(true);
        checkEquipment.setMotionDevice(true);
        checkEquipment.setMedicalSprayer(true);

        Set<ConstraintViolation<CheckEquipment>> result = validator.validate(checkEquipment);

        assertEquals(1, result.size());

        ConstraintViolation<CheckEquipment> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("respirator", message.getPropertyPath().toString());
    }

    @Test
    void B5800841_testCheckEquipmentwithOxygenmachineNotNull() throws ParseException{
        CheckEquipment checkEquipment= new CheckEquipment();
        java.util.Date date = new java.util.Date(2020-01-21);

        checkEquipment.setNote("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        checkEquipment.setCheckDate(date);
        checkEquipment.setDefibrillator(true);
        checkEquipment.setSphygmomanometer(true);
        checkEquipment.setRespirator(true);
        checkEquipment.setOxygenmachine(null);
        checkEquipment.setSuction(true);
        checkEquipment.setMotionDevice(true);
        checkEquipment.setMedicalSprayer(true);

        Set<ConstraintViolation<CheckEquipment>> result = validator.validate(checkEquipment);

        assertEquals(1, result.size());

        ConstraintViolation<CheckEquipment> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("oxygenmachine", message.getPropertyPath().toString());
    }

    @Test
    void B5800841_testCheckEquipmentwithSuctionNotNull() throws ParseException{
        CheckEquipment checkEquipment= new CheckEquipment();
        java.util.Date date = new java.util.Date(2020-01-21);

        checkEquipment.setNote("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        checkEquipment.setCheckDate(date);
        checkEquipment.setDefibrillator(true);
        checkEquipment.setSphygmomanometer(true);
        checkEquipment.setRespirator(true);
        checkEquipment.setOxygenmachine(true);
        checkEquipment.setSuction(null);
        checkEquipment.setMotionDevice(true);
        checkEquipment.setMedicalSprayer(true);

        Set<ConstraintViolation<CheckEquipment>> result = validator.validate(checkEquipment);

        assertEquals(1, result.size());

        ConstraintViolation<CheckEquipment> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("suction", message.getPropertyPath().toString());
    }

    @Test
    void B5800841_testCheckEquipmentwithMotionDeviceNotNull() throws ParseException{
        CheckEquipment checkEquipment= new CheckEquipment();
        java.util.Date date = new java.util.Date(2020-01-21);

        checkEquipment.setNote("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        checkEquipment.setCheckDate(date);
        checkEquipment.setDefibrillator(true);
        checkEquipment.setSphygmomanometer(true);
        checkEquipment.setRespirator(true);
        checkEquipment.setOxygenmachine(true);
        checkEquipment.setSuction(true);
        checkEquipment.setMotionDevice(null);
        checkEquipment.setMedicalSprayer(true);

        Set<ConstraintViolation<CheckEquipment>> result = validator.validate(checkEquipment);

        assertEquals(1, result.size());

        ConstraintViolation<CheckEquipment> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("motionDevice", message.getPropertyPath().toString());
    }

    @Test
    void B5800841_testCheckEquipmentwithMedicalSprayerNotNull() throws ParseException{
        CheckEquipment checkEquipment= new CheckEquipment();
        java.util.Date date = new java.util.Date(2020-01-21);

        checkEquipment.setNote("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        checkEquipment.setCheckDate(date);
        checkEquipment.setDefibrillator(true);
        checkEquipment.setSphygmomanometer(true);
        checkEquipment.setRespirator(true);
        checkEquipment.setOxygenmachine(true);
        checkEquipment.setSuction(true);
        checkEquipment.setMotionDevice(true);
        checkEquipment.setMedicalSprayer(null);

        Set<ConstraintViolation<CheckEquipment>> result = validator.validate(checkEquipment);

        assertEquals(1, result.size());

        ConstraintViolation<CheckEquipment> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("medicalSprayer", message.getPropertyPath().toString());
    }

    @Test
    void B5800841_testCheckEquipmentwithNoteNotNull() throws ParseException{
        CheckEquipment checkEquipment= new CheckEquipment();
        java.util.Date date = new java.util.Date(2020-01-21);

        checkEquipment.setNote(null);
        checkEquipment.setCheckDate(date);
        checkEquipment.setDefibrillator(true);
        checkEquipment.setSphygmomanometer(true);
        checkEquipment.setRespirator(true);
        checkEquipment.setOxygenmachine(true);
        checkEquipment.setSuction(true);
        checkEquipment.setMotionDevice(true);
        checkEquipment.setMedicalSprayer(true);

        Set<ConstraintViolation<CheckEquipment>> result = validator.validate(checkEquipment);

        assertEquals(1, result.size());

        ConstraintViolation<CheckEquipment> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("note", message.getPropertyPath().toString());
    }

   @Test
    void B5800841_testCheckEquipmentwithCheckDateNotNull() throws ParseException{
        CheckEquipment checkEquipment= new CheckEquipment();
        java.util.Date date = new java.util.Date(2020-01-21);
        checkEquipment.setNote("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        checkEquipment.setCheckDate(null);
        checkEquipment.setDefibrillator(true);
        checkEquipment.setSphygmomanometer(true);
        checkEquipment.setRespirator(true);
        checkEquipment.setOxygenmachine(true);
        checkEquipment.setSuction(true);
        checkEquipment.setMotionDevice(true);
        checkEquipment.setMedicalSprayer(true);

        Set<ConstraintViolation<CheckEquipment>> result = validator.validate(checkEquipment);

        assertEquals(1, result.size());

        ConstraintViolation<CheckEquipment> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("checkDate", message.getPropertyPath().toString());
    }

    @Test
    void B5800841_testNoteMustMoreThan50String(){
        CheckEquipment checkEquipment= new CheckEquipment();
        java.util.Date date = new java.util.Date(2020-01-21);
        checkEquipment.setNote("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        checkEquipment.setCheckDate(date);
        checkEquipment.setDefibrillator(true);
        checkEquipment.setSphygmomanometer(true);
        checkEquipment.setRespirator(true);
        checkEquipment.setOxygenmachine(true);
        checkEquipment.setSuction(true);
        checkEquipment.setMotionDevice(true);
        checkEquipment.setMedicalSprayer(true);

        Set<ConstraintViolation<CheckEquipment>> result = validator.validate(checkEquipment);

          // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

         // error message ตรงชนิด และถูก field
         ConstraintViolation<CheckEquipment> v = result.iterator().next();
        assertEquals("size must be between 4 and 50", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
     }

     @Test
    void B5800841_testNoteMustLessThan4String()throws ParseException{
        CheckEquipment checkEquipment= new CheckEquipment();
        java.util.Date date = new java.util.Date(2020-01-21);

        checkEquipment.setNote("aaa");
        checkEquipment.setCheckDate(date);
        checkEquipment.setDefibrillator(true);
        checkEquipment.setSphygmomanometer(true);
        checkEquipment.setRespirator(true);
        checkEquipment.setOxygenmachine(true);
        checkEquipment.setSuction(true);
        checkEquipment.setMotionDevice(true);
        checkEquipment.setMedicalSprayer(true);

        Set<ConstraintViolation<CheckEquipment>> result = validator.validate(checkEquipment);

        // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());
        
         // error message ตรงชนิด และถูก field
         ConstraintViolation<CheckEquipment> v = result.iterator().next();
        assertEquals("size must be between 4 and 50", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
     }

}
