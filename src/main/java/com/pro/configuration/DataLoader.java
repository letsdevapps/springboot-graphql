package com.pro.configuration;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pro.model.Client;
import com.pro.model.Order;
import com.pro.model.Product;
import com.pro.repository.ClientRepository;
import com.pro.repository.OrderRepository;
import com.pro.repository.ProductRepository;

@Configuration
public class DataLoader {

	@Bean
	CommandLineRunner loadClients(ClientRepository repository) {
		return args -> {
			repository.save(new Client("Joao", "joao@email.com"));
			repository.save(new Client("Maria", "maria@email.com"));
			repository.save(new Client("Jose", "jose@email.com"));
		};
	}

	@Bean
	CommandLineRunner loadProducts(ProductRepository repository) {
		return args -> {
			repository.save(new Product("Notebook", 3500.0, 10));
			repository.save(new Product("Mouse", 80.0, 50));
			repository.save(new Product("Teclado", 150.0, 30));
		};
	}

	@Bean
	CommandLineRunner loadOrders(OrderRepository orderRepository, ClientRepository clientRepository,
			ProductRepository productRepository) {
		return args -> {
			if (orderRepository.count() == 0) {

				// --- Pegar clientes de forma segura ---
				List<Client> clients = clientRepository.findAll();
				if (clients.size() < 2) {
					System.out.println("Não há clientes suficientes para criar pedidos.");
					return;
				}
				Client joao = clients.get(0);
				Client maria = clients.get(1);

				// --- Pegar produtos de forma segura ---
				List<Product> products = productRepository.findAll();
				if (products.size() < 3) {
					System.out.println("Não há produtos suficientes para criar pedidos.");
					return;
				}
				Product notebook = products.get(0);
				Product mouse = products.get(1);
				Product teclado = products.get(2);

				// --- Criar pedidos ---
				Order order1 = new Order(joao.getId(), List.of(notebook.getId(), mouse.getId()),
						notebook.getPrice() + mouse.getPrice());

				Order order2 = new Order(maria.getId(), List.of(teclado.getId(), mouse.getId()),
						teclado.getPrice() + mouse.getPrice());

				orderRepository.saveAll(List.of(order1, order2));

				System.out.println("Pedidos carregados com sucesso!");
			}
		};
	}
}