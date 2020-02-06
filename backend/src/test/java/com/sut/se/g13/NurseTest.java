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
    void B5802111_testNursewithCorrect() throws ParseException { // เช็ค with correct ทั้งหมดของฟิลด์ที่กรอกข้อมูล
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);

        nurseRepository.saveAndFlush(nurse);
        Optional<Nurse> check = nurseRepository.findById(nurse.getNurseid());
        assertEquals("ศราวุฒิ ดวงดี", check.get().getNursename());
        assertEquals(nurse.getBirthday(), check.get().getBirthday());
        assertEquals("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130", check.get().getAddress());
        assertEquals("0880517942", check.get().getTelephone());
        assertEquals("sarawutduangdee@gmail.com", check.get().getEmail());
        assertEquals(gender, check.get().getGenderid());
        assertEquals(province, check.get().getProvinceid());
        assertEquals(educational, check.get().getEducationalid());
        assertEquals(tier, check.get().getTierid());
        assertEquals(nurse.getNowdate(), check.get().getNowdate());
    }

    @Test
    void B5802111_testNursewithNursenameNotNull() throws ParseException { // เช็คค่า NotNull ชื่อพยาบาล
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename(null);
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("nursename", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithBirthdayNotNull() throws ParseException{
        Nurse nurse = new Nurse();
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(null);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("birthday", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithAddressNotNull() throws ParseException { // เช็คค่า NotNull ที่อยู่
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress(null);
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("address", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithTelephonNotNull() throws ParseException { // เช็คค่า NotNull เบอร์โทรศัพท์
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);
        
        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone(null);
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("telephone", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithEmailNotNull() throws ParseException { // เช็คค่า NotNull อีเมล
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail(null);
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("email", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithGenderNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Gender
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(null);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("genderid", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithProvinceNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Province
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(null);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("provinceid", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithEducationalNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Educational
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(null);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("educationalid", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithTierrNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Tier
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(null);
        nurse.setNowdate(date1);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("tierid", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursewithNodDateNotNull() throws ParseException {
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(null);

        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        assertEquals(1, result.size());

        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("nowdate", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testNursenameNotMustMoreThen30String() throws ParseException { // เช็ค Nursename ต้องไม่เกิน 30 ตัวอักษร
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดีดวงดีดวงดีดวงดีดวง");
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);
 
        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("size must be between 1 and 30", message.getMessage());
        assertEquals("nursename", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testAddressNotMustMoreThen50String() throws ParseException { //เช็ค Address ต้องไม่เกิน 50 ตัวอักษร
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress("15333 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ จ.หนองคาย 43130");
        nurse.setTelephone("0880517942");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);
 
        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);
        
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Nurse> message = result.iterator().next();
        assertEquals("size must be between 1 and 50", message.getMessage());
        assertEquals("address", message.getPropertyPath().toString());
    }

    @Test
    void B5802111_testTelephoneMustNotBe9Digits() throws ParseException { // เช็ค Telephon ต้องเป็น 10 หลักเท่านั้น
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("088051794");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);
 
        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Nurse> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }

    @Test
    void B5802111_testTelephoneMustNotBe11Digits() throws ParseException { // เช็ค Telephon ต้องเป็น 10 หลักเท่านั้น
        Nurse nurse = new Nurse();
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        java.util.Date date = simpleDateFormat.parse("1996-08-02");
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("2020-01-23");
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        Tier tier = tierRepository.findByTierid(1);

        nurse.setNursename("ศราวุฒิ ดวงดี");
        nurse.setBirthday(date);
        nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse.setTelephone("08805179422");
        nurse.setEmail("sarawutduangdee@gmail.com");
        nurse.setGenderid(gender);
        nurse.setProvinceid(province);
        nurse.setEducationalid(educational);
        nurse.setTierid(tier);
        nurse.setNowdate(date1);
 
        Set<ConstraintViolation<Nurse>> result = validator.validate(nurse);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Nurse> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }

    @Test
    void B5802111_testTelephoneMustBeUnique() throws ParseException {  // เช็ค Telephon ต้องไม่ซ้ำกัน
        // สร้าง nurse object
        Nurse nurse1 = new Nurse();
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("1996-08-02");
        String pattern2 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
        java.util.Date date2 = simpleDateFormat2.parse("2020-01-23");
        Gender gender1 = genderRepository.findByGenderid(1);
        Province province1 = provinceRepository.findByProvinceid(1);
        Educational educational1 = educationalRepository.findByEducationalid(1);
        Tier tier1 = tierRepository.findByTierid(1);

        nurse1.setNursename("ศราวุฒิ ดวงดี");
        nurse1.setBirthday(date1);
        nurse1.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse1.setTelephone("0880517942");
        nurse1.setEmail("sarawutduangdee@gmail.com");
        nurse1.setGenderid(gender1);
        nurse1.setProvinceid(province1);
        nurse1.setEducationalid(educational1);
        nurse1.setTierid(tier1);
        nurse1.setNowdate(date2);
        nurseRepository.saveAndFlush(nurse1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง nurse object ตัวที่ 2
            Nurse nurse2 = new Nurse();
            String pattern3 ="yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(pattern3);
            java.util.Date date3 = simpleDateFormat3.parse("1996-08-02");
            String pattern4 ="yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat(pattern4);
            java.util.Date date4 = simpleDateFormat4.parse("2020-01-23");
            Gender gender2 = genderRepository.findByGenderid(1);
            Province province2 = provinceRepository.findByProvinceid(1);
            Educational educational2 = educationalRepository.findByEducationalid(1);
            Tier tier2 = tierRepository.findByTierid(1);

            nurse2.setNursename("ศราวุฒิ ดวงมี");
            nurse2.setBirthday(date3);
            nurse2.setAddress("351 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
            nurse2.setTelephone("0880517942");
            nurse2.setEmail("sarawutduangmee@gmail.com");
            nurse2.setGenderid(gender2);
            nurse2.setProvinceid(province2);
            nurse2.setEducationalid(educational2);
            nurse2.setTierid(tier2);
            nurse2.setNowdate(date4);
            nurseRepository.saveAndFlush(nurse2);
        });
    }

    @Test
    void B5802111_testEmailMustBeUnique() throws ParseException {  // เช็ค Email ต้องไม่ซ้ำกัน
        // สร้าง nurse object
        Nurse nurse1 = new Nurse();
        String pattern1 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        java.util.Date date1 = simpleDateFormat1.parse("1996-08-02");
        String pattern2 ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
        java.util.Date date2 = simpleDateFormat2.parse("2020-01-23");
        Gender gender1 = genderRepository.findByGenderid(1);
        Province province1 = provinceRepository.findByProvinceid(1);
        Educational educational1 = educationalRepository.findByEducationalid(1);
        Tier tier1 = tierRepository.findByTierid(1);

        nurse1.setNursename("ศราวุฒิ ดวงดี");
        nurse1.setBirthday(date1);
        nurse1.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        nurse1.setTelephone("0880517942");
        nurse1.setEmail("sarawutduangdee@gmail.com");
        nurse1.setGenderid(gender1);
        nurse1.setProvinceid(province1);
        nurse1.setEducationalid(educational1);
        nurse1.setTierid(tier1);
        nurse1.setNowdate(date2);
        nurseRepository.saveAndFlush(nurse1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง nurse object ตัวที่ 2
            Nurse nurse2 = new Nurse();
            String pattern3 ="yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(pattern3);
            java.util.Date date3 = simpleDateFormat3.parse("1996-08-02");
            String pattern4 ="yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat(pattern4);
            java.util.Date date4 = simpleDateFormat4.parse("2020-01-23");
            Gender gender2 = genderRepository.findByGenderid(1);
            Province province2 = provinceRepository.findByProvinceid(1);
            Educational educational2 = educationalRepository.findByEducationalid(1);
            Tier tier2 = tierRepository.findByTierid(1);

            nurse2.setNursename("ศราวุฒิ ดวงดี");
            nurse2.setBirthday(date3);
            nurse2.setAddress("351 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
            nurse2.setTelephone("0880517941");
            nurse2.setEmail("sarawutduangdee@gmail.com");
            nurse2.setGenderid(gender2);
            nurse2.setProvinceid(province2);
            nurse2.setEducationalid(educational2);
            nurse2.setTierid(tier2);
            nurse2.setNowdate(date4);
            nurseRepository.saveAndFlush(nurse2);
        });
    }

}