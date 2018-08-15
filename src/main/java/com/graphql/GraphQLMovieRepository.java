package com.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLMovieRepository implements DataFetcher<List<Movie>> {
	@Override
	public List<Movie> get(DataFetchingEnvironment environment) {
		return MovieFactory.create();
	}
}
