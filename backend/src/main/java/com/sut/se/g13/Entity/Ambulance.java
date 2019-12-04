package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "AMBULANCE")
public class Ambulance {

    @Id
    @SequenceGenerator(name = "Ambulance_seq", sequenceName = "Ambulance_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Ambulance_seq")
    @Column(name = "AMBULANCE_ID", unique = true, nullable = true)
    private @NonNull Long ambulanceid;
    private @NonNull String ambulancemodel;
    private @NonNull String ambulancenum;
    private @NonNull String licenseplate;
    private @NonNull String enginenum;
}