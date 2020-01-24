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
public class DriverRegisTest {
    private Validator validator;

    @Autowired
    private DriverRegisRepository driverRegisRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private EducationalRepository educationalRepository;

    @Autowired
    private PositionDriverRepository positiondriverRepository;


    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5813643_testDriverRegiswithCorrect() { // เช็ค with correct ทั้งหมดของฟิลด์ที่กรอกข้อมูล
        final DriverRegis driverRegis = new DriverRegis();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);
     
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(educational);
        driverRegis.setPositionid(positiondriver);
        

        driverRegisRepository.saveAndFlush(driverRegis);
        final Optional<DriverRegis> check = driverRegisRepository.findById(driverRegis.getDriverregisid());
        assertEquals("BennyBenBen", check.get().getDrivername());
        assertEquals("1349900799556", check.get().getIdcard());
        assertEquals("0927378868", check.get().getTelephone());
        assertEquals("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190", check.get().getAddress());
        assertEquals(gender, check.get().getGenderid());
        assertEquals(province, check.get().getProvinceid());
        assertEquals(educational, check.get().getEducationalid());
        assertEquals(positiondriver, check.get().getPositionid());

    }

    @Test
    void B5813643_testDriverRegiswithDrivernameNotNull() { // เช็คค่า NotNull ชื่อคนขับ
        final DriverRegis driverRegis = new DriverRegis();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);
        
        driverRegis.setDrivername(null);
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(educational);
        driverRegis.setPositionid(positiondriver);
    
        final Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        final ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("drivername", message.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDriverRegiswithIdcardNotNull() { // เช็คค่า NotNull เลขที่บัตรประชาชน
        final DriverRegis driverRegis = new DriverRegis();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);
        
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard(null);
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(educational);
        driverRegis.setPositionid(positiondriver);

        final Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        final ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("idcard", message.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDriverRegiswithTelephoneNotNull() { // เช็คค่า NotNull เบอร์โทรศัพท์มือถือ
        final DriverRegis driverRegis = new DriverRegis();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);
        
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone(null);
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(educational);
        driverRegis.setPositionid(positiondriver);


        final Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        final ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("telephone", message.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDriverRegiswithAddressNotNull() { // เช็คค่า NotNull ที่อยู่
        final DriverRegis driverRegis = new DriverRegis();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);
        
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress(null);
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(educational);
        driverRegis.setPositionid(positiondriver);

        final Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        final ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("address", message.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDrivernameNotMoreThan20String() { // เช็คชื่อคนขับ ห้ามมากกว่า 20 อักษร
        final DriverRegis driverRegis = new DriverRegis();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);
        
        driverRegis.setDrivername("BennyBenBenTuckyTucky");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(educational);
        driverRegis.setPositionid(positiondriver);

        final Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<DriverRegis> v = result.iterator().next();
        assertEquals("size must be between 1 and 20", v.getMessage());
        assertEquals("drivername", v.getPropertyPath().toString());
    }

    @Test
    void B5813643_testIdcardmustbe13String() { // เช็คเลขที่บัตรประชาชน ต้องมี 13 หลักเท่านั้น
        final DriverRegis driverRegis = new DriverRegis();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);
        
        driverRegis.setDrivername("BennyBenBenTuckyTuck");
        driverRegis.setIdcard("13499007995566");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(educational);
        driverRegis.setPositionid(positiondriver);

        final Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<DriverRegis> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("idcard", v.getPropertyPath().toString());
    }

    @Test
    void B5813643_testTelephonemustbe10String() { // เช็คเบอร์มือถือ ต้องมี 10 หลักเท่านั้น
        final DriverRegis driverRegis = new DriverRegis();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);
        
        
        driverRegis.setDrivername("BennyBenBenTuckyTuck");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("09273788688");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(educational);
        driverRegis.setPositionid(positiondriver);

        final Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<DriverRegis> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("telephone", v.getPropertyPath().toString());
    }

    @Test
    void B5813643_testAddressNotMoreThan50String() { // เช็คที่อยู่ มีอักษารได้ไม่เกิน 50 ตัว
        final DriverRegis driverRegis = new DriverRegis();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educational = educationalRepository.findByEducationalid(1);
        PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);
        
        driverRegis.setDrivername("BennyBenBenTuckyTuck");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 หมู่บ้านโสภณ 4 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(educational);
        driverRegis.setPositionid(positiondriver);

        final Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<DriverRegis> v = result.iterator().next();
        assertEquals("size must be between 1 and 50", v.getMessage());
        assertEquals("address", v.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDriverRegiswithAddGenderNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Gender
        DriverRegis driverRegis = new DriverRegis();
        Province province = provinceRepository.findByProvinceid(1);
        Educational educationallevel = educationalRepository.findByEducationalid(1);
        PositionDriver positionlevel = positiondriverRepository.findByPositionDriverid(1);
        
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(null);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(educationallevel);
        driverRegis.setPositionid(positionlevel);
        

        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("genderid", message.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDriverRegiswithAddProvinceNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Province
        DriverRegis driverRegis = new DriverRegis();
        Gender gender = genderRepository.findByGenderid(1);
        Educational educationallevel = educationalRepository.findByEducationalid(1);
        PositionDriver positionlevel = positiondriverRepository.findByPositionDriverid(1);
        
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(null);
        driverRegis.setEducationalid(educationallevel);
        driverRegis.setPositionid(positionlevel);
        

        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("provinceid", message.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDriverRegiswithAddEducationalNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Educational
        DriverRegis driverRegis = new DriverRegis();
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        PositionDriver positionlevel = positiondriverRepository.findByPositionDriverid(1);
        
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(null);
        driverRegis.setPositionid(positionlevel);
        

        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("educationalid", message.getPropertyPath().toString());
    }

    @Test
    void B5813643_testDriverRegiswithAddPositionNotNull() throws ParseException { //ทดสอบห้ามเป็นค่าว่างใน combo box Position
        DriverRegis driverRegis = new DriverRegis();
        
        Gender gender = genderRepository.findByGenderid(1);
        Province province = provinceRepository.findByProvinceid(1);
        Educational educationallevel = educationalRepository.findByEducationalid(1);
        
        driverRegis.setDrivername("BennyBenBen");
        driverRegis.setIdcard("1349900799556");
        driverRegis.setTelephone("0927378868");
        driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
        driverRegis.setGenderid(gender);
        driverRegis.setProvinceid(province);
        driverRegis.setEducationalid(educationallevel);
        driverRegis.setPositionid(null);
        

        Set<ConstraintViolation<DriverRegis>> result = validator.validate(driverRegis);

        assertEquals(1, result.size());

        ConstraintViolation<DriverRegis> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("positionid", message.getPropertyPath().toString());
    }

    @Test //test Id Card (เลขบัตรประชาชน) ไม่ซ้ำกัน
    void B5813643_testIdcardMustBeUnique() throws ParseException{
    // สร้าง DriverRegis object
    DriverRegis driverRegis1 = new DriverRegis();
    Gender gender1 = genderRepository.findByGenderid(1);
    Province province1 = provinceRepository.findByProvinceid(1);
    Educational educationallevel1 = educationalRepository.findByEducationalid(1);
    PositionDriver positiondriver1 = positiondriverRepository.findByPositionDriverid(1);
    driverRegis1.setDrivername("BennyBenBen");
    driverRegis1.setIdcard("1349900799556");
    driverRegis1.setTelephone("0927378868");
    driverRegis1.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    driverRegis1.setGenderid(gender1);
    driverRegis1.setProvinceid(province1);
    driverRegis1.setEducationalid(educationallevel1);
    driverRegis1.setPositionid(positiondriver1);

    driverRegisRepository.saveAndFlush(driverRegis1);

    // DataIntegrityViolationException จะถูก throw
    assertThrows(DataIntegrityViolationException.class, () -> {
    // สร้าง ambulance object ตัวที่ 2

    DriverRegis driverRegis2 = new DriverRegis();
    Gender gender2 = genderRepository.findByGenderid(1);
    Province province2 = provinceRepository.findByProvinceid(1);
    Educational educationallevel2 = educationalRepository.findByEducationalid(1);
    PositionDriver positiondriver2 = positiondriverRepository.findByPositionDriverid(1);
    
    driverRegis2.setDrivername("BennyBenBen");
    driverRegis2.setIdcard("1349900799556");
    driverRegis2.setTelephone("0935676893");
    driverRegis2.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    driverRegis2.setGenderid(gender2);
    driverRegis2.setProvinceid(province2);
    driverRegis2.setEducationalid(educationallevel2);
    driverRegis2.setPositionid(positiondriver2);

    driverRegisRepository.saveAndFlush(driverRegis2);
    });
    }

    @Test //test Telephone (หมายเลขโทรศัพท์) ไม่ซ้ำกัน
    void B5813643_testTelephoneMustBeUnique() throws ParseException{
    // สร้าง DriverRegis object
    DriverRegis driverRegis1 = new DriverRegis();
    Gender gender1 = genderRepository.findByGenderid(1);
    Province province1 = provinceRepository.findByProvinceid(1);
    Educational educationallevel1 = educationalRepository.findByEducationalid(1);
    PositionDriver positiondriver1 = positiondriverRepository.findByPositionDriverid(1);
    driverRegis1.setDrivername("BennyBenBen");
    driverRegis1.setIdcard("1349900799556");
    driverRegis1.setTelephone("0927378868");
    driverRegis1.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    driverRegis1.setGenderid(gender1);
    driverRegis1.setProvinceid(province1);
    driverRegis1.setEducationalid(educationallevel1);
    driverRegis1.setPositionid(positiondriver1);

    driverRegisRepository.saveAndFlush(driverRegis1);

    // DataIntegrityViolationException จะถูก throw
    assertThrows(DataIntegrityViolationException.class, () -> {
    // สร้าง ambulance object ตัวที่ 2

    DriverRegis driverRegis2 = new DriverRegis();
    Gender gender2 = genderRepository.findByGenderid(1);
    Province province2 = provinceRepository.findByProvinceid(1);
    Educational educationallevel2 = educationalRepository.findByEducationalid(1);
    PositionDriver positiondriver2 = positiondriverRepository.findByPositionDriverid(1);
    
    driverRegis2.setDrivername("BennyBenBen");
    driverRegis2.setIdcard("1349900799556");
    driverRegis2.setTelephone("0923455555");
    driverRegis2.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    driverRegis2.setGenderid(gender2);
    driverRegis2.setProvinceid(province2);
    driverRegis2.setEducationalid(educationallevel2);
    driverRegis2.setPositionid(positiondriver2);

    driverRegisRepository.saveAndFlush(driverRegis2);
    });
    }







     
     

}
