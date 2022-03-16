package RestTesting;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Trello {

	//Base URL is common for every action
	public static String baseurl = "https://api.trello.com";
	public static String id;
	
	@Test (priority = 0)
	public void createBoard()
	{
		//I want to fetch the base url so that I can add other things to that base url
		//This is the first step to provide base url to the request
		RestAssured.baseURI = baseurl;
		
		//In rest assured we have three keywords and they are;
		//given : pre-condition   It includes parameter, header, authorization
		//when : condition and testing   It includes resource; like name and so on...
		//then : post-condition   It is something like response, chech the response
		Response response = given().queryParam("name", "Lakshay's Board")
		.queryParam("key", "8e3e37f415953197537b94d4f97feed1")
		.queryParam("token", "6e9807d27aa5c2530b7394721320fac821874685590668be98d4ce32de974720")
		.header("Content-Type", "application/json")
		
		.when()
		.post("/1/boards/");
		
		String jsonresponse = response.asString();           
		//Converting the response from string to Json format
		JsonPath js = new JsonPath(jsonresponse);   
		id = js.get("id");
		//ID = js.get("id");
		System.out.println("ID is "+id);
	}
	
	@Test (priority = 1)
	public void getBoard()
	{
		RestAssured.baseURI = baseurl;
		Response response = given().queryParam("key", "8e3e37f415953197537b94d4f97feed1")
		.queryParam("token", "6e9807d27aa5c2530b7394721320fac821874685590668be98d4ce32de974720")
		.header("Content-Type", "application/json")
		
		.when()
		.get("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		System.out.println(jsonresponse);
		
	}
	
	@Test (priority = 2)
	public void deleteBoard()
	{
		RestAssured.baseURI = baseurl;
		Response response = given()
		.queryParam("key", "8e3e37f415953197537b94d4f97feed1")
		.queryParam("token", "6e9807d27aa5c2530b7394721320fac821874685590668be98d4ce32de974720")
		.header("Content-Type", "application/json")
		
		.when()
		.delete("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		System.out.println(jsonresponse);
	}
	
}
