package com.pro.consume;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/consume/graphql/clients")
public class ClientGraphqlConsume {

	private final WebClient webClient;

	public ClientGraphqlConsume() {
		this.webClient = WebClient.builder().baseUrl("http://localhost:8080/graphql") // endpoint do seu GraphQL
				.defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).build();
	}

	// Consulta todos os clientes
	@GetMapping("")
	public Mono<String> getClients() {
		String query = "{ \"query\": \"{ clients { id name email } }\" }";

		return webClient.post().bodyValue(query).retrieve().bodyToMono(String.class);
	}

	// Consulta cliente por ID
	@GetMapping("/{id}")
	public Mono<String> getClientById(@PathVariable Long id) {
		String query = "{ \"query\": \"query($id: ID!){ client(id: $id) { id name email } }\", "
				+ "\"variables\": {\"id\": " + id + "} }";

		return webClient.post().bodyValue(query).retrieve().bodyToMono(String.class);
	}

	// Cria cliente
	@GetMapping("/{name}/{email}")
	public Mono<String> createClient(@PathVariable String name, @PathVariable String email) {
		String mutation = "{ \"query\": \"mutation($name: String!, $email: String!){ createClient(name: $name, email: $email) { id name email } }\", "
				+ "\"variables\": {\"name\": \"" + name + "\", \"email\": \"" + email + "\"} }";

		return webClient.post().bodyValue(mutation).retrieve().bodyToMono(String.class);
	}
}