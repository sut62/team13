package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.*;
import java.util.*;

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
	private @NonNull Date emDate;

	@Column(name = "EM_PNam")
	private @NonNull String patientName;

	@Column(name = "EM_PAddress")
	private @NonNull String patientAddress;

	@Column(name = "EM_PTel")
	private @NonNull String patientTel;

	@Column(name = "EM_PNote")
	private @NonNull String note;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Symptom.class)
	@JoinColumn(name = "SYMPTOME_ID", insertable = true)
	private Symptom symptom;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Ambulance.class)
	@JoinColumn(name = "AMBULANCE_ID", insertable = true)
	private Ambulance ambulance;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Nurse.class)
	@JoinColumn(name = "NURSE_ID", insertable = true)
	private Nurse nurse;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = DriverRegis.class)
	@JoinColumn(name = "DRIVERREGIS_ID", insertable = true)
	private DriverRegis driverRegis;

}