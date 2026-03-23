package com.pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.pro.model.Product;
import com.pro.repository.ProductRepository;

@Controller
public class ProductGraphql {

	@Autowired
	private ProductRepository repository;

	@QueryMapping
	public List<Product> products() {
		return repository.findAll();
	}

	@QueryMapping
	public Product product(@Argument Long id) {
		return repository.findById(id).orElse(null);
	}

	@MutationMapping
	public Product createProduct(@Argument String name, @Argument Double price, @Argument Integer quantity) {
		return repository.save(new Product(name, price, quantity));
	}
}