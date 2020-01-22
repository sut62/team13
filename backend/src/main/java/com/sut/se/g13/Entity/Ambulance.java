package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "AMBULANCE")
public class Ambulance {
  @Id
  @SequenceGenerator(name = "AMBULANCE_SEQ", sequenceName = "AMBULANCE_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AMBULANCE_SEQ")
  @Column(name = "ID_AMBULANCE", unique = true, nullable = true)
  private @NotNull Long ambulanceid; // ไอดีรถ
  
  @Temporal(TemporalType.DATE)
  @NotNull
  private Date adddate;

  @NotNull
  private String ambulancemodel; // รุ่นรถ

  @Pattern(regexp = "\\w{17}")
  @NotNull
  @Column(name = "AMBULANCENUM", unique = true)
  private String ambulancenum; // เลขตัวรถ

  @NotNull
  @Size(min = 4, max = 8)
  private String licenseplate; // เลขทะเบียน (ไม่มีจังหวัด)

  @Pattern(regexp = "\\w{12}")
  @NotNull
  @Column(name = "ENGINENUM", unique = true)
  private String enginenum; // เลขเครื่องยนตร์

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = AmbulanceBrand.class)
  @JoinColumn(name = "AMBULANCEBRAND_ID", insertable = true)
  @NotNull
  private AmbulanceBrand brandid; // ยี่ห่อรถ (ambulancebrand)

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = AmbulanceType.class)
  @JoinColumn(name = "AMBULANCETYPE_ID", insertable = true)
  @NotNull
  private AmbulanceType typeid; // ประเภทรถการใช้งาน (ambulancetype)

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
  @JoinColumn(name = "PROVINCE_ID", insertable = true)
  @NotNull
  private Province provinceid; // จังหวัดในป้ายทะเบียน (province)

}