package com.graphql.fetchers;

import com.graphql.Actor;
import com.graphql.MovieFactory;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllActorsFetcher implements DataFetcher<List<Actor>> {
	private final MovieFactory movieFactory;

	public AllActorsFetcher(MovieFactory movieFactory) {
		this.movieFactory = movieFactory;
	}

	@Override
	public List<Actor> get(DataFetchingEnvironment environment) {
		return movieFactory.allActors();
	}
}
