package com.graphql.fetchers;

import com.graphql.Movie;
import com.graphql.MovieFactory;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class FindMovieByIdFetcher implements DataFetcher<Movie> {
	private final MovieFactory movieFactory;

	public FindMovieByIdFetcher(MovieFactory movieFactory) {
		this.movieFactory = movieFactory;
	}

	@Override
	public Movie get(DataFetchingEnvironment environment) {
		return this.movieFactory.all().stream()
				.filter(i -> environment.getArgument("id").equals(i)).findFirst().orElseThrow();
	}
}
