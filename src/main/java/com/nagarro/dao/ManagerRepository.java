package com.nagarro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entities.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
