package com.framework.api.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class ApiBase {

	@BeforeClass
	public void apiSetup() {

	    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

	    System.out.println(">>> API Base URI set");
	}

}
