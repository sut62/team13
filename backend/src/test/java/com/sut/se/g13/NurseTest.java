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
public class NurseTest {
    private Validator validator;

    @Autowired
    private NurseRepository nurseRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5802111_testNursewithCorrect() {
        Nurse nurse = new Nurse();
        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");

        nurseRepository.saveAndFlush(nurse);
        Optional<Nurse> check = nurseRepository.findById(nurse.getNurseid());
        assertEquals("ศราวุฒิ ดวงดี", check.get().getNursename());
        assertEquals("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130", check.get().getAddress());
        assertEquals("0880517942", check.get().getTelephone());
        assertEquals("sarawutduangdee@gmail.com", check.get().getEmail());
    }

    @Test
    void B5802111_testNursewithNursenameNotNull(){
        Nurse nurse = new Nurse();
        nurse.setNursename(null);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("nursename", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithAddressNotNull(){
        Nurse nurse = new Nurse();
        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress(null);
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("address", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithTelephonNotNull(){
        Nurse nurse = new Nurse();
        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone(null);
        nurse.setEmail("sarawutduangdee@gmail.com");

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("telephone", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithEmailNotNull(){
        Nurse nurse = new Nurse();
        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail(null);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("email", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursenameNotMustMoreThen30String() {
        Nurse nurse = new Nurse();
        nurse.setNursename("ศราวุฒิ ดวงดีดวงดีดวงดีดวงดีดวง");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
 
        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("size must be between 1 and 30", message.getMessage());
        assertEquals("nursename", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testAddressNotMustMoreThen50String() {
        Nurse nurse = new Nurse();
        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("15333 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ จ.หนองคาย 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
 
        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("size must be between 1 and 50", message.getMessage());
        assertEquals("address", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testTelephoneMustNotBe9Digits() {
        Nurse nurse = new Nurse();
        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("088051794");
        nurse.setEmail("sarawutduangdee@gmail.com");
 
        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Nurse> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }

    @Test
    void B5802111_testTelephoneMustNotBe11Digits() {
        Nurse nurse = new Nurse();
        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("08805179422");
        nurse.setEmail("sarawutduangdee@gmail.com");
 
        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Nurse> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }

    @Test
    void B5802111_testTelephoneMustBeUnique() {
        // สร้าง nurse object
        Nurse nurse1 = new Nurse();
        nurse1.setNursename("ศราวุฒิ ดวงดี");
        nurse1.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse1.setTelephone("0880517942");
        nurse1.setEmail("sarawutduangdee@gmail.com");
        nurseRepository.saveAndFlush(nurse1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง nurse object ตัวที่ 2
            Nurse nurse2 = new Nurse();
            nurse2.setNursename("ศราวุฒิ ดวงมี");
            nurse2.setAddress("351 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
            nurse2.setTelephone("0880517942");
            nurse2.setEmail("sarawutduangmee@gmail.com");
            nurseRepository.saveAndFlush(nurse2);
        });
    }

    @Test
    void B5802111_testEmailMustBeUnique() {
        // สร้าง nurse object
        Nurse nurse1 = new Nurse();
        nurse1.setNursename("ศราวุฒิ ดวงดี");
        nurse1.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse1.setTelephone("0880517942");
        nurse1.setEmail("sarawutduangdee@gmail.com");
        nurseRepository.saveAndFlush(nurse1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง nurse object ตัวที่ 2
            Nurse nurse2 = new Nurse();
            nurse2.setNursename("ศราวุฒิ ดวงดี");
            nurse2.setAddress("351 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
            nurse2.setTelephone("0880517941");
            nurse2.setEmail("sarawutduangdee@gmail.com");
            nurseRepository.saveAndFlush(nurse2);
        });
    }

}