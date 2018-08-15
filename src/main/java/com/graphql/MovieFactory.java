package com.graphql;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class MovieFactory {
	private List<Movie> movies;
	private List<Actor> actors;

	public MovieFactory() {
		Actor tomHanks = new Actor(1L, "Tom Hanks");
		Actor mattDamon = new Actor(2L, "Matt Damon");
		Actor garySinise = new Actor(3L, "Gary Sinise");
		Actor sallyField = new Actor(4L, "Sally Field");
		Movie savingPrivateRyan = new Movie(1L, "Saving Private Ryan", "1998", asList(tomHanks, mattDamon));
		Movie forrestGump = new Movie(2L, "Forrest Gump", "1994", asList(tomHanks, sallyField, garySinise));
		movies = asList(savingPrivateRyan, forrestGump);
		actors = asList(tomHanks, sallyField, garySinise, sallyField);
	}

	public List<Movie> all() {
		return this.movies;
	}

	public List<Actor> allActors() { return this.actors; }
}
