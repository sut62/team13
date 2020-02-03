package com.sut.se.g13.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.FutureOrPresent;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name="CHECKEQUIPMENT")
public class CheckEquipment  {
  @Id
  @SequenceGenerator(name="CHECKEQUIPMENT_SEQ",sequenceName="CHECKEQUIPMENT_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CHECKEQUIPMENT_SEQ" )
  @Column(name = "ID_CHECKEQUIPMENT", unique = true, nullable = true)
  private @NonNull Long id;
  
  @NotBlank
  @Size (min=4, max=50)
  private String note;   //หมายเหตุ

  @Temporal(TemporalType.DATE)
  @NotNull
  @Column(name="CHECK_DATE")
  private Date checkDate ;   //วันที่เช็ค

  @NotNull
  @Column(name="CHECK_DEFIBRILLATOR")
  private Boolean defibrillator;   //เช็คเครื่องกระตุ้นหัวใจ

  @NotNull
  @Column(name="CHECK_SPHYGMOMANOMETER")
  private Boolean sphygmomanometer;   //เช็คเครื่องวัดความดันโลหิต

  @NotNull
  @Column(name="CHECK_RESPIRATOR")
  private Boolean respirator;   //เช็คเครื่องช่วยหายใจ

  @NotNull
  @Column(name="CHECK_OXYGENMACHINE")
  private Boolean oxygenmachine;   //เช็คเครื่องออกซิเจน

  @NotNull
  @Column(name="CHECK_SUCTION")
  private Boolean suction;   //เช็คเครื่องดูดเสมหะ

  @NotNull
  @Column(name="CHECK_MOTIONDEVICE")
  private Boolean motionDevice;   //เช็คอุปกรณ์เคลื่อนที่

  @NotNull
  @Column(name="CHECK_MEDICALSPRAYER")
  private Boolean medicalSprayer;   //เช็คเครื่องพ่นยาทางการแพทย์


  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ambulance.class)
  @JoinColumn(name = "ID_AMBULANCE", insertable = true)
  @NotNull
  private Ambulance ambulance;   //ไอดีรถพยาบาล

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Nurse.class)
  @JoinColumn(name = "ID_NURSE", insertable = true)
  @NotNull
  private Nurse nurse;   //พยาบาลผู้เช็ค

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
  @JoinColumn(name = "ID_STATUS", insertable = true)
  @NotNull
  private Status status;   //สถานะอุปกรณ์

}