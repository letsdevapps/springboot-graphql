package com.pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}