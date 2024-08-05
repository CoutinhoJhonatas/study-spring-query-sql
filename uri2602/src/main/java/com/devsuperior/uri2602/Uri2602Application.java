package com.devsuperior.uri2602;

import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;
import com.devsuperior.uri2602.dto.CustomerMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CustomerMinProjection> list = customerRepository.search("RS");
		List<CustomerMinDTO> result1 = list.stream().map(c -> new CustomerMinDTO(c)).collect(Collectors.toList());

		System.out.println("\n*** RESULTADO SQL RAIZ: ");
		result1.forEach(System.out::println);

		List<CustomerMinDTO> result2 = customerRepository.searchJpql("RS");

		System.out.println("\n*** RESULTADO JPQL: ");
		result2.forEach(System.out::println);
	}
}
