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
@Table(name = "AMBULANCEBRAND")
public class AmbulanceBrand {
    @Id
    @SequenceGenerator(name = "AmbulanceBrand_seq", sequenceName = "AmbulanceBrand_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AmbulanceBrand_seq")
    @Column(name = "AMBULANCEBRAND_ID", unique = true, nullable = true)
    private @NonNull Long brandid;
    private @NonNull String ambrand;
}