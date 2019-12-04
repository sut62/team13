package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="CHECKEQUIPMENT")
public class CheckEquipment {

    @Id
    @SequenceGenerator(name = "checkEquipment_seq", sequenceName = "checkEquipment_seq")
    @GeneratedValue(generator = "checkEquipment_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="CHECKEQUIPMENT_ID",unique = true, nullable = true)
 
    private @NonNull Long id;
    private @NonNull Date date;
    private @NonNull String checklish;
   