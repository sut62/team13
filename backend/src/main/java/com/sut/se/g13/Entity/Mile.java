package com.sut.se.g13.Entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name="MILE")
public class Mile {
  @Id
  @SequenceGenerator(name="MILE_SEQ",sequenceName="MILE_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MILE_SEQ")
  @Column(name = "ID_MILE", unique = true, nullable = true)
  private @NonNull Long id;
  private @NonNull String name;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<AmbulanceCheck> AmbulanceCheck;
}