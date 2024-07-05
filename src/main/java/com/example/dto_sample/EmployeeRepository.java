package com.example.dto_sample;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	Optional<Employee> findByName(String name);

	

}
