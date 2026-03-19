package com.pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.model.Product;
import com.pro.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	public List<Product> findAll() {
		return productRepo.findAll();
	}
}