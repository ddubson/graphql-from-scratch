package com.graphql;

import com.google.common.collect.ImmutableMap;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
import static java.util.Arrays.asList;
import static org.springframework.http.ResponseEntity.ok;

@SpringBootApplication
@RestController
public class App {

	private Actor tomHanks;
	private Actor mattDamon;
	private Movie savingPrivateRyan;
	private Movie forrestGump;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@GetMapping("/")
	public ResponseEntity<List<Movie>> helloWorld() {
		List<Movie> movies = generateMovies();
		return ok(movies);
	}

	@PostMapping("/graphql")
	public ResponseEntity helloWorldWithGraphQL(@RequestBody String query) {
		String schema = "type Query { movies: [Movie] } type Movie { name: String, year: String, actors: [Actor] } type Actor { name: String }";

		SchemaParser schemaParser = new SchemaParser();
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

		RuntimeWiring runtimeWiring = newRuntimeWiring()
				.type("Query", builder -> builder.dataFetcher("movies", new MovieFetcher()))
				.type("Movie", builder -> builder.)
				.build();

		SchemaGenerator schemaGenerator = new SchemaGenerator();
		GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

		GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
		ExecutionResult executionResult = build.execute(query);

		return ok(ImmutableMap.of("Request", query, "Response", executionResult.getData().toString()));
	}

	class MovieFetcher implements DataFetcher<List<Movie>> {
		@Override
		public List<Movie> get(DataFetchingEnvironment environment) {
			return generateMovies();
		}
	}

	private List<Movie> generateMovies() {
		tomHanks = new Actor("Tom Hanks");
		mattDamon = new Actor("Matt Damon");
		savingPrivateRyan = new Movie("Saving Private Ryan", "1998", asList(tomHanks, mattDamon));
		forrestGump = new Movie("Forrest Gump", "1994", Collections.singletonList(tomHanks));
		return asList(savingPrivateRyan, forrestGump);
	}
}
