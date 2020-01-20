package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;



@Data
@Entity
@NoArgsConstructor
@Table(name = "AMBULANCEDELETED")
public class AmbulanceDeleted {

    @Id
    @SequenceGenerator(name = "deleted_seq", sequenceName = "deleted_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deleted_seq")
    @Column(name = "DELETED_ID", unique = true, nullable = true)
    private @NotNull Long deletedid;
    private @NotNull Date deletedtime;
    private @NotNull String employee;
    private @NotNull String ambulance;
}
