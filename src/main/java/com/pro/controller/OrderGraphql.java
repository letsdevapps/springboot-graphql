package com.pro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.pro.model.Order;
import com.pro.repository.OrderRepository;
import com.pro.repository.ProductRepository;

@Controller
public class OrderGraphql {

	private final OrderRepository repository;
	private final ProductRepository productRepository; // para calcular o total

	public OrderGraphql(OrderRepository repository, ProductRepository productRepository) {
		this.repository = repository;
		this.productRepository = productRepository;
	}

	@QueryMapping
	public List<Order> orders() {
		return repository.findAll();
	}

	@QueryMapping
	public Order order(@Argument Long id) {
		return repository.findById(id).orElse(null);
	}

	@MutationMapping
	public Order createOrder(@Argument Long clientId, @Argument List<Long> productIds) {
		// Calcula o total
		double total = productIds.stream().map(productRepository::findById).filter(Optional::isPresent)
				.mapToDouble(p -> p.get().getPrice()).sum();

		Order order = new Order(clientId, productIds, total);
		return repository.save(order);
	}

	@MutationMapping
	public Boolean deleteOrder(@Argument Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	@MutationMapping
	public Order updateOrder(@Argument Long id, @Argument List<Long> productIds) {
		return repository.findById(id).map(order -> {
			double total = productIds.stream().map(productRepository::findById).filter(Optional::isPresent)
					.mapToDouble(p -> p.get().getPrice()).sum();
			order.setProductIds(productIds);
			order.setTotal(total);
			return repository.save(order);
		}).orElse(null);
	}
}