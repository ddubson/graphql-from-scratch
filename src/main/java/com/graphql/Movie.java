package com.graphql;

import java.util.List;

public class Movie {
	private String name;
	private String year;
	private List<Actor> actors;

	public Movie(String name, String year, List<Actor> actors) {
		this.name = name;
		this.year = year;
		this.actors = actors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
}
