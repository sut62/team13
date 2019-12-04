package com.sut.sa.g13.Entity;

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
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "NURSE")
public class Nurse {
    @Id
    @SequenceGenerator(name =  "Nurse_seq", sequenceName="Nurse_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Nurse_seq")
    @Column(name = "NURSE_ID", unique = true, nullable = true)
    private @NonNull long id;
    private @NonNull String name;
    private @NonNull String address;
    private @NonNull String telephone;
    private @NonNull String email;
    private @NonNull Date birthday;
}