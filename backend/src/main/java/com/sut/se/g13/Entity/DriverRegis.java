package com.sut.sa.g13.Entity;

import lombok.*;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="DRIVERREGIS")
public class DriverRegis {
    @Id
    @SequenceGenerator(name="DriverRegis_seq",sequenceName="DriverRegis_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="DriverRegisr_seq")
    @Column(name = "DRIVERREGIS_ID", unique = true, nullable = true)
    
    private @NonNull Long driverregisid;
    private @NonNull String idcard;
    private @NonNull String address;
    private @NonNull String phonenum;
    private @NonNull String username;
    private @NonNull String password;

}

