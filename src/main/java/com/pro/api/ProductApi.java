package com.pro.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.model.Product;
import com.pro.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductApi {

	@Autowired
	private ProductService productServ;

	@GetMapping
	@RequestMapping("")
	public ResponseEntity<List<Product>> findAll() {
		return ResponseEntity.ok().body(productServ.findAll());
	}
}