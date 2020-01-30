package com.sut.se.g13;

import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVueApplication.class, args);
	}

	@Bean
	ApplicationRunner init(AmbulanceRepository ambulanceRepository, StatusRepository statusRepository,
			DriverRegisRepository driverRegisRepository, MileRepository mileRepository,
			ProvinceRepository provinceRepository, AmbulanceBrandRepository ambulanceBrandRepository,
			GenderRepository genderRepository, PositionDriverRepository positiondriverRepository,
			SymptomRepository symptomRepository, EducationalRepository educationalRepository,
			AmbulanceTypeRepository ambulanceTypeRepository, EmployeeRepository employeeRepository,
			TierRepository tierRepository,NurseRepository nurseRepository) {
		return args -> {

			Stream.of("กรุงเทพมหานคร", "กระบี่", "กาญจนบุรี", "กาฬสินธุ์", "กำแพงเพชร", "ขอนแก่น", "จันทบุรี",
					"ฉะเชิงเทรา", "ชลบุรี", "ชัยนาท", "ชัยภูมิ", "ชุมพร", "เชียงราย", "เชียงใหม่", "ตรัง", "ตราด",
					"ตาก", "นครนายก", "นครปฐม", "นครพนม", "นครราชสีมา", "นครศรีธรรมราช", "นครสวรรค์", "นนทบุรี",

					"นราธิวาส", "น่าน", "บึงกาฬ", "บุรีรัมย์", "ปทุมธานี", "ประจวบคีรีขันธ์", "ปราจีนบุรี", "ปัตตานี",
					"พระนครศรีอยุธยา", "พังงา", "พัทลุง", "พิจิตร", "พิษณุโลก", "เพชรบุรี", "เพชรบูรณ์", "แพร่",
					"พะเยา", "ภูเก็ต", "มหาสารคาม", "มุกดาหาร", "แม่ฮ่องสอน", "ยะลา", "ยโสธร", "ร้อยเอ็ด", "ระนอง",
					"ระยอง", "ราชบุรี", "ลพบุรี", "ลำปาง", "ลำพูน", "เลย", "ศรีสะเกษ", "สกลนคร", "สงขลา", "สตูล",
					"สมุทรปราการ", "สมุทรสงคราม", "สมุทรสาคร", "สระแก้ว", "สระบุรี", "สิงห์บุรี", "สุโขทัย",
					"สุพรรณบุรี", "สุราษฎร์ธานี", "สุรินทร์", "หนองคาย", "หนองบัวลำภู", "อ่างทอง", "อุดรธานี",
					"อุทัยธานี", "อุตรดิตถ์", "อุบลราชธานี", "อำนาจเจริญ").forEach(pname -> {
						Province province = new Province();
						province.setProvincename(pname);
						provinceRepository.save(province);
					});

			Stream.of("TOYOTA", "HONDA", "BENZ", "ISUZU", "FORD", "HYUNDAI", "MITSUBISHI", "CHEVROLET", "other")
					.forEach(brand -> {
						AmbulanceBrand ambulancebrand = new AmbulanceBrand();
						ambulancebrand.setAmbrand(brand);
						ambulanceBrandRepository.save(ambulancebrand);
					});

			Stream.of("Basic Life Support", "Intermediate Life Support", "Advanced Life Support").forEach(Type -> {
				AmbulanceType ambulancetype = new AmbulanceType();
				ambulancetype.setAmtype(Type);
				ambulanceTypeRepository.save(ambulancetype);
			});

			Stream.of("พร้อมใช้งาน", "ไม่พร้อมใช้งาน").forEach(name -> {
				Status status = new Status(); // สร้าง Object Status
				status.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Status
				statusRepository.save(status); // บันทึก Objcet ชื่อ Status
			});

			Stream.of("0 - 2000", "2001 - 5000", "5001 - 10000", "10001 -20000", "20001 ขึ้นไป").forEach(name -> {
				Mile mile = new Mile(); // สร้าง Object Mile
				mile.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Mile
				mileRepository.save(mile); // บันทึก Objcet ชื่อ Mile
			});

			Stream.of("Male", "Female", "Another").forEach(sex -> {
				Gender genders = new Gender();
				genders.setSex(sex);
				genderRepository.save(genders);
			});

			Stream.of("พนักงานขับฝึกหัด", "พนักงานขับทั่วไป", "พนักงานขับเชี่ยวชาญ").forEach(positionlevel -> {
				PositionDriver positiondrivers = new PositionDriver();
				positiondrivers.setPositionlevel(positionlevel);
				positiondriverRepository.save(positiondrivers);
			});

			Stream.of("มัธยมศึกษาตอนปลาย", "อนุปริญญา", "ปริญญาตรี").forEach(educationallevel -> {
				Educational educationals = new Educational();
				educationals.setEducationallevel(educationallevel);
				educationalRepository.save(educationals);
			});

			Stream.of("หัวใจหยุดเต้น", "อวัยวะฉีกขาด ", "สติเปลี่ยนไป", "ไม่สามารถหายใจได้ปกติ",
					"ระบบไหลเวียนเลือดวิกฤต", "อาการอื่นๆ ").forEach(type -> {
						Symptom symptom = new Symptom(); // สร้าง Object Symptom
						symptom.setType(type); // set ชื่อ (type) ให้ Object ชื่อ Symptom
						symptomRepository.save(symptom); // บันทึก Objcet ชื่อ Symptom
			});
			
			Stream.of("หัวใจหยุดเต้น", "อวัยวะฉีกขาด ", "สติเปลี่ยนไป", "ไม่สามารถหายใจได้ปกติ",
					"ระบบไหลเวียนเลือดวิกฤต", "อาการอื่นๆ ").forEach(type -> {
						Symptom symptom = new Symptom(); // สร้าง Object Symptom
						symptom.setType(type); // set ชื่อ (type) ให้ Object ชื่อ Symptom
						symptomRepository.save(symptom); // บันทึก Objcet ชื่อ Symptom
			});

			Stream.of("C1", "C2 ", "C3", "C4", "C5").forEach(tiername -> {
						Tier tier = new Tier();
						tier.setTiername(tiername);
						tierRepository.save(tier);
			});

			Employee infoemp1 = new Employee();
			infoemp1.setUsername("G13");
			infoemp1.setPassword("SE2562");
			employeeRepository.save(infoemp1);

			Employee infoemp2 = new Employee();
			infoemp2.setUsername("Bank");
			infoemp2.setPassword("031239");
			employeeRepository.save(infoemp2);

			java.util.Date date = new java.util.Date(2020-01-21);
			AmbulanceBrand brand = ambulanceBrandRepository.findByBrandid(1);
    		AmbulanceType type = ambulanceTypeRepository.findByTypeid(1);
			Province province = provinceRepository.findByProvinceid(1);
			Ambulance ambulance = new Ambulance();
    		ambulance.setEnginenum("JS150MM07777");
    		ambulance.setAmbulancemodel("Dmax");
    		ambulance.setAmbulancenum("ML2S150CMHTP07777");
    		ambulance.setLicenseplate("1กว 4451");
    		ambulance.setAdddate(date);
    		ambulance.setBrandid(brand);
    		ambulance.setTypeid(type);
    		ambulance.setProvinceid(province);
    		ambulanceRepository.save(ambulance);
			
			Gender gender = genderRepository.findByGenderid(1);
    		Educational educational = educationalRepository.findByEducationalid(1);
    		PositionDriver positiondriver = positiondriverRepository.findByPositionDriverid(1);

    		DriverRegis driverRegis = new DriverRegis();
   			driverRegis.setDrivername("BennyBenBen");
   			driverRegis.setIdcard("1309901281917");
   			driverRegis.setTelephone("0935081634");
   			driverRegis.setAddress("445 ม.17 ต.แสนสุข อ.วารินชำราบ 34190");
   			driverRegis.setGenderid(gender);
   			driverRegis.setProvinceid(province);
			driverRegis.setEducationalid(educational);
			driverRegis.setEducationalid(educational);
			driverRegis.setPositionid(positiondriver);
			driverRegis.setBirthday(date);
   			driverRegisRepository.save(driverRegis);
			   
			Tier tier = tierRepository.findByTierid(1);
			Nurse nurse = new Nurse();
        	nurse.setNursename("ศราวุฒิ ดวงดี");
        	nurse.setAddress("153 ม.7 ต.บ้านหม้อ อ.ศรีเชียงใหม่ 43130");
        	nurse.setTelephone("0880517900");
        	nurse.setEmail("sarawutduangdee12@gmail.com");
        	nurse.setGenderid(gender);
        	nurse.setProvinceid(province);
        	nurse.setEducationalid(educational);
        	nurse.setTierid(tier);
			nurse.setNowdate(date);
			nurse.setBirthday(date);
			nurseRepository.save(nurse);
		};
	}
}