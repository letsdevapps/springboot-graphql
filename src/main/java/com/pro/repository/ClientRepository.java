package com.pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}