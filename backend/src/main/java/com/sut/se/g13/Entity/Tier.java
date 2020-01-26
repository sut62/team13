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
@Table(name = "TIER")
public class Tier {
    @Id
    @SequenceGenerator(name = "TIER_seq", sequenceName = "TIER_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIER_seq")
    @Column(name = "TIER_ID", unique = true, nullable = true)
    private @NonNull Long tierid;
    private @NonNull String tiername;
}