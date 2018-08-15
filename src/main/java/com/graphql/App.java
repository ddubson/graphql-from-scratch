package com.graphql;

import graphql.GraphQL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@SpringBootApplication
@RestController
public class App {
	private GraphQL graphQLAPI;

	public App(GraphQL graphQLAPI) {
		this.graphQLAPI = graphQLAPI;
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@GetMapping(value = "/rest/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Movie>> moviesRest() {
		return ok(MovieFactory.create());
	}

	@PostMapping(value = "/graphql/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity helloWorldWithGraphQL(@RequestBody String query) {
		return ok(graphQLAPI.execute(query).getData());
	}
}
