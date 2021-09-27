package com.nagarro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.EmployeeDetail;

public interface EmployeeRepository extends JpaRepository<EmployeeDetail, Integer> {

}
