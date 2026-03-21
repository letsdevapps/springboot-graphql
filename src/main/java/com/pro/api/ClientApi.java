package com.pro.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.model.Client;
import com.pro.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientApi {

	@Autowired
	private ClientService clientServ;

	@GetMapping
	@RequestMapping("")
	public ResponseEntity<List<Client>> findAll() {
		return ResponseEntity.ok().body(clientServ.findAll());
	}
}