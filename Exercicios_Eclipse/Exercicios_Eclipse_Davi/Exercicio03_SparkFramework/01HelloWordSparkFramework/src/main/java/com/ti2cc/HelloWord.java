package com.ti2cc;

import static spark.Spark.*;

public class HelloWord {

	public static void main(String[] args) {
		port(5678);
		get("/hello", (request, response) -> "BORAAAAA!");
	}

}
