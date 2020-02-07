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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
  private Long nurseid; // ไอดีพยาบาล  

  @Size(min=1, max=30)
  @NotNull 
  private String nursename; // ชื่อ-นามสกุล

  @Temporal(TemporalType.DATE)
  @NotNull
  private Date birthday; // วันเกิด

  @Size(min=1, max=50)
  @NotNull 
  private String address; // ที่อยู่

  @Pattern(regexp = "\\d{10}")
  @NotNull 
  @Column(name = "TELEPHONE", unique = true)
  private String telephone; // เบอร์โทร

  @Email
  @NotNull 
  @Column(name = "EMAIL", unique = true)
  private String email; // เมล

  @Temporal(TemporalType.DATE)
  @NotNull
  private Date nowdate; // เวลาบันทึก

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
  @JoinColumn(name = "GENDER_ID", insertable = true)
  private Gender genderid; // เพศ

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Educational.class)
  @JoinColumn(name = "EDUCATIONAL_ID", insertable = true)
  private Educational educationalid; //ระดับการศึกษา

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Tier.class)
  @JoinColumn(name = "TIER_ID", insertable = true)
  private Tier tierid;  // ขั้นข้าราชการ

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
  @JoinColumn(name = "PROVINCE_ID", insertable = true)
  private Province provinceid; // จังหวัด

}