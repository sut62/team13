package com.sut.se.g13.Repository;

import com.sut.se.g13.Entity.Mile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MileRepository extends JpaRepository<Mile, Long> {
    Mile findByMileid(long mileid);
}