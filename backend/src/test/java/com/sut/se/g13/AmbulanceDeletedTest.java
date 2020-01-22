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
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class AmbulanceDeletedTest {
    private Validator validator;

    @Autowired
    private AmbulanceDeletedRepository ambulanceDeletedRepository;
    
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    
    @Test
    void B5814664_testAmbulanceDeletedwithCorrect() throws ParseException {
        AmbulanceDeleted ambulancedeleted = new AmbulanceDeleted();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        Long am = 1L;
       
        ambulancedeleted.setAmbulance(am);
        ambulancedeleted.setEmployee("G13");
        ambulancedeleted.setDeletedtime(date);
       
        ambulanceDeletedRepository.saveAndFlush(ambulancedeleted);
        Optional<AmbulanceDeleted> check = ambulanceDeletedRepository.findById(ambulancedeleted.getDeletedid());
        assertEquals(am, check.get().getAmbulance());
        assertEquals("G13", check.get().getEmployee());
        assertEquals(ambulancedeleted.getDeletedtime(), check.get().getDeletedtime());
    }
    @Test
    void B5814664_testAmbulanceDeletedwithAmbulanceNotNull() throws ParseException{
        AmbulanceDeleted ambulancedeleted = new AmbulanceDeleted();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        
        ambulancedeleted.setAmbulance(null);
        ambulancedeleted.setEmployee("G13");
        ambulancedeleted.setDeletedtime(date);
       
        
        Set<ConstraintViolation<AmbulanceDeleted>> result = validator.validate(ambulancedeleted);

        assertEquals(1, result.size());

        ConstraintViolation<AmbulanceDeleted> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("ambulance", message.getPropertyPath().toString());
    }
    @Test
    void B5814664_testAmbulanceDeletedwithEmployeeNotNull() throws ParseException{
        AmbulanceDeleted ambulancedeleted = new AmbulanceDeleted();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        Long am = 1L;
        
        ambulancedeleted.setAmbulance(am);
        ambulancedeleted.setEmployee(null);
        ambulancedeleted.setDeletedtime(date);
       
        
        Set<ConstraintViolation<AmbulanceDeleted>> result = validator.validate(ambulancedeleted);

        assertEquals(1, result.size());

        ConstraintViolation<AmbulanceDeleted> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("employee", message.getPropertyPath().toString());
    }
    @Test
        void B5814664_testAmbulanceDeletedwithDeletedtimeNotNull() throws ParseException{
        AmbulanceDeleted ambulancedeleted = new AmbulanceDeleted();
        Long am = 1L;
        
        ambulancedeleted.setAmbulance(am);
        ambulancedeleted.setEmployee("G13");
        ambulancedeleted.setDeletedtime(null);
       
        
        Set<ConstraintViolation<AmbulanceDeleted>> result = validator.validate(ambulancedeleted);

        assertEquals(1, result.size());

        ConstraintViolation<AmbulanceDeleted> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("deletedtime", message.getPropertyPath().toString());
    }
    @Test
    void B5814664_testEmployeeMustNotLessThan2String() throws ParseException{
        AmbulanceDeleted ambulancedeleted = new AmbulanceDeleted();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        Long am = 1L;

        ambulancedeleted.setAmbulance(am);
        ambulancedeleted.setEmployee("G");
        ambulancedeleted.setDeletedtime(date);

        Set<ConstraintViolation<AmbulanceDeleted>> result = validator.validate(ambulancedeleted);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<AmbulanceDeleted> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }

    @Test
    void B5814664_testEmployeeMustNotMoreThan20String() throws ParseException{
        AmbulanceDeleted ambulancedeleted = new AmbulanceDeleted();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        Long am = 1L;

        ambulancedeleted.setAmbulance(am);
        ambulancedeleted.setEmployee("G132564123NHJK121213132132");
        ambulancedeleted.setDeletedtime(date);

        Set<ConstraintViolation<AmbulanceDeleted>> result = validator.validate(ambulancedeleted);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<AmbulanceDeleted> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }

    @Test //test EngineNumไม่ซ้ำกัน
    void B5814664_testAmbulanceIDMustBeUnique() throws ParseException{
    // สร้าง AmbulanceDeleted object
    AmbulanceDeleted ambulancedeleted1 = new AmbulanceDeleted();
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Long am1 = 1L;
        ambulancedeleted1.setAmbulance(am1);
        ambulancedeleted1.setEmployee("G13");
        ambulancedeleted1.setDeletedtime(date1);

    ambulanceDeletedRepository.saveAndFlush(ambulancedeleted1);

    // คาดหวังว่า DataIntegrityViolationException จะถูก throw
    assertThrows(DataIntegrityViolationException.class, () -> {
    // สร้าง AmbulanceDeleted object ตัวที่ 2
    AmbulanceDeleted ambulancedeleted2 = new AmbulanceDeleted();
        String pattern2 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
        java.util.Date date2 = simpleDateFormat2.parse("2020-01-23");
        Long am2 = 1L;
        ambulancedeleted2.setAmbulance(am2);
        ambulancedeleted2.setEmployee("G13");
        ambulancedeleted2.setDeletedtime(date2);

    ambulanceDeletedRepository.saveAndFlush(ambulancedeleted2);
    });
    }


}