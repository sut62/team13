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
@Table(name = "LOGIN")
public class Login {

    @Id
    @SequenceGenerator(name = "login_seq", sequenceName = "login_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_seq")
    @Column(name = "LOGIN_ID", unique = true, nullable = true)
    private @NonNull Long loginid;
    private @NonNull Date logintime;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employeeid; 
}
