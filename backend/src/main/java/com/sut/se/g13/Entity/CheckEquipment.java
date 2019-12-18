package com.sut.se.g13.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

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
  
  @Column(name="CHECK_DATE")
  private @NonNull Date checkDate;

  @Column(name="CHECK_DEFIBRILLATOR")
  private @NonNull Boolean defibrillator;

  @Column(name="CHECK_SPHYGMOMANOMETER")
  private @NonNull Boolean sphygmomanometer;

  @Column(name="CHECK_RESPIRATOR")
  private @NonNull Boolean respirator;

  @Column(name="CHECK_OXYGENMACHINE")
  private @NonNull Boolean oxygenmachine;

  @Column(name="CHECK_SUCTION")
  private @NonNull Boolean suction;

  @Column(name="CHECK_MOTIONDEVICE")
  private @NonNull Boolean motionDevice;

  @Column(name="CHECK_MEDICALSPRAYER")
  private @NonNull Boolean medicalSprayer;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ambulance.class)
  @JoinColumn(name = "ID_AMBULANCE", insertable = true)
  private Ambulance ambulance;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Nurse.class)
  @JoinColumn(name = "ID_NURSE", insertable = true)
  private Nurse Nurse;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
  @JoinColumn(name = "ID_STATUS", insertable = true)
  private Status status;

}