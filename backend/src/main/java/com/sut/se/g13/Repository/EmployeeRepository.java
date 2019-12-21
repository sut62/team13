package com.sut.se.g13.Repository;

import com.sut.se.g13.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Collection;

@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeid(Long employeeid);

    @Query(value = "SELECT * FROM Employee z Where z.username = :username and z.password = :password", nativeQuery = true)
    Collection<Employee> findCheck(@Param("username") String username, @Param("password") String password);
}
