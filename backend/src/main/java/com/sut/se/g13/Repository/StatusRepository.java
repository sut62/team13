package com.sut.se.g13.Repository;

import com.sut.se.g13.Entity.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByStatusid(long statusid);
}