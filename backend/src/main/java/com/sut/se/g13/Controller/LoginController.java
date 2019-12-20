package com.sut.se.g13.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import com.sut.se.g13.Entity.*;
import com.sut.se.g13.Repository.*;
import java.text.ParseException;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class LoginController {

    @Autowired
    private final LoginRepository loginRepository;
    @Autowired
    private final EmployeeRepository employeeRepository;

    public LoginController(final LoginRepository loginRepository, EmployeeRepository employeeRepository) {
        this.loginRepository = loginRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employee")
    public Collection<Employee> employee() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/check/{employeeid}/{passoword}")
    public Collection<Employee> getCheck(@PathVariable("employeeid") Long employeeid,
            @PathVariable("passoword") String password) {
        return employeeRepository.findCheck(employeeid, password);
    }

    @PostMapping("/login/{emid}")
    public Login newLogin(Login newLogin, @PathVariable final Long emid) throws ParseException {

        final Employee employee = employeeRepository.findByEmployeeid(emid);

        newLogin.setEmployeeid(employee);
        newLogin.setLogintime(new Date());

        return loginRepository.save(newLogin);
    }
}