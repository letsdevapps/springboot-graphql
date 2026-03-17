package com.pro.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeApi {

	@GetMapping
	public ResponseEntity<String> index() {
		String message = "----- Springboot GraphQL | API Index -----";
		return ResponseEntity.ok().body(message);
	}
}