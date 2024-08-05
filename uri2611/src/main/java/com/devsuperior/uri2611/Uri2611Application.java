package com.devsuperior.uri2611;

import com.devsuperior.uri2611.dto.MovieProjectionDTO;
import com.devsuperior.uri2611.repositores.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {

	@Autowired
	private MovieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<MovieProjection> list = repository.search("action");
		List<MovieProjectionDTO> result1 = list.stream().map(MovieProjectionDTO::new).collect(Collectors.toList());

		System.out.println("\n*** RESULTADO SQL RAIZ: ");
		result1.forEach(System.out::println);

		System.out.println("\n*** RESULTADO JPQL: ");
		repository.searchJpql("ACTIon").forEach(System.out::println);
	}
}
