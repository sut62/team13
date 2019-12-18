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
@Table(name="EDUCATIONAL")
public class Educational {
	@Id
	@SequenceGenerator(name="educational_seq",sequenceName="educational_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="educational_seq")
	@Column(name="EDUCATIONAL_ID",unique = true, nullable = true)
	private @NonNull Long educationalid;
	private @NonNull String educationallevel;

}
