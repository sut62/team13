package com.sut.se.g13.Repository;

import com.sut.se.g13.Entity.Emergency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
    Emergency findByEmergencyId(long emergencyid);
}