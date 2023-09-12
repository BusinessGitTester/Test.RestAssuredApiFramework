package api.endPoints;

import static org.hamcrest.Matchers.*;

import api.payLoads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class userEndPoint {
	
	public static Response createUser(User payload)
	{
		Response response = given()
				              .contentType(ContentType.JSON)
				              .accept(ContentType.JSON)
				              .body(payload)
				            .when()
				              .post(Route.postUrl);
		
		return response;
	}
	
	public static Response getUser(String userName)
	{
		Response response = given()
				              .pathParam("username",userName)
				            .when()
				              .get(Route.getUrl);
		
		return response;
	}
	
	public static Response updateUser(User payload,String userName)
	{
		Response response = given()
				              .contentType(ContentType.JSON)
				              .accept(ContentType.JSON)
				              .body(payload)
				              .pathParam("username",userName)
				            .when()
				              .put(Route.updateUrl);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = given()
				              .pathParam("username",userName)
				            .when()
				              .delete(Route.deleteUrl);
		
		return response;
	}

}
