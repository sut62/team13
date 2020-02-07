package com.sut.se.g13.Entity;
import lombok.*;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name="STATUS")
public class Status {
  @Id
  @SequenceGenerator(name="STATUS_SEQ",sequenceName="STATUS_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STATUS_SEQ")
  @Column(name = "ID_STATUS", unique = true, nullable = true)
  private @NonNull Long statusid;
  private @NonNull String name;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<AmbulanceCheck> AmbulanceCheck;
}