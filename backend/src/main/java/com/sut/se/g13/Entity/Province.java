package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PROVINCE")
public class Province {
	@Id
	@SequenceGenerator(name = "province_seq", sequenceName = "province_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "province_seq")
	@Column(name = "PROVINCE_ID", unique = true, nullable = true)
	private @NonNull Long provinceid;
	private @NonNull String provincename;

}
