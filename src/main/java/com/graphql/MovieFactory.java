package com.graphql;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class MovieFactory {
	private static List<Movie> movies;

	static {
		Actor tomHanks = new Actor(1L, "Tom Hanks");
		Actor mattDamon = new Actor(2L, "Matt Damon");
		Movie savingPrivateRyan = new Movie(1L, "Saving Private Ryan", "1998", asList(tomHanks, mattDamon));
		Movie forrestGump = new Movie(2L, "Forrest Gump", "1994", Collections.singletonList(tomHanks));
		movies = asList(savingPrivateRyan, forrestGump);
	}
	static List<Movie> create() {
		return movies;
	}
}
