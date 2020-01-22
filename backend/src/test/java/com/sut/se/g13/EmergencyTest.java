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

    @Autowired
    private EmergencyRepository emergencyRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void B5814985_testEmergencywithCorrect() {
        Emergency emergency = new Emergency();
 

        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");


        emergencyRepository.saveAndFlush(emergency);
        Optional<Emergency> check = emergencyRepository.findById(emergency.getEmergencyId());
        assertEquals("Chayanon Piakhuntod", check.get().getPatientName());
        assertEquals("123 ถ.เมือง อ.เมือง จ.เมือง", check.get().getPatientAddress());
        assertEquals("0902345678", check.get().getPatientTel());
        assertEquals("เป็นหวัด", check.get().getNote());

    }
    @Test
    void B5814985_testEmergencywithPatientNameNotNull() {
        Emergency emergency = new Emergency();
        emergency.setPatientName(null);
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("patientName", message.getPropertyPath().toString());
    }

    @Test
    void B5814985_testEmergencywithPatientAddressNotNull() {
        Emergency emergency = new Emergency();
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress(null);
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("patientAddress", message.getPropertyPath().toString());
    }

    @Test
    void B5814985_testEmergencywithPatientTelNotNull() {
        Emergency emergency = new Emergency();
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel(null);
        emergency.setNote("เป็นหวัด");

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("patientTel", message.getPropertyPath().toString());
    }

    @Test
    void B5814985_testEmergencywithNoteNotNull() {
        Emergency emergency = new Emergency();
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("0902345678");
        emergency.setNote(null);

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        assertEquals(1, result.size());

        ConstraintViolation<Emergency> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("note", message.getPropertyPath().toString());
    }

    @Test
    void B5814985_testPatientNameNotMoreThan30String() {
        Emergency emergency = new Emergency();
        emergency.setPatientName("Chayanon PiakhuntodXXXHardUnderBlade");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Emergency> v = result.iterator().next();
        assertEquals("size must be between 0 and 30", v.getMessage());
        assertEquals("patientName", v.getPropertyPath().toString());
    }

    @Test
    void B5814985_testPatientAddressNotMoreThan50String() {
        Emergency emergency = new Emergency();
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("445 ม.17 หมู่บ้านโสภณ 4 ต.แสนสุข อ.วารินชำราบ 34190");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด");
        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Emergency> v = result.iterator().next();
        assertEquals("size must be between 0 and 50", v.getMessage());
        assertEquals("patientAddress", v.getPropertyPath().toString());
    }

    @Test
    void B5814985_testPatientTelmustbe10String() {
        Emergency emergency = new Emergency();
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("09273788688");
        emergency.setNote("เป็นหวัด");
        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Emergency> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("patientTel", v.getPropertyPath().toString());
    }

    @Test
    void B5814985_testNoteNotMoreThan30String() {
        Emergency emergency = new Emergency();
        emergency.setPatientName("Chayanon Piakhuntod");
        emergency.setPatientAddress("123 ถ.เมือง อ.เมือง จ.เมือง");
        emergency.setPatientTel("0902345678");
        emergency.setNote("เป็นหวัด หัวร้อน ปวดหัว อยากตาย อกหัก");

        Set<ConstraintViolation<Emergency>> result = validator.validate(emergency);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Emergency> v = result.iterator().next();
        assertEquals("size must be between 0 and 30", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }
}