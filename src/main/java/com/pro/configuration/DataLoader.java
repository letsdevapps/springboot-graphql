package com.pro.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pro.model.Product;
import com.pro.repository.ProductRepository;

@Configuration
public class DataLoader {

	@Bean
	CommandLineRunner loadData(ProductRepository repository) {
		return args -> {
			repository.save(new Product("Notebook", 3500.0, 10));
			repository.save(new Product("Mouse", 80.0, 50));
			repository.save(new Product("Teclado", 150.0, 30));
		};
	}
}