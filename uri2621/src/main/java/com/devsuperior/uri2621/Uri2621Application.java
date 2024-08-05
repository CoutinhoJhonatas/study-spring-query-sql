package com.devsuperior.uri2621;

import com.devsuperior.uri2621.dtos.ProductDTO;
import com.devsuperior.uri2621.projections.ProductProjection;
import com.devsuperior.uri2621.repositores.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n*** RESULTADO SQL RAIZ: ");
		List<ProductDTO> result1 = repository.search("P", 10, 20).stream().map(ProductDTO::new).collect(Collectors.toList());
		result1.forEach(System.out::println);

		System.out.println("\n*** RESULTADO JPQL: ");
		List<ProductDTO> result2 = repository.searchJpql("P", 10, 20);
		result2.forEach(System.out::println);

	}
}
