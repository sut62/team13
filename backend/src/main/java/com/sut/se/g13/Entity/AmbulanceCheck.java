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
@Table(name="AMBULANCECHECK")
public class AmbulanceCheck  {
  @Id
  @SequenceGenerator(name="AMBULANCECHECK_SEQ",sequenceName="AMBULANCECHECK_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AMBULANCECHECK_SEQ" )
  @Column(name = "ID_AMBULANCECHECK", unique = true, nullable = true)
  private @NonNull Long id;
  
  @Column(name="CHECK_DATE")
  private @NonNull Date checkDate;

  @Column(name="CHECK_BATTERY")
  private @NonNull String battery;

  @Column(name="CHECK_LIGHT")
  private @NonNull String light;

  @Column(name="CHECK_WHEEL")
  private @NonNull String wheel;

  @Column(name="CHECK_SUSPENSION")
  private @NonNull String suspension;

  @Column(name="CHECK_BRAKE_FLUID")
  private @NonNull String brakeFluid;

  @Column(name="CHECK_ENGINE_OIL")
  private @NonNull String engineOil;

  @Column(name="CHECK_RADIATOR")
  private @NonNull String radiator;

  @Column(name="CHECK_TOOL_SET")
  private @NonNull String toolSet;

  /*@ManyToOne(fetch = FetchType.EAGER, targetEntity = Ambulance.class)
  @JoinColumn(name = "ID_AMBULANCE", insertable = true)
  private Ambulance ambulance;*/

  /*@ManyToOne(fetch = FetchType.EAGER, targetEntity = Mile.class)
  @JoinColumn(name = "ID_MILE", insertable = true)
  private Mile mile;*/

  /*@ManyToOne(fetch = FetchType.EAGER, targetEntity = Driver.class)
  @JoinColumn(name = "ID_DRIVER", insertable = true)
  private Driver driver;*/

  /*@ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
  @JoinColumn(name = "ID_STATUS", insertable = true)
  private Status status;*/
}