package com.pro.consume;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/consume")
public class HomeApiConsume {

	private RestTemplate restTemplate = new RestTemplate();

	@GetMapping
	public ResponseEntity<Map<String, String>> index() {
		Map<String, String> results = new HashMap<>();

		// Consumindo REST
		String restUrl = "http://localhost:8080/api";
		String restResponse = restTemplate.getForObject(restUrl, String.class);
		results.put("restMessage", restResponse);

		// Consumindo GraphQL
		String graphqlUrl = "http://localhost:8080/graphql";
		String graphqlQuery = "{ message }";
		Map<String, String> request = new HashMap<>();
		request.put("query", graphqlQuery);

		Map<?, ?> graphqlResponse = restTemplate.postForObject(graphqlUrl, request, Map.class);
		// O resultado do GraphQL vem dentro de "data"
		results.put("graphqlMessage", ((Map<?, ?>) graphqlResponse.get("data")).get("message").toString());

		return ResponseEntity.ok(results);
	}
}