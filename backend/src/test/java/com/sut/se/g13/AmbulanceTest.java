package com.sut.se.g13;

import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.dao.DataIntegrityViolationException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class AmbulanceTest {
    private Validator validator;

    @Autowired
    private AmbulanceRepository ambulanceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5814664_testAmbulancewithCorrect() {
        Ambulance ambulance = new Ambulance();
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");

        ambulanceRepository.saveAndFlush(ambulance);
        Optional<Ambulance> check = ambulanceRepository.findById(ambulance.getAmbulanceid());
        assertEquals("JS150MM03731", check.get().getEnginenum());
        assertEquals("Dmax", check.get().getAmbulancemodel());
        assertEquals("ML2S150CMHTP03731", check.get().getAmbulancenum());
        assertEquals("1กว 4451", check.get().getLicenseplate());

    }

    @Test
    void B5814664_testAmbulancewithEngineNotNull() {
        Ambulance ambulance = new Ambulance();
        ambulance.setEnginenum(null);
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("enginenum", message.getPropertyPath().toString());
    }

    @Test
    void B5814664_testAmbulancewithModelNotNull() {
        Ambulance ambulance = new Ambulance();
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel(null);
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("ambulancemodel", message.getPropertyPath().toString());
    }

    @Test
    void B5814664_testAmbulancewithAmbulancenumNotNull() {
        Ambulance ambulance = new Ambulance();
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum(null);
        ambulance.setLicenseplate("1กว 4451");

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("ambulancenum", message.getPropertyPath().toString());
    }

    @Test
    void B5814664_testAmbulancewithLicenseplateNotNull() {
        Ambulance ambulance = new Ambulance();
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate(null);

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("licenseplate", message.getPropertyPath().toString());
    }

    @Test
    void B5814664_testEngineMustNotBe11String() {
        Ambulance ambulance = new Ambulance();
        ambulance.setEnginenum("JS150MM0373");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Ambulance> v = result.iterator().next();
        assertEquals("size must be between 12 and 12", v.getMessage());
        assertEquals("enginenum", v.getPropertyPath().toString());
    }

    @Test
    void B5814664_testEngineMustNotBe14String() {
        Ambulance ambulance = new Ambulance();
        ambulance.setEnginenum("JS150MM037311");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Ambulance> v = result.iterator().next();
        assertEquals("size must be between 12 and 12", v.getMessage());
        assertEquals("enginenum", v.getPropertyPath().toString());
    }

    @Test
    void B5814664_testEngineMustNotBe16String() {
        Ambulance ambulance = new Ambulance();
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP0373");
        ambulance.setLicenseplate("1กว 4451");

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Ambulance> v = result.iterator().next();
        assertEquals("size must be between 17 and 17", v.getMessage());
        assertEquals("ambulancenum", v.getPropertyPath().toString());
    }

    @Test
    void B5814664_testEngineMustNotBe18String() {
        Ambulance ambulance = new Ambulance();
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP037311");
        ambulance.setLicenseplate("1กว 4451");

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Ambulance> v = result.iterator().next();
        assertEquals("size must be between 17 and 17", v.getMessage());
        assertEquals("ambulancenum", v.getPropertyPath().toString());
    }
    
    // @Test //test ไม่ซ้ำกัน
    // void B5814664_testEngineMustBeUnique() {
    //     // สร้าง ambulance object
    //     Ambulance ambulance1 = new Ambulance();
    //     ambulance1.setEnginenum("JS150MM03731");
    //     ambulance1.setAmbulancemodel("Dmax");
    //     ambulance1.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance1.setLicenseplate("1กว 4451");

    //     ambulanceRepository.saveAndFlush(ambulance1);

    //     // คาดหวังว่า DataIntegrityViolationException จะถูก throw
    //     assertThrows(DataIntegrityViolationException.class, () -> {
    //         // สร้าง ambulance object ตัวที่ 2
    //     Ambulance ambulance2 = new Ambulance();
    //     ambulance2.setEnginenum("JS150MM03731");
    //     ambulance2.setAmbulancemodel("Pajero");
    //     ambulance2.setAmbulancenum("HJ2S150CMHTP03731");
    //     ambulance2.setLicenseplate("1กว 6621");

    //     ambulanceRepository.saveAndFlush(ambulance2);
    //     });
    // }
}