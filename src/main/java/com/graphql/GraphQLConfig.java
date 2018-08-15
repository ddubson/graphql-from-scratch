package com.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Configuration
public class GraphQLConfig {
	@Bean
	public GraphQL graphQL(DataFetcher allMoviesFetcher,
						   DataFetcher allActorsFetcher) throws IOException {
		File file = ResourceUtils.getFile("classpath:schema.graphqls");
		String result = String.join(" ", Files.readLines(file, Charsets.UTF_8));

		SchemaParser schemaParser = new SchemaParser();
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(result);

		RuntimeWiring runtimeWiring = newRuntimeWiring()
				.type("QueryType", builder ->
						builder.dataFetcher("movies", allMoviesFetcher)
								.dataFetcher("actors", allActorsFetcher))
				.build();

		SchemaGenerator schemaGenerator = new SchemaGenerator();
		GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		return GraphQL.newGraphQL(graphQLSchema).build();
	}
}
