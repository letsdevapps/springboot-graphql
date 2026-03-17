package com.pro.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HomeGraphQLController {

	@QueryMapping
	public String message() {
		return "----- Springboot GraphQL | GraphQL Index -----";
	}
}