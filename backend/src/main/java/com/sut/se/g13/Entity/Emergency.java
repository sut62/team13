package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Date;

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
	private Long emergencyId;

	@NotNull
	@Column(name = "EM_DATE")
	@Temporal(TemporalType.DATE)
	private Date emDate;

	@NotNull
	@Column(name = "EM_PNam")
	@Size(min = 3,max = 30)
	private String patientName;

	@NotNull
	@Column(name = "EM_PAddress")
	@Size(min = 3,max = 70)
	private String patientAddress;

	@NotNull
	@Column(name = "EM_PTel" , unique = true)
	@Pattern(regexp="\\d{10}")
	private String patientTel;

	@NotBlank
	@Column(name = "EM_PNote")
	@Size(max = 30)
	private String note;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Symptom.class)
	@JoinColumn(name = "SYMPTOME_ID", insertable = true)
	@NotNull
	private Symptom symptom;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Ambulance.class)
	@JoinColumn(name = "AMBULANCE_ID", insertable = true)
	@NotNull
	private Ambulance ambulance;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Nurse.class)
	@JoinColumn(name = "NURSE_ID", insertable = true)
	@NotNull
	private Nurse nurse;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = DriverRegis.class)
	@JoinColumn(name = "DRIVERREGIS_ID", insertable = true)
	@NotNull
	private DriverRegis driverRegis;

}