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
@Table(name = "AMBULANCEDELETED")
public class AmbulanceDeleted {

    @Id
    @SequenceGenerator(name = "deleted_seq", sequenceName = "deleted_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deleted_seq")
    @Column(name = "DELETED_ID", unique = true, nullable = true)
    private @NonNull Long deletedid;
    
    @Temporal(TemporalType.DATE)
    @NotNull
    private  Date deletedtime;

    @Size(min = 2, max = 20)
    private @NotNull String employee;

    @Column(name = "AMBULANCE_ID", unique = true, nullable = true)
    private @NotNull Long ambulance;
}
