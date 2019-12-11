package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name = "AMBULANCETYPE")
public class AmbulanceType {
    @Id
    @SequenceGenerator(name = "AmbulanceType_seq", sequenceName = "AmbulanceType_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AmbulanceType_seq")
    @Column(name = "AMBULANCETYPE_ID", unique = true, nullable = true)
    private @NonNull Long typeid;
    private @NonNull String amtype;
}