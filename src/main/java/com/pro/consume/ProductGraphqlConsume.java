package com.pro.consume;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/consume/graphql/products")
public class ProductGraphqlConsume {

	private final WebClient webClient;

	public ProductGraphqlConsume() {
		this.webClient = WebClient.builder().baseUrl("http://localhost:8080/graphql") // endpoint do seu GraphQL
				.defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).build();
	}

	// Consulta todos os produtos
	@GetMapping("")
	public Mono<String> getProducts() {
		String query = "{ \"query\": \"{ products { id name price quantity } }\" }";

		return webClient.post().bodyValue(query).retrieve().bodyToMono(String.class);
	}

	// Consulta produto por ID
	@GetMapping("/{id}")
	public Mono<String> getProductById(@PathVariable Long id) {
		String query = "{ \"query\": \"query($id: ID!){ product(id: $id) { id name price quantity } }\", "
				+ "\"variables\": {\"id\": " + id + "} }";

		return webClient.post().bodyValue(query).retrieve().bodyToMono(String.class);
	}

	// Cria produto
	@GetMapping("/{name}/{email}/{quantity}")
	public Mono<String> createProduct(@PathVariable String name, @PathVariable Double price,
			@PathVariable Integer quantity) {
		String mutation = "{ \"query\": \"mutation($name: String!, $price: Double!, $quantity: Integer){ createProduct(name: $name, price: $price, quantity: $quantity) { id name price quantity } }\", "
				+ "\"variables\": {\"name\": \"" + name + "\", \"price\": \"" + price + "\", \"quantity\": \""
				+ quantity + "\"} }";

		return webClient.post().bodyValue(mutation).retrieve().bodyToMono(String.class);
	}
}