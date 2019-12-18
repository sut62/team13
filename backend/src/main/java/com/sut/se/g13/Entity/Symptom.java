package com.sut.se.g13.Entity;

import lombok.*;
import javax.persistence.*;
import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "SYMPTOM")
public class Symptom {
    @Id
    @SequenceGenerator(name = "SYMPTOM_SEQ", sequenceName = "SYMPTOM_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYMPTOM_SEQ")
    @Column(name = "SYMPTOME_ID", unique = true, nullable = true)
    private @NonNull Long symptomid;
    private @NonNull String type;
    
   /* @OneToMany(fetch = FetchType.EAGER)
    private Collection<Emergency> Emergency;*/
    

}