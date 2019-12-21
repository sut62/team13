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
			AmbulanceTypeRepository ambulanceTypeRepository, EmployeeRepository employeeRepository) {
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

			Stream.of("นายแบงค์", "นายเบ้น", "นายเฉลิมชัย", "นางตั๊กกี้").forEach(drivername -> {
				DriverRegis driverRegis = new DriverRegis(); // สร้าง Object DriverRegis
				driverRegis.setDrivername(drivername); // set ชื่อ (name) ให้ Object ชื่อ DriverRegis
				driverRegisRepository.save(driverRegis); // บันทึก Objcet ชื่อ DriverRegis
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
			Employee infoemp1 = new Employee();
			infoemp1.setUsername("G13");
			infoemp1.setPassword("SE2562");
			employeeRepository.save(infoemp1);

			Employee infoemp2 = new Employee();
			infoemp2.setUsername("Bank");
			infoemp2.setPassword("031239");
			employeeRepository.save(infoemp2);
		};
	}

}