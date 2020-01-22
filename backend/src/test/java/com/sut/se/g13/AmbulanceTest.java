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
public class AmbulanceTest {
    private Validator validator;

    @Autowired
    private AmbulanceRepository ambulanceRepository;

    @Autowired
    private AmbulanceBrandRepository ambulanceBrandRepository;

    @Autowired
    private AmbulanceTypeRepository ambulanceTypeRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void B5814664_testAmbulancewithCorrect() throws ParseException {
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
    
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);

        ambulanceRepository.saveAndFlush(ambulance);
        Optional<Ambulance> check = ambulanceRepository.findById(ambulance.getAmbulanceid());
        assertEquals("JS150MM03731", check.get().getEnginenum());
        assertEquals("Dmax", check.get().getAmbulancemodel());
        assertEquals("ML2S150CMHTP03731", check.get().getAmbulancenum());
        assertEquals("1กว 4451", check.get().getLicenseplate());
        assertEquals(ambulance.getAdddate(), check.get().getAdddate());
        assertEquals(brand, check.get().getBrandid());
        assertEquals(type, check.get().getTypeid());
        assertEquals(province, check.get().getProvinceid());
    }

    @Test
    void B5814664_testAmbulancewithEngineNotNull() throws ParseException{
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum(null);
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);
        
        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("enginenum", message.getPropertyPath().toString());
    }

    @Test
    void B5814664_testAmbulancewithModelNotNull() throws ParseException{
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel(null);
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("ambulancemodel", message.getPropertyPath().toString());
    }

    @Test
    void B5814664_testAmbulancewithAmbulancenumNotNull() throws ParseException{
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum(null);
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("ambulancenum", message.getPropertyPath().toString());
    }

    @Test
    void B5814664_testAmbulancewithLicenseplateNotNull() throws ParseException {
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate(null);
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("licenseplate", message.getPropertyPath().toString());
    }

    @Test
    void B5814664_testAmbulancewithAddDateNotNull() throws ParseException {
        Ambulance ambulance = new Ambulance();
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(null);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("adddate", message.getPropertyPath().toString());
    }
    @Test
    void B5814664_testAmbulancewithBrandNotNull() throws ParseException{
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(date);
        ambulance.setBrandid(null);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);
        
        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("brandid", message.getPropertyPath().toString());
    }

    @Test
    void B5814664_testAmbulancewithTypeNull() throws ParseException{
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(null);
        ambulance.setProvinceid(province);
        
        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("typeid", message.getPropertyPath().toString());
    }

    @Test
    void B5814664_testAmbulancewithProvinceNotNull() throws ParseException{
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(null);
        
        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        assertEquals(1, result.size());

        ConstraintViolation<Ambulance> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("provinceid", message.getPropertyPath().toString());
    }

    @Test
    void B5814664_testEngineMustNotBe11String() throws ParseException{
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM0373");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Ambulance> v = result.iterator().next();
        assertEquals("must match \"\\w{12}\"", v.getMessage());
        assertEquals("enginenum", v.getPropertyPath().toString());
    }

    @Test
    void B5814664_testEngineMustNotBe13String() throws ParseException {
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM037311");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Ambulance> v = result.iterator().next();
        assertEquals("must match \"\\w{12}\"", v.getMessage());
        assertEquals("enginenum", v.getPropertyPath().toString());
    }

    @Test
    void B5814664_testAmNumMustNotBe16String() throws ParseException{
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP0373");
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Ambulance> v = result.iterator().next();
        assertEquals("must match \"\\w{17}\"", v.getMessage());
        assertEquals("ambulancenum", v.getPropertyPath().toString());
    }

    @Test
    void B5814664_testAmNumMustNotBe18String() throws ParseException{
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP037311");
        ambulance.setLicenseplate("1กว 4451");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Ambulance> v = result.iterator().next();
        assertEquals("must match \"\\w{17}\"", v.getMessage());
        assertEquals("ambulancenum", v.getPropertyPath().toString());
    }
    @Test
    void B5814664_testLicenseMustLessThan4String() throws ParseException{
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Ambulance> v = result.iterator().next();
        assertEquals("size must be between 4 and 8", v.getMessage());
        assertEquals("licenseplate", v.getPropertyPath().toString());
    }

    @Test
    void B5814664_testLicenseMustMoreThan8String() throws ParseException{
        Ambulance ambulance = new Ambulance();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("2020-01-23");
        AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
        AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
        Province province = provinceRepository.findByProvinceid(1);
        ambulance.setEnginenum("JS150MM03731");
        ambulance.setAmbulancemodel("Dmax");
        ambulance.setAmbulancenum("ML2S150CMHTP03731");
        ambulance.setLicenseplate("1กว 45684");
        ambulance.setAdddate(date);
        ambulance.setBrandid(brand);
        ambulance.setTypeid(type);
        ambulance.setProvinceid(province);

        Set<ConstraintViolation<Ambulance>> result = validator.validate(ambulance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Ambulance> v = result.iterator().next();
        assertEquals("size must be between 4 and 8", v.getMessage());
        assertEquals("licenseplate", v.getPropertyPath().toString());
    }

    @Test //test EngineNumไม่ซ้ำกัน
    void B5814664_testEngineMustBeUnique() throws ParseException{
    // สร้าง ambulance object
    Ambulance ambulance1 = new Ambulance();
    String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
    AmbulanceBrand brand1 = ambulanceBrandRepository.findByBrandid(1);
    AmbulanceType type1 = ambulanceTypeRepository.findByTypeid(1);
    Province province1 = provinceRepository.findByProvinceid(1);
    ambulance1.setEnginenum("JS150MM03731");
    ambulance1.setAmbulancemodel("Dmax");
    ambulance1.setAmbulancenum("ML2S150CMHTP03731");
    ambulance1.setLicenseplate("1กว 4451");
    ambulance1.setAdddate(date1);
    ambulance1.setBrandid(brand1);
    ambulance1.setTypeid(type1);
    ambulance1.setProvinceid(province1);

    ambulanceRepository.saveAndFlush(ambulance1);

    // คาดหวังว่า DataIntegrityViolationException จะถูก throw
    assertThrows(DataIntegrityViolationException.class, () -> {
    // สร้าง ambulance object ตัวที่ 2
    Ambulance ambulance2 = new Ambulance();
    String pattern2 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
        java.util.Date date2 = simpleDateFormat2.parse("2020-01-23");
    AmbulanceBrand brand2 = ambulanceBrandRepository.findByBrandid(1);
    AmbulanceType type2 = ambulanceTypeRepository.findByTypeid(1);
    Province province2 = provinceRepository.findByProvinceid(1);
    ambulance2.setEnginenum("JS150MM03731");
    ambulance2.setAmbulancemodel("Pajero");
    ambulance2.setAmbulancenum("HJ2S150CMHTP03731");
    ambulance2.setLicenseplate("1กว 6621");
    ambulance2.setAdddate(date2);
    ambulance2.setBrandid(brand2);
    ambulance2.setTypeid(type2);
    ambulance2.setProvinceid(province2);

    ambulanceRepository.saveAndFlush(ambulance2);
    });
    }

    @Test //test AmbulanceNumไม่ซ้ำกัน
    void B5814664_testAmbulanceMustBeUnique() throws ParseException{
    // สร้าง ambulance object
    Ambulance ambulance1 = new Ambulance();
    String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
    AmbulanceBrand brand1 = ambulanceBrandRepository.findByBrandid(1);
    AmbulanceType type1 = ambulanceTypeRepository.findByTypeid(1);
    Province province1 = provinceRepository.findByProvinceid(1);
    ambulance1.setEnginenum("JS150MM03731");
    ambulance1.setAmbulancemodel("Dmax");
    ambulance1.setAmbulancenum("ML2S150CMHTP03731");
    ambulance1.setLicenseplate("1กว 4451");
    ambulance1.setAdddate(date1);
    ambulance1.setBrandid(brand1);
    ambulance1.setTypeid(type1);
    ambulance1.setProvinceid(province1);

    ambulanceRepository.saveAndFlush(ambulance1);

    // คาดหวังว่า DataIntegrityViolationException จะถูก throw
    assertThrows(DataIntegrityViolationException.class, () -> {
    // สร้าง ambulance object ตัวที่ 2
    Ambulance ambulance2 = new Ambulance();
    String pattern2 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
        java.util.Date date2 = simpleDateFormat2.parse("2020-01-23");
    AmbulanceBrand brand2 = ambulanceBrandRepository.findByBrandid(1);
    AmbulanceType type2 = ambulanceTypeRepository.findByTypeid(1);
    Province province2 = provinceRepository.findByProvinceid(1);
    ambulance2.setEnginenum("JS150MM03745");
    ambulance2.setAmbulancemodel("Pajero");
    ambulance2.setAmbulancenum("ML2S150CMHTP03731");
    ambulance2.setLicenseplate("1กว 6621");
    ambulance2.setAdddate(date2);
    ambulance2.setBrandid(brand2);
    ambulance2.setTypeid(type2);
    ambulance2.setProvinceid(province2);

    ambulanceRepository.saveAndFlush(ambulance2);
    });
    }
}