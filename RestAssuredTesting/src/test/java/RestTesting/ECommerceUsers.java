package RestTesting;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ECommerceUsers {
	
	public static String baseurl = "https://ecommerceservice.herokuapp.com";
	public static String message;
	public static String Token;
	public static String id;
	public static String emailid;
	
	@Test (priority = 0, enabled = false)
	public void signup()
	{
		RestAssured.baseURI = baseurl;
		
		String requestbody = "{\r\n"
				+ "	\"email\": \"Krypto1.Knight@gmail.com\",\r\n"
				+ "	\"password\": \"krishna@123\"\r\n"
				+ "}\r\n";                                                                                   
	
	Response response = given()
			.header("Content-Type", "application/json")
			.body(requestbody)
			
			.when()
			.post("/user/signup")
			
			.then()
			.assertThat().statusCode(201).contentType(ContentType.JSON)
			.extract().response();
	//Calling response and saving it as a string
			String jsonresponse = response.asString();
	//Now converting the response into the JSON format
			JsonPath js = new JsonPath(jsonresponse);
	//Now using the response we will get the ID
			message = js.get("message");
			System.out.println(message);
			
	}
	
	@Test (priority = 1)
	public void login()
	{
		RestAssured.baseURI = baseurl;
		
		String requestbody = "{\r\n"
				+ "	\"email\": \"Krypto1.Knight@gmail.com\",\r\n"
				+ "	\"password\": \"krishna@123\"\r\n"
				+ "}\r\n";
		
		Response response = given()
				.header("Content-Type", "application/json")
				.body(requestbody)
				
				.when()
				.post("/user/login")
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();
		
		String jsonresponse = response.asString();
		JsonPath js = new JsonPath(jsonresponse);
		Token = js.get("accessToken");
		System.out.println("Token is "+Token);
		//System.out.println("Anything");
	}
	
	@Test (priority = 2)
	public void getallusers()
	{
		RestAssured.baseURI = baseurl;
		
		Response response = given()
				.header("Authorization", "Bearer "+Token)
				
				.when()
				.get("/user")
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();
		
		String jsonresponse = response.asString();
		JsonPath js = new JsonPath(jsonresponse);
		id = js.get("users[50]._id");
		System.out.println("The Users ID is "+id);
		emailid = js.get("users[50].email");
		System.out.println("The Users Email ID is "+emailid);
	}
	
	@Test (priority = 3)
	public void delete()
	{
		RestAssured.baseURI = baseurl;
		
		Response response = given()
				.header("Authorization", "Bearer "+Token)
				
				.when()
				.delete("user/"+id)
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				.extract().response();
				
		System.out.println("The User is Deleted");
	}
	
}