package com.sut.se.g13;

import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.dao.DataIntegrityViolationException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class DriverRegisTest {
    private Validator validator;

    @Autowired
    private DriverRegisRepository driverRegisRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    void B5813643_testDriverRegiswithCorrect() {
        DriverRegis driverRegis = new DriverRegis();
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");

        driverRegisRepository.saveAndFlush(driverRegis);
        Optional<DriverRegis> check = driverRegisRepository.findById(driverRegis.getDriverregisid());
        assertEquals("BennyBenBen", check.get().getDrivername());
        assertEquals("1349900799556", check.get().getIdcard());
        assertEquals("0927378868", check.get().getTelephone());
        assertEquals("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190", check.get().getAddress());
        
    }

    @Test
    void B5813643_testDriverRegiswithDrivernameNotNull() {
        DriverRegis driverRegis = new DriverRegis();
        driverRegis.setDrivername(null);
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");

        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("drivername", message.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDriverRegiswithIdcardNotNull() {
        DriverRegis driverRegis = new DriverRegis();
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard(null);
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");

        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("idcard", message.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDriverRegiswithTelephoneNotNull() {
        DriverRegis driverRegis = new DriverRegis();
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone(null);
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");

        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("telephone", message.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDriverRegiswithAddressNotNull() {
        DriverRegis driverRegis = new DriverRegis();
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress(null);

        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("address", message.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDrivernameNotMoreThan20String() {
        DriverRegis driverRegis = new DriverRegis();
        driverRegis.setDrivername("BennyBenBenTuckyTucky");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<DriverRegis> v = result.iterator().next();
        assertEquals("size must be between 1 and 20", v.getMessage());
        assertEquals("drivername", v.getPropertyPath().toString());
    }

    @Test
    void B5813643_testIdcardmustbe13String() {
        DriverRegis driverRegis = new DriverRegis();
        driverRegis.setDrivername("BennyBenBenTuckyTuck");
        driverRegis.setIdcard("13499007995566");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");

        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<DriverRegis> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("idcard", v.getPropertyPath().toString());
    }

    @Test
    void B5813643_testTelephonemustbe10String() {
        DriverRegis driverRegis = new DriverRegis();
        driverRegis.setDrivername("BennyBenBenTuckyTuck");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("09273788688");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");

        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<DriverRegis> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }

    @Test
    void B5813643_testAddressNotMoreThan50String() {
        DriverRegis driverRegis = new DriverRegis();
        driverRegis.setDrivername("BennyBenBenTuckyTuck");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 หมู่บ้านโสภณ 4 ต.แสนสุข อ.วารินชำราบ 34190");

        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<DriverRegis> v = result.iterator().next();
        assertEquals("size must be between 1 and 50", v.getMessage());
        assertEquals("address", v.getPropertyPath().toString());
    }
     
     

}
