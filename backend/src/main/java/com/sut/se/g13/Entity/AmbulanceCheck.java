package com.sut.se.g13.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Table(name="AMBULANCECHECK")
public class AmbulanceCheck  {
  @Id
  @SequenceGenerator(name="AMBULANCECHECK_SEQ",sequenceName="AMBULANCECHECK_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AMBULANCECHECK_SEQ" )
  @Column(name = "ID_AMBULANCECHECK", unique = true, nullable = true)
  private @NonNull Long id;
  
  @NotNull
  @Size(max = 100)
  private String note;  //หมายเหตุ

  @Temporal(TemporalType.DATE)
  @NotNull
  @Column(name="CHECK_DATE")
  private Date checkDate ;  //วันที่เช็คสภาพรถ

  @NotNull
  @Column(name="CHECK_BATTERY")
  private Boolean battery; //เช็คแบตเตอรี่

  @NotNull
  @Column(name="CHECK_LIGHT")
  private  Boolean light; //เช็คล้อและยาง

  @NotNull
  @Column(name="CHECK_WHEEL")
  private  Boolean wheel; //เช็คช่วงล่างและระบบกันสะเทือน

  @NotNull
  @Column(name="CHECK_SUSPENSION")
  private  Boolean suspension;  //เช็คระดับน้ำมันเบรค

  @NotNull
  @Column(name="CHECK_BRAKE_FLUID")
  private Boolean brakeFluid; //เช็คระบบไฟส่องสว่าง

  @NotNull
  @Column(name="CHECK_ENGINE_OIL")
  private Boolean engineOil;  //เช็คระดับน้ำมันเครื่อง

  @NotNull
  @Column(name="CHECK_RADIATOR")
  private  Boolean radiator;  //เช็คหม้อน้ำและท่อยาง

  @NotNull
  @Column(name="CHECK_TOOL_SET")
  private Boolean toolSet;  //เช็คชุดเครื่องมือประจำรถ

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ambulance.class)
  @JoinColumn(name = "ID_AMBULANCE", insertable = true)
  // @NotNull
  private Ambulance ambulance;  //ข้อมูลทั้งหมดของรถพยาบาล

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Mile.class)
  @JoinColumn(name = "ID_MILE", insertable = true)
  @NotNull
  private Mile mile;  //ระยะของรถ

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = DriverRegis.class)
  @JoinColumn(name = "ID_DRIVERREGIS", insertable = true)
  // @NotNull
  private DriverRegis driverRegis;  //ข้อมูลทั้งหมดของพนักงานคนขับ

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
  @JoinColumn(name = "ID_STATUS", insertable = true)
  @NotNull
  private Status status;  //สถานะ
}