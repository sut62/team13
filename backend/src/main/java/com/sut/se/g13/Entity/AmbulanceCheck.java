package com.sut.se.g13.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@Table(name="AMBULANCECHECK")
public class AmbulanceCheck  {
  @Id
  @SequenceGenerator(name="AMBULANCECHECK_SEQ",sequenceName="AMBULANCECHECK_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AMBULANCECHECK_SEQ" )
  @Column(name = "ID_AMBULANCECHECK", unique = true, nullable = true)
  private @NonNull Long id;
  
  @NotNull
  @Size(max = 100)
  private String note;

  @Temporal(TemporalType.DATE)
  @NotNull
  @Column(name="CHECK_DATE")
  private Date checkDate ;

  @NotNull
  @Column(name="CHECK_BATTERY")
  private Boolean battery;

  @NotNull
  @Column(name="CHECK_LIGHT")
  private  Boolean light;

  @NotNull
  @Column(name="CHECK_WHEEL")
  private  Boolean wheel;

  @NotNull
  @Column(name="CHECK_SUSPENSION")
  private  Boolean suspension;

  @NotNull
  @Column(name="CHECK_BRAKE_FLUID")
  private Boolean brakeFluid;

  @NotNull
  @Column(name="CHECK_ENGINE_OIL")
  private Boolean engineOil;

  @NotNull
  @Column(name="CHECK_RADIATOR")
  private  Boolean radiator;

  @NotNull
  @Column(name="CHECK_TOOL_SET")
  private Boolean toolSet;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ambulance.class)
  @JoinColumn(name = "ID_AMBULANCE", insertable = true)
  private Ambulance ambulance;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Mile.class)
  @JoinColumn(name = "ID_MILE", insertable = true)
  private Mile mile;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = DriverRegis.class)
  @JoinColumn(name = "ID_DRIVERREGIS", insertable = true)
  private DriverRegis driverRegis;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
  @JoinColumn(name = "ID_STATUS", insertable = true)
  private Status status;
}