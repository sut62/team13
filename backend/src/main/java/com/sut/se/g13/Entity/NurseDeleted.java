package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;



@Data
@Entity
@NoArgsConstructor
@Table(name = "NURSEDELETED")
public class NurseDeleted {

    @Id
    @SequenceGenerator(name = "nursedeleted_seq", sequenceName = "nursedeleted_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nursedeleted_seq")
    @Column(name = "NURSEDELETED_ID", unique = true, nullable = true)
    private @NonNull Long nursedeletedid;
    
    @Temporal(TemporalType.DATE)
    @NotNull
    private  Date nursedeletedtime;

    @Size(min = 2, max = 20)
    private @NotNull String employee;

    @Column(name = "NURSE_ID", unique = true, nullable = true)
    private @NotNull Long nurse;
}
