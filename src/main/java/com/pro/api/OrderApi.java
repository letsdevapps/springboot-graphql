package com.pro.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.model.Order;
import com.pro.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
public class OrderApi {

	@Autowired
	private OrderRepository orderRepo;

	@GetMapping
	@RequestMapping("")
	public ResponseEntity<List<Order>> findAll() {
		return ResponseEntity.ok().body(orderRepo.findAll());
	}
}