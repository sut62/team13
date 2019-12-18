package com.sut.se.g13.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="POSITIONDRIVER")
public class PositionDriver {
	@Id
	@SequenceGenerator(name="positiondriver_seq",sequenceName="positiondriver_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="positiondriver_seq")
	@Column(name="POSITIONDRIVER_ID",unique = true, nullable = true)
    
    private @NonNull Long positionDriverid;
	private @NonNull String positionlevel;

}