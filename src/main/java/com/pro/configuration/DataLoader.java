package com.pro.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pro.model.Client;
import com.pro.model.Product;
import com.pro.repository.ClientRepository;
import com.pro.repository.ProductRepository;

@Configuration
public class DataLoader {

	@Bean
	CommandLineRunner loadClient(ClientRepository repository) {
		return args -> {
			repository.save(new Client("Joao", "joao@email.com"));
			repository.save(new Client("Maria", "maria@email.com"));
			repository.save(new Client("Jose", "jose@email.com"));
		};
	}

	@Bean
	CommandLineRunner loadData(ProductRepository repository) {
		return args -> {
			repository.save(new Product("Notebook", 3500.0, 10));
			repository.save(new Product("Mouse", 80.0, 50));
			repository.save(new Product("Teclado", 150.0, 30));
		};
	}
}