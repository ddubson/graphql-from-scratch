package com.graphql;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Actor {
	private final Long id;
	private final String name;
}
