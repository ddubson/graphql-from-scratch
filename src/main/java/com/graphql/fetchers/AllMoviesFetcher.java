package com.graphql.fetchers;

import com.graphql.MovieFactory;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class AllMoviesFetcher implements DataFetcher<Object> {
	private final MovieFactory movieFactory;

	public AllMoviesFetcher(MovieFactory movieFactory) {
		this.movieFactory = movieFactory;
	}

	@Override
	public Object get(DataFetchingEnvironment environment) {
		if(environment.getArgument("id") != null) {
			return this.movieFactory.all().stream()
					.filter(i -> environment.getArgument("id").equals(i)).findFirst().orElseThrow();
		}

		return movieFactory.all();
	}
}
