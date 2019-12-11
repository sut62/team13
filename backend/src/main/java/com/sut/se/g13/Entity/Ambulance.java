package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
  private @NonNull Long ambulanceid; // ไอดีรถ  
  private @NonNull String ambulancemodel; // รุ่นรถ
  private @NonNull String ambulancenum; // เลขตัวรถ
  private @NonNull String licenseplate; // เลขทะเบียน (ไม่มีจังหวัด)
  private @NonNull String enginenum; // เลขเครื่องยนตร์

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = AmbulanceBrand.class)
  @JoinColumn(name = "AMBULANCEBRAND_ID", insertable = true)
  private AmbulanceBrand brandid; // ยี่ห่อรถ (ambulancebrand)

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = AmbulanceType.class)
  @JoinColumn(name = "AMBULANCETYPE_ID", insertable = true)
  private AmbulanceType typeid; // ประเภทรถการใช้งาน (ambulancetype)

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
  @JoinColumn(name = "PROVINCE_ID", insertable = true)
  private Province provinceid; // จังหวัดในป้ายทะเบียน (province)

}