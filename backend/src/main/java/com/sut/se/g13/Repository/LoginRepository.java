package com.sut.se.g13.Repository;

import com.sut.se.g13.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByLoginid(long loginid);
}