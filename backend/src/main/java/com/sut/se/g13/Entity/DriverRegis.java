package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@Table(name="DRIVERREGIS")
public class DriverRegis {
    @Id
    @SequenceGenerator(name="DriverRegis_seq",sequenceName="DriverRegis_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="DriverRegis_seq")
    @Column(name = "DRIVERREGIS_ID", unique = true, nullable = true)
    private Long driverregisid;
    
    @Temporal(TemporalType.DATE)
    private Date birthday;     //วันเดือนปีเกิด
    
    @NotNull
    @Size (min=1, max=20)
    private String drivername; //ชื่อ-สุกล คนขับ
    
    @NotNull
    @Pattern(regexp = "\\d{13}")
    private  String idcard;     //เลขที่บัตรประชาชน
   
    @NotNull
    @Size (min=1, max=50)
    private String address;    //ที่อยู่
    
    @NotNull
    @Pattern(regexp="\\d{10}")
    private String telephone;  //หมายเลขโทรศัพท์



    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER")
    private Gender genderid; //เพศพนักงานคนขับ

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PositionDriver.class)
    @JoinColumn(name = "POSITIONDRIVER")
    private PositionDriver positionid; //ตำแหน่งของพนักงานคนขับ
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
    private Province provinceid; //รายชื่อจังหวัด

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Educational.class)
    @JoinColumn(name = "EDUCATIONAL_ID", insertable = true)
    private Educational educationalid; //ระดับการศึกษา

}

