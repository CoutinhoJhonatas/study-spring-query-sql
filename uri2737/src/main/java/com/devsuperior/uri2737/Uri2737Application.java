package com.devsuperior.uri2737;

import com.devsuperior.uri2737.dto.LawyerMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2737.repositories.LawyerRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2737Application implements CommandLineRunner {

	@Autowired
	private LawyerRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2737Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<LawyerMinDTO> result1 = repository.search1().stream().map(LawyerMinDTO::new).collect(Collectors.toList());
		System.out.println("\n*** RESULTADO SQL RAIZ 1: ");
		result1.forEach(System.out::println);

		List<LawyerMinDTO> result2 = repository.search2().stream().map(LawyerMinDTO::new).collect(Collectors.toList());;
		System.out.println("\n*** RESULTADO SQL RAIZ 2: ");
		result2.forEach(System.out::println);

	}
}
