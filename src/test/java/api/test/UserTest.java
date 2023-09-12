package api.test;


import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endPoints.userEndPoint;
import api.payLoads.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class UserTest {

	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		logger = LogManager.getLogger(this.getClass());
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		Response response = userEndPoint.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("post request done");
	}
	
	@Test(priority=2)
	public void testGetUser()
	{
		Response response = userEndPoint.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("get user done");
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		//update firstname and email
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		
		Response response = userEndPoint.updateUser(userPayload, this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//get updated response
		Response response1 = userEndPoint.getUser(this.userPayload.getUsername());
		response1.then().log().all();
		logger.info("update user done");
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		Response response = userEndPoint.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("delete user done");
	}
}
