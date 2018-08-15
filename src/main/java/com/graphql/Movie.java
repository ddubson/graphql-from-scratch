package com.graphql;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Movie {
	private final Long id;
	private final String name;
	private final String year;
	private final List<Actor> actors;
}
