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
@NoArgsConstructor
@Table(name = "EMERGENCY")
public class Emergency {
	@Id
	@SequenceGenerator(name = "EMERGENCY_SEQ", sequenceName = "EMERGENCY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMERGENCY_SEQ")
	@Column(name = "ID_EMERGENCY", unique = true, nullable = true)
	private @NonNull Long emergencyId;
	private @NonNull String patientName;
	private @NonNull String patientAddress;
	private @NonNull Number patientTel;
	private @NonNull String note;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Symptom.class)
	@JoinColumn(name = "ID_EMPOLYEE", insertable = true)
	private Symptom symptom;

	@Column(name = "EM_DATE")
	private @NonNull Date emDate;

}