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
public class NurseDeletedTest {
    private Validator validator;

    @Autowired
    private NurseDeletedRepository nursedeletedRepository;
    
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5802111_testNurseDeletedwithCorrect() throws ParseException {
        NurseDeleted nursedeleted = new NurseDeleted();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        Long nd = 1L;
       
        nursedeleted.setNurse(nd);
        nursedeleted.setEmployee("G13");
        nursedeleted.setNursedeletedtime(date);
       
        nursedeletedRepository.saveAndFlush(nursedeleted);
        Optional<NurseDeleted> check = nursedeletedRepository.findById(nursedeleted.getNursedeletedid());
        assertEquals(nd, check.get().getNurse());
        assertEquals("G13", check.get().getEmployee());
        assertEquals(nursedeleted.getNursedeletedtime(), check.get().getNursedeletedtime());
    }

    @Test
    void B5802111_testNurseDeletedwitNurseNotNull() throws ParseException{
        NurseDeleted nursedeleted = new NurseDeleted();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");

        nursedeleted.setNurse(null);
        nursedeleted.setEmployee("G13");
        nursedeleted.setNursedeletedtime(date);

        Set<ConstraintViolation<NurseDeleted>> result = validator.validate(nursedeleted);

        assertEquals(1, result.size());

        ConstraintViolation<NurseDeleted> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("nurse", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNurseDeletedwitEmployeeNotNull() throws ParseException{
        NurseDeleted nursedeleted = new NurseDeleted();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        Long nd = 1L;

        nursedeleted.setNurse(nd);
        nursedeleted.setEmployee(null);
        nursedeleted.setNursedeletedtime(date);

        Set<ConstraintViolation<NurseDeleted>> result = validator.validate(nursedeleted);

        assertEquals(1, result.size());

        ConstraintViolation<NurseDeleted> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("employee", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNurseDeletedwitNursedeletedtimeNotNull() throws ParseException{
        NurseDeleted nursedeleted = new NurseDeleted();
        Long nd = 1L;

        nursedeleted.setNurse(nd);
        nursedeleted.setEmployee("G13");
        nursedeleted.setNursedeletedtime(null);

        Set<ConstraintViolation<NurseDeleted>> result = validator.validate(nursedeleted);

        assertEquals(1, result.size());

        ConstraintViolation<NurseDeleted> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("nursedeletedtime", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testEmployeeMustNotLessThan2String() throws ParseException{
        NurseDeleted nursedeleted = new NurseDeleted();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        Long nd = 1L;

        nursedeleted.setNurse(nd);
        nursedeleted.setEmployee("G");
        nursedeleted.setNursedeletedtime(date);

        Set<ConstraintViolation<NurseDeleted>> result = validator.validate(nursedeleted);

        assertEquals(1, result.size());

        ConstraintViolation<NurseDeleted> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }

    @Test
    void B5814664_testEmployeeMustNotMoreThan20String() throws ParseException{
        NurseDeleted nursedeleted = new NurseDeleted();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        Long nd = 1L;

        nursedeleted.setNurse(nd);
        nursedeleted.setEmployee("G132564123NHJK121213132132");
        nursedeleted.setNursedeletedtime(date);

        Set<ConstraintViolation<NurseDeleted>> result = validator.validate(nursedeleted);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<NurseDeleted> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNurseIdMustBeUique() throws ParseException { // test Nurse ไม่ซ้ำกัน
        NurseDeleted nursedeleted1 = new NurseDeleted();
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Long nd1 = 1L;

        nursedeleted1.setNurse(nd1);
        nursedeleted1.setEmployee("G13");
        nursedeleted1.setNursedeletedtime(date1);

        nursedeletedRepository.saveAndFlush(nursedeleted1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
        
        // สร้าง AmbulanceDeleted object ตัวที่ 2
        NurseDeleted nursedeleted2 = new NurseDeleted();
        String pattern2 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
        java.util.Date date2 = simpleDateFormat2.parse("2020-01-23");
        Long nd2 = 1L;

        nursedeleted2.setNurse(nd2);
        nursedeleted2.setEmployee("G13");
        nursedeleted2.setNursedeletedtime(date2);

        nursedeletedRepository.saveAndFlush(nursedeleted2);
        });
    }

}
