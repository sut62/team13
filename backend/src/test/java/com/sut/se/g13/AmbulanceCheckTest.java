package com.sut.se.g13;

import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

//ถ้าจะเช็คทั้งหมด รวมcomboboxที่ดึงเพื่อนมาใช้ด้วย ต้องTestทีละอัน (อย่าลืมเปิด @NotNull ใน Entity)
@DataJpaTest
public class AmbulanceCheckTest {
    private Validator validator;

    @Autowired private AmbulanceCheckRepository ambulanceCheckRepository;
    @Autowired private MileRepository mileRepository;
    @Autowired private StatusRepository statusRepository;

    @Autowired private AmbulanceRepository ambulanceRepository;
    @Autowired private AmbulanceBrandRepository ambulanceBrandRepository;
    @Autowired private AmbulanceTypeRepository ambulanceTypeRepository;

    @Autowired private ProvinceRepository provinceRepository;

    @Autowired private  DriverRegisRepository driverRegisRepository;
    @Autowired private  EducationalRepository educationalRepository;
    @Autowired private  GenderRepository genderRepository;
    @Autowired private  PositionDriverRepository positiondriverRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    //ใส่ข้อมูลถูกต้องปกติ
    void B5809844_testAmbulanceCheckwithCorrect() throws ParseException{
        AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
        Mile mile = mileRepository.findByMileid(1);
        Status status = statusRepository.findByStatusid(1);

        java.util.Date date = new java.util.Date(2020-01-21);

    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

    //     Province province = provinceRepository.findByProvinceid(1);

    //     Gender gender = genderRepository.findByGenderid(1);
    //     Educational educational = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    //      Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //      Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    //      DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //     DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

        ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
        ambulanceCheck.setCheckDate(date);
        ambulanceCheck.setBattery(true);
        ambulanceCheck.setLight(true);
        ambulanceCheck.setWheel(true);
        ambulanceCheck.setSuspension(true);
        ambulanceCheck.setBrakeFluid(true);
        ambulanceCheck.setEngineOil(true);
        ambulanceCheck.setRadiator(true);
        ambulanceCheck.setToolSet(true);
        ambulanceCheck.setMile(mile);
        ambulanceCheck.setStatus(status);
        // ambulanceCheck.setAmbulance(am);
        // ambulanceCheck.setDriverRegis(dr);

        ambulanceCheckRepository.saveAndFlush(ambulanceCheck);

        Optional<AmbulanceCheck> check = ambulanceCheckRepository.findById(ambulanceCheck.getId());
        assertEquals("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ", check.get().getNote());
        assertEquals(ambulanceCheck.getCheckDate(), check.get().getCheckDate());
        assertEquals(true, check.get().getBattery());
        assertEquals(true, check.get().getLight());
        assertEquals(true, check.get().getWheel());
        assertEquals(true, check.get().getSuspension());
        assertEquals(true, check.get().getBrakeFluid());
        assertEquals(true, check.get().getEngineOil());
        assertEquals(true, check.get().getRadiator());
        assertEquals(true, check.get().getToolSet());
        assertEquals(mile, check.get().getMile());
        assertEquals(status, check.get().getStatus());
        // assertEquals(am, check.get().getAmbulance());
        // assertEquals(dr, check.get().getDriverRegis());
    }

    @Test
    //ป้องกันการใส่ค่า null ของ Battery
    void B5809844_testAmbulanceCheckwithBatteryNotNull() throws ParseException{
        AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
        Mile mile = mileRepository.findByMileid(1);
        Status status = statusRepository.findByStatusid(1);

        java.util.Date date = new java.util.Date(2020-01-21);

    //     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    //     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

    //     Province province = provinceRepository.findByProvinceid(1);

    //     Gender gender = genderRepository.findByGenderid(1);
    //     Educational educational = educationalRepository.findByEducationalid(1);
    //     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    //     Ambulance ambulance = new Ambulance();
    //     ambulance.setEnginenum("JS150MM03731");
    //     ambulance.setAmbulancemodel("Dmax");
    //     ambulance.setAmbulancenum("ML2S150CMHTP03731");
    //     ambulance.setLicenseplate("1กว 4451");
    //     ambulance.setAdddate(date);
    //     ambulance.setBrandid(brand);
    //     ambulance.setTypeid(type);
    //     ambulance.setProvinceid(province);
    //     ambulanceRepository.save(ambulance);
    //     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

    //     DriverRegis driverRegis = new DriverRegis();
    //    driverRegis.setDrivername("BennyBenBen");
    //    driverRegis.setIdcard("1349900799556");
    //    driverRegis.setTelephone("0927378868");
    //    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
    //    driverRegis.setGenderid(gender);
    //    driverRegis.setProvinceid(province);
    //    driverRegis.setEducationalid(educational);
    //    driverRegis.setPositionid(positiondriver);
    //    driverRegisRepository.save(driverRegis);
    //    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

        ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
        ambulanceCheck.setCheckDate(date);
        ambulanceCheck.setBattery(null);
        ambulanceCheck.setLight(true);
        ambulanceCheck.setWheel(true);
        ambulanceCheck.setSuspension(true);
        ambulanceCheck.setBrakeFluid(true);
        ambulanceCheck.setEngineOil(true);
        ambulanceCheck.setRadiator(true);
        ambulanceCheck.setToolSet(true);
        ambulanceCheck.setMile(mile);
        ambulanceCheck.setStatus(status);
        // ambulanceCheck.setAmbulance(am);
        // ambulanceCheck.setDriverRegis(dr);
        Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

        assertEquals(1, result.size());

        ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("battery", message.getPropertyPath().toString());
    }

   @Test
   //ป้องกันการใส่ค่า null ของ Light
   void B5809844_testAmbulanceCheckwithLightNotNull() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Mile mile = mileRepository.findByMileid(1);
    Status status = statusRepository.findByStatusid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
    ambulanceCheck.setCheckDate(date);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(null);
    ambulanceCheck.setWheel(true);
    ambulanceCheck.setSuspension(true);
    ambulanceCheck.setBrakeFluid(true);
    ambulanceCheck.setEngineOil(true);
    ambulanceCheck.setRadiator(true);
    ambulanceCheck.setToolSet(true);
    ambulanceCheck.setMile(mile);
    ambulanceCheck.setStatus(status);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);

       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       assertEquals(1, result.size());

       ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
       assertEquals("must not be null", message.getMessage());
       assertEquals("light", message.getPropertyPath().toString());
   }

   @Test
   //ป้องกันการใส่ค่า null ของ Wheel
   void B5809844_testAmbulanceCheckwithWheelNotNull() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Mile mile = mileRepository.findByMileid(1);
    Status status = statusRepository.findByStatusid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
    ambulanceCheck.setCheckDate(date);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(true);
    ambulanceCheck.setWheel(null);
    ambulanceCheck.setSuspension(true);
    ambulanceCheck.setBrakeFluid(true);
    ambulanceCheck.setEngineOil(true);
    ambulanceCheck.setRadiator(true);
    ambulanceCheck.setToolSet(true);
    ambulanceCheck.setMile(mile);
    ambulanceCheck.setStatus(status);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);

       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       assertEquals(1, result.size());

       ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
       assertEquals("must not be null", message.getMessage());
       assertEquals("wheel", message.getPropertyPath().toString());
   }

   @Test
   //ป้องกันการใส่ค่า null ของ Suspension
   void B5809844_testAmbulanceCheckwithSuspensionNotNull() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Mile mile = mileRepository.findByMileid(1);
    Status status = statusRepository.findByStatusid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
    ambulanceCheck.setCheckDate(date);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(true);
    ambulanceCheck.setWheel(true);
    ambulanceCheck.setSuspension(null);
    ambulanceCheck.setBrakeFluid(true);
    ambulanceCheck.setEngineOil(true);
    ambulanceCheck.setRadiator(true);
    ambulanceCheck.setToolSet(true);
    ambulanceCheck.setMile(mile);
    ambulanceCheck.setStatus(status);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);

       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       assertEquals(1, result.size());

       ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
       assertEquals("must not be null", message.getMessage());
       assertEquals("suspension", message.getPropertyPath().toString());
   }

   @Test
   //ป้องกันการใส่ค่า null ของ BrakeFluid
   void B5809844_testAmbulanceCheckwithBrakeFluidNotNull() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Mile mile = mileRepository.findByMileid(1);
    Status status = statusRepository.findByStatusid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
    ambulanceCheck.setCheckDate(date);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(true);
    ambulanceCheck.setWheel(true);
    ambulanceCheck.setSuspension(true);
    ambulanceCheck.setBrakeFluid(null);
    ambulanceCheck.setEngineOil(true);
    ambulanceCheck.setRadiator(true);
    ambulanceCheck.setToolSet(true);
    ambulanceCheck.setMile(mile);
    ambulanceCheck.setStatus(status);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);

       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       assertEquals(1, result.size());

       ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
       assertEquals("must not be null", message.getMessage());
       assertEquals("brakeFluid", message.getPropertyPath().toString());
   }

   @Test
   //ป้องกันการใส่ค่า null ของ EngineOil
   void B5809844_testAmbulanceCheckwithEngineOilNotNull() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Mile mile = mileRepository.findByMileid(1);
    Status status = statusRepository.findByStatusid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
    ambulanceCheck.setCheckDate(date);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(true);
    ambulanceCheck.setWheel(true);
    ambulanceCheck.setSuspension(true);
    ambulanceCheck.setBrakeFluid(true);
    ambulanceCheck.setEngineOil(null);
    ambulanceCheck.setRadiator(true);
    ambulanceCheck.setToolSet(true);
    ambulanceCheck.setMile(mile);
    ambulanceCheck.setStatus(status);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);

       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       assertEquals(1, result.size());

       ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
       assertEquals("must not be null", message.getMessage());
       assertEquals("engineOil", message.getPropertyPath().toString());
   }

   @Test
   //ป้องกันการใส่ค่า null ของ Radiator
   void B5809844_testAmbulanceCheckwithRadiatorNotNull() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Mile mile = mileRepository.findByMileid(1);
    Status status = statusRepository.findByStatusid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
    ambulanceCheck.setCheckDate(date);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(true);
    ambulanceCheck.setWheel(true);
    ambulanceCheck.setSuspension(true);
    ambulanceCheck.setBrakeFluid(true);
    ambulanceCheck.setEngineOil(true);
    ambulanceCheck.setRadiator(null);
    ambulanceCheck.setToolSet(true);
    ambulanceCheck.setMile(mile);
    ambulanceCheck.setStatus(status);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);

       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       assertEquals(1, result.size());

       ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
       assertEquals("must not be null", message.getMessage());
       assertEquals("radiator", message.getPropertyPath().toString());
   }

   @Test
   //ป้องกันการใส่ค่า null ของ ToolSet
   void B5809844_testAmbulanceCheckwithToolSetNotNull() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Mile mile = mileRepository.findByMileid(1);
    Status status = statusRepository.findByStatusid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
    ambulanceCheck.setCheckDate(date);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(true);
    ambulanceCheck.setWheel(true);
    ambulanceCheck.setSuspension(true);
    ambulanceCheck.setBrakeFluid(true);
    ambulanceCheck.setEngineOil(true);
    ambulanceCheck.setRadiator(true);
    ambulanceCheck.setToolSet(null);
    ambulanceCheck.setMile(mile);
    ambulanceCheck.setStatus(status);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);

       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       assertEquals(1, result.size());

       ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
       assertEquals("must not be null", message.getMessage());
       assertEquals("toolSet", message.getPropertyPath().toString());
   }

   @Test
   //ป้องกันการใส่ค่า null ของ Note
   void B5809844_testAmbulanceCheckwithNoteNotNull() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Mile mile = mileRepository.findByMileid(1);
    Status status = statusRepository.findByStatusid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote(null);
    ambulanceCheck.setCheckDate(date);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(true);
    ambulanceCheck.setWheel(true);
    ambulanceCheck.setSuspension(true);
    ambulanceCheck.setBrakeFluid(true);
    ambulanceCheck.setEngineOil(true);
    ambulanceCheck.setRadiator(true);
    ambulanceCheck.setToolSet(true);
    ambulanceCheck.setMile(mile);
    ambulanceCheck.setStatus(status);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);

       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       assertEquals(1, result.size());

       ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
       assertEquals("must not be null", message.getMessage());
       assertEquals("note", message.getPropertyPath().toString());
   }

   @Test
   //ป้องกันการใส่ค่า null ของ CheckDate
   void B5809844_testAmbulanceCheckwithCheckDateNotNull() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Mile mile = mileRepository.findByMileid(1);
    Status status = statusRepository.findByStatusid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
    ambulanceCheck.setCheckDate(null);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(true);
    ambulanceCheck.setWheel(true);
    ambulanceCheck.setSuspension(true);
    ambulanceCheck.setBrakeFluid(true);
    ambulanceCheck.setEngineOil(true);
    ambulanceCheck.setRadiator(true);
    ambulanceCheck.setToolSet(true);
    ambulanceCheck.setMile(mile);
    ambulanceCheck.setStatus(status);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);
       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       assertEquals(1, result.size());

       ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
       assertEquals("must not be null", message.getMessage());
       assertEquals("checkDate", message.getPropertyPath().toString());
   }

   @Test
   //ป้องกันการใส่ค่า null ของ Mile
   void B5809844_testAmbulanceCheckwithMileNotNull() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Status status = statusRepository.findByStatusid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
    ambulanceCheck.setCheckDate(date);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(true);
    ambulanceCheck.setWheel(true);
    ambulanceCheck.setSuspension(true);
    ambulanceCheck.setBrakeFluid(true);
    ambulanceCheck.setEngineOil(true);
    ambulanceCheck.setRadiator(true);
    ambulanceCheck.setToolSet(true);
    ambulanceCheck.setMile(null);
    ambulanceCheck.setStatus(status);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);
       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       assertEquals(1, result.size());

       ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
       assertEquals("must not be null", message.getMessage());
       assertEquals("mile", message.getPropertyPath().toString());
   }

   @Test
   //ป้องกันการใส่ค่า null ของ Status
   void B5809844_testAmbulanceCheckwithStatusNotNull() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Mile mile = mileRepository.findByMileid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
    ambulanceCheck.setCheckDate(date);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(true);
    ambulanceCheck.setWheel(true);
    ambulanceCheck.setSuspension(true);
    ambulanceCheck.setBrakeFluid(true);
    ambulanceCheck.setEngineOil(true);
    ambulanceCheck.setRadiator(true);
    ambulanceCheck.setToolSet(true);
    ambulanceCheck.setMile(mile);
    ambulanceCheck.setStatus(null);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);
       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       assertEquals(1, result.size());

       ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
       assertEquals("must not be null", message.getMessage());
       assertEquals("status", message.getPropertyPath().toString());
   }

//    @Test
//     //ป้องกันการใส่ค่า null ของ Ambulance(combobox ที่ดึงจากเพื่อน)
//    void B5809844_testAmbulanceCheckwithAmbulanceNotNull() throws ParseException{
//     AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
//     Mile mile = mileRepository.findByMileid(1);
//     Status status = statusRepository.findByStatusid(1);

//     java.util.Date date = new java.util.Date(2020-01-21);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

//     ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
//     ambulanceCheck.setCheckDate(date);
//     ambulanceCheck.setBattery(true);
//     ambulanceCheck.setLight(true);
//     ambulanceCheck.setWheel(true);
//     ambulanceCheck.setSuspension(true);
//     ambulanceCheck.setBrakeFluid(true);
//     ambulanceCheck.setEngineOil(true);
//     ambulanceCheck.setRadiator(true);
//     ambulanceCheck.setToolSet(true);
//     ambulanceCheck.setMile(mile);
//     ambulanceCheck.setStatus(status);
//     ambulanceCheck.setAmbulance(null);
//     ambulanceCheck.setDriverRegis(dr);
//        Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

//        assertEquals(1, result.size());

//        ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
//        assertEquals("must not be null", message.getMessage());
//        assertEquals("ambulance", message.getPropertyPath().toString());
//    }

//    @Test
//     //ป้องกันการใส่ค่า null ของ DriverRegis(combobox ที่ดึงจากเพื่อน)
//    void B5809844_testAmbulanceCheckwithDriverRegisNotNull() throws ParseException{
//     AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
//     Mile mile = mileRepository.findByMileid(1);
//     Status status = statusRepository.findByStatusid(1);

//     java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     ambulanceCheck.setNote("สีของรถพยาบาลมีรอยถลอกด้านข้างทางขวาข้างคนขับ");
//     ambulanceCheck.setCheckDate(date);
//     ambulanceCheck.setBattery(true);
//     ambulanceCheck.setLight(true);
//     ambulanceCheck.setWheel(true);
//     ambulanceCheck.setSuspension(true);
//     ambulanceCheck.setBrakeFluid(true);
//     ambulanceCheck.setEngineOil(true);
//     ambulanceCheck.setRadiator(true);
//     ambulanceCheck.setToolSet(true);
//     ambulanceCheck.setMile(mile);
//     ambulanceCheck.setStatus(status);
//     ambulanceCheck.setAmbulance(am);
//     ambulanceCheck.setDriverRegis(null);
//        Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

//        assertEquals(1, result.size());

//        ConstraintViolation<AmbulanceCheck> message = result.iterator().next();
//        assertEquals("must not be null", message.getMessage());
//        assertEquals("driverRegis", message.getPropertyPath().toString());
//    }

   @Test
   //ป้องกันการใส่ข้อมูลยาวเกิน100 ของ Note
   void B5809844_testNoteMustMoreThan100String() throws ParseException{
    AmbulanceCheck ambulanceCheck = new AmbulanceCheck();
    Mile mile = mileRepository.findByMileid(1);
    Status status = statusRepository.findByStatusid(1);

    java.util.Date date = new java.util.Date(2020-01-21);

//     AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
//     AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);

//     Province province = provinceRepository.findByProvinceid(1);

//     Gender gender = genderRepository.findByGenderid(1);
//     Educational educational = educationalRepository.findByEducationalid(1);
//     PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

//     Ambulance ambulance = new Ambulance();
//     ambulance.setEnginenum("JS150MM03731");
//     ambulance.setAmbulancemodel("Dmax");
//     ambulance.setAmbulancenum("ML2S150CMHTP03731");
//     ambulance.setLicenseplate("1กว 4451");
//     ambulance.setAdddate(date);
//     ambulance.setBrandid(brand);
//     ambulance.setTypeid(type);
//     ambulance.setProvinceid(province);
//     ambulanceRepository.save(ambulance);
//     Ambulance am = ambulanceRepository.findByAmbulanceid(1);

//     DriverRegis driverRegis = new DriverRegis();
//    driverRegis.setDrivername("BennyBenBen");
//    driverRegis.setIdcard("1349900799556");
//    driverRegis.setTelephone("0927378868");
//    driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
//    driverRegis.setGenderid(gender);
//    driverRegis.setProvinceid(province);
//    driverRegis.setEducationalid(educational);
//    driverRegis.setPositionid(positiondriver);
//    driverRegisRepository.save(driverRegis);
//    DriverRegis dr = driverRegisRepository.findByDriverregisid(1);

    ambulanceCheck.setNote("สีของรถพยาบาลมีร่องรอยของการขูดขีดทำให้สีของรถทางด้านขวาข้างคนขับมีร่องรอยของการขูดขีดและศูนย์ของรถพยาบาลไม่ดีทำให้รถขับไม่คงที่");
    ambulanceCheck.setCheckDate(date);
    ambulanceCheck.setBattery(true);
    ambulanceCheck.setLight(true);
    ambulanceCheck.setWheel(true);
    ambulanceCheck.setSuspension(true);
    ambulanceCheck.setBrakeFluid(true);
    ambulanceCheck.setEngineOil(true);
    ambulanceCheck.setRadiator(true);
    ambulanceCheck.setToolSet(true);
    ambulanceCheck.setMile(mile);
    ambulanceCheck.setStatus(status);
    // ambulanceCheck.setAmbulance(am);
    // ambulanceCheck.setDriverRegis(dr);

       Set<ConstraintViolation<AmbulanceCheck>> result = validator.validate(ambulanceCheck);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<AmbulanceCheck> v = result.iterator().next();
       assertEquals("size must be between 0 and 100", v.getMessage());
       assertEquals("note", v.getPropertyPath().toString());
   }
}