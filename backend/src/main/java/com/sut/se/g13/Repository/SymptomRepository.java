package com.sut.se.g13.Repository;

import com.sut.se.g13.Entity.Symptom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SymptomRepository extends JpaRepository<Symptom, Long> {
    Symptom findBySymptomid(long symptomid);
}