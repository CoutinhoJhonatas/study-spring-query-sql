package com.devsuperior.uri2990;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.repositories.EmpregadoRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<EmpregadoDeptDTO> result1 = repository.search1().stream().map(EmpregadoDeptDTO::new).collect(Collectors.toList());
		System.out.println("\n*** RESULTADO SQL RAIZ 1: ");
		result1.forEach(System.out::println);

		List<EmpregadoDeptDTO> result2 = repository.search2().stream().map(EmpregadoDeptDTO::new).collect(Collectors.toList());
		System.out.println("\n*** RESULTADO SQL RAIZ 2: ");
		result2.forEach(System.out::println);

		List<EmpregadoDeptDTO> result3 = repository.searchJpql();
		System.out.println("\n*** RESULTADO JPQL: ");
		result3.forEach(System.out::println);
	}
}
