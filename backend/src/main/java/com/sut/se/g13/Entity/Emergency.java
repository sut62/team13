package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.*;


@Data
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "EMERGENCY")
public class Emergency {
	@Id
	@SequenceGenerator(name = "EMERGENCY_SEQ", sequenceName = "EMERGENCY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMERGENCY_SEQ")
	@Column(name = "ID_EMERGENCY", unique = true, nullable = true)
	private @NonNull Long emergencyId;

	@Column(name = "EM_DATE")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date emDate;

	@Column(name = "EM_PNam")
	@Size(max = 30)
	@NotNull
	private String patientName;

	@Column(name = "EM_PAddress")
	@Size(max = 50)
	@NotNull
	private String patientAddress;

	@Column(name = "EM_PTel" , unique = true )
	@Pattern(regexp="\\d{10}")
	@NotNull
	private String patientTel;

	@Column(name = "EM_PNote")
	@Size(max = 30)
	@NotNull
	private String note;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Symptom.class)
	@JoinColumn(name = "SYMPTOME_ID", insertable = true)
	@NotNull
	private Symptom symptom;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Ambulance.class)
	@JoinColumn(name = "AMBULANCE_ID", insertable = true)
	//@NotNull
	private Ambulance ambulance;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Nurse.class)
	@JoinColumn(name = "NURSE_ID", insertable = true)
	private Nurse nurse;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = DriverRegis.class)
	@JoinColumn(name = "DRIVERREGIS_ID", insertable = true)
	//@NotNull
	private DriverRegis driverRegis;

}