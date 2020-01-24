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
    @Autowired
    private  TierRepository tierRepository;
    @Autowired
    private  GenderRepository genderRepository;
    @Autowired
    private  EducationalRepository educationalRepository;
    @Autowired
    private  ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5802111_testNursewithCorrect() { // เช็ค with correct ทั้งหมดของฟิลด์ที่กรอกข้อมูล
        final Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);

        nurseRepository.saveAndFlush(nurse);
        final Optional<Nurse> check = nurseRepository.findById(nurse.getNurseid());
        assertEquals("ศราวุฒิ ดวงดี", check.get().getNursename());
        assertEquals("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130", check.get().getAddress());
        assertEquals("0880517942", check.get().getTelephone());
        assertEquals("sarawutduangdee@gmail.com", check.get().getEmail());
        assertEquals(gender, check.get().getGenderid());
        assertEquals(province, check.get().getProvinceid());
        assertEquals(educational, check.get().getEducationalid());
        assertEquals(tier, check.get().getTierid());
    }

    @Test
    void B5802111_testNursewithNursenameNotNull(){ // เช็คค่า NotNull ชื่อพยาบาล
        final Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);
        nurse.setNursename(null);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);

        

        final Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        final ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("nursename", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithAddressNotNull(){ // เช็คค่า NotNull ที่อยู่
        final Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress(null);
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);


        final Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        final ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("address", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithTelephonNotNull(){ // เช็คค่า NotNull เบอร์โทรศัพท์
        final Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);
        

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone(null);
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);

        final Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        final ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("telephone", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithEmailNotNull(){ // เช็คค่า NotNull อีเมล
        final Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail(null);
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);

        final Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        final ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("email", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithGenderNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Gender
        Nurse nurse = new Nurse();
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(null);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("genderid", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithProvinceNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Province
        Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(null);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("provinceid", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithEducationalNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Educational
        Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(null);
        nurse.setTierid(tier);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("educationalid", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithTierrNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Tier
        Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(null);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("tierid", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursenameNotMustMoreThen30String() { // เช็ค Nursename ต้องไม่เกิน 30 ตัวอักษร
        final Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดีดวงดีดวงดีดวงดีดวง");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
 
        final Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("size must be between 1 and 30", message.getMessage());
        assertEquals("nursename", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testAddressNotMustMoreThen50String() { //เช็ค Address ต้องไม่เกิน 50 ตัวอักษร
        final Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("15333 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ จ.หนองคาย 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
 
        final Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("size must be between 1 and 50", message.getMessage());
        assertEquals("address", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testTelephoneMustNotBe9Digits() { // เช็ค Telephon ต้องเป็น 10 หลักเท่านั้น
        final Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("088051794");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
 
        final Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Nurse> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }

    @Test
    void B5802111_testTelephoneMustNotBe11Digits() { // เช็ค Telephon ต้องเป็น 10 หลักเท่านั้น
        final Nurse nurse = new Nurse();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("08805179422");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
 
        final Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<Nurse> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }

    @Test
    void B5802111_testTelephoneMustBeUnique() {  // เช็ค Telephon ต้องไม่ซ้ำกัน
        // สร้าง nurse object
        Nurse nurse1 = new Nurse();
        Gender gender1 = genderRepository.findByGenderid(1);
        Province province1 = provinceRepository.findByProvinceid(1);
        Educational educational1 = educationalRepository.findByEducationalid(1);
        Tier tier1 = tierRepository.findByTierid(1);

        nurse1.setNursename("ศราวุฒิ ดวงดี");
        nurse1.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse1.setTelephone("0880517942");
        nurse1.setEmail("sarawutduangdee@gmail.com");
        nurse1.setGenderid(gender1);
        nurse1.setProvinceid(province1);
        nurse1.setEducationalid(educational1);
        nurse1.setTierid(tier1);
        nurseRepository.saveAndFlush(nurse1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง nurse object ตัวที่ 2
            Nurse nurse2 = new Nurse();
            Gender gender2 = genderRepository.findByGenderid(1);
            Province province2 = provinceRepository.findByProvinceid(1);
            Educational educational2 = educationalRepository.findByEducationalid(1);
            Tier tier2 = tierRepository.findByTierid(1);

            nurse2.setNursename("ศราวุฒิ ดวงมี");
            nurse2.setAddress("351 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
            nurse2.setTelephone("0880517942");
            nurse2.setEmail("sarawutduangmee@gmail.com");
            nurse2.setGenderid(gender2);
            nurse2.setProvinceid(province2);
            nurse2.setEducationalid(educational2);
            nurse2.setTierid(tier2);
            nurseRepository.saveAndFlush(nurse2);
        });
    }

    @Test
    void B5802111_testEmailMustBeUnique() {  // เช็ค Email ต้องไม่ซ้ำกัน
        // สร้าง nurse object
        Nurse nurse1 = new Nurse();
        Gender gender1 = genderRepository.findByGenderid(1);
        Province province1 = provinceRepository.findByProvinceid(1);
        Educational educational1 = educationalRepository.findByEducationalid(1);
        Tier tier1 = tierRepository.findByTierid(1);

        nurse1.setNursename("ศราวุฒิ ดวงดี");
        nurse1.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse1.setTelephone("0880517942");
        nurse1.setEmail("sarawutduangdee@gmail.com");
        nurse1.setGenderid(gender1);
        nurse1.setProvinceid(province1);
        nurse1.setEducationalid(educational1);
        nurse1.setTierid(tier1);
        nurseRepository.saveAndFlush(nurse1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง nurse object ตัวที่ 2
            Nurse nurse2 = new Nurse();
            Gender gender2 = genderRepository.findByGenderid(1);
            Province province2 = provinceRepository.findByProvinceid(1);
            Educational educational2 = educationalRepository.findByEducationalid(1);
            Tier tier2 = tierRepository.findByTierid(1);

            nurse2.setNursename("ศราวุฒิ ดวงดี");
            nurse2.setAddress("351 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
            nurse2.setTelephone("0880517941");
            nurse2.setEmail("sarawutduangdee@gmail.com");
            nurse2.setGenderid(gender2);
            nurse2.setProvinceid(province2);
            nurse2.setEducationalid(educational2);
            nurse2.setTierid(tier2);
            nurseRepository.saveAndFlush(nurse2);
        });
    }

}