package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "NURSE")
public class Nurse {
  @Id
  @SequenceGenerator(name = "NURSE_SEQ", sequenceName = "NURSE_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NURSE_SEQ")
  @Column(name = "NURSE_ID", unique = true, nullable = true)
  private @NonNull Long nurseid; // ไอดีพยาบาล  
  private @NonNull String nursename; // ชื่อ-นามสกุล
  private @NonNull Date birthday; // วันเกิด
  private @NonNull String address; // ที่อยู่
  private @NonNull String telephone; // เบอร์โทร
  private @NonNull String email; // เมล
  private @NonNull Date nowdate; // เวลาบันทึก

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
  @JoinColumn(name = "GENDER_ID", insertable = true)
  private Gender genderid; // เพศ

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Educational.class)
    @JoinColumn(name = "EDUCATIONAL_ID", insertable = true)
    private Educational educationalid; //ระดับการศึกษา

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Tier.class)
  @JoinColumn(name = "TIER_ID", insertable = true)
  private Tier tierid;  // ขั้นข้าราชการ

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
  @JoinColumn(name = "PROVINCE_ID", insertable = true)
  private Province provinceid; // จังหวัด

}