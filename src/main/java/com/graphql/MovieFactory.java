package com.graphql;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

@Component
public class MovieFactory {
	private List<Movie> movies;

	public MovieFactory() {
		Actor tomHanks = new Actor(1L, "Tom Hanks");
		Actor mattDamon = new Actor(2L, "Matt Damon");
		Movie savingPrivateRyan = new Movie(1L, "Saving Private Ryan", "1998", asList(tomHanks, mattDamon));
		Movie forrestGump = new Movie(2L, "Forrest Gump", "1994", Collections.singletonList(tomHanks));
		movies = asList(savingPrivateRyan, forrestGump);
	}

	public List<Movie> all() {
		return this.movies;
	}
}
