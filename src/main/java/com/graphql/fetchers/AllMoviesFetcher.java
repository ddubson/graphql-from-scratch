package com.graphql.fetchers;

import com.graphql.Movie;
import com.graphql.MovieFactory;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class AllMoviesFetcher implements DataFetcher<Object> {
	private final MovieFactory movieFactory;

	public AllMoviesFetcher(MovieFactory movieFactory) {
		this.movieFactory = movieFactory;
	}

	@Override
	public List<Movie> get(DataFetchingEnvironment environment) {
		if (environment.getArgument("id") != null) {
			return this.movieFactory.all().stream()
					.filter(i -> environment.getArgument("id").equals(i.getId()))
					.collect(toList());
		}

		return movieFactory.all();
	}
}
