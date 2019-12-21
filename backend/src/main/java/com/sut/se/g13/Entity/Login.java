package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@Entity
@NoArgsConstructor
@Table(name = "LOGIN")
public class Login {

    @Id
    @SequenceGenerator(name = "login_seq", sequenceName = "login_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_seq")
    @Column(name = "LOGIN_ID", unique = true, nullable = true)
    private @NonNull Long loginid;
    private @NonNull String employee;
    private @NonNull Date logintime;
}
