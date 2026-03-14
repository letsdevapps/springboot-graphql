package com.pro.consume;

import org.springframework.http.HttpMethod;
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
	public ResponseEntity<String> index() {
		String apiUrl = "http://localhost:8080/api";
		ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, String.class);
		return response;
	}
}