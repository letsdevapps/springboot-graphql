package com.pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.pro.model.Client;
import com.pro.repository.ClientRepository;

@Controller
public class ClientGraphql {

	@Autowired
	private ClientRepository repository;

	@QueryMapping
	public List<Client> clients() {
		return repository.findAll();
	}

	@QueryMapping
	public Client client(@Argument Long id) {
		return repository.findById(id).orElse(null);
	}

	@MutationMapping
	public Client createClient(@Argument String name, @Argument String email) {
		return repository.save(new Client(name, email));
	}
}