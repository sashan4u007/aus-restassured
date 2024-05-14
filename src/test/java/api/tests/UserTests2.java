package api.tests;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints2;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests2 {
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		logger = LogManager.getLogger(this.getClass());
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		logger.info("*****Creating user*****");
		Response response =  UserEndpoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*****User created*****");
	}
	
	@Test(priority = 2, enabled = false)
	public void testGetUser() {
		logger.info("*****Reading user info*****");
		Response response = UserEndpoints2.getUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*****User info displayed*****");
	}

	@Test(priority = 3, enabled = false)
	public void testUpdateUser() {
		logger.info("*****Updating user*****");
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndpoints2.updateUser(userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		testGetUser();
		logger.info("*****User is updated*****");
	}

	@Test(priority = 4, enabled = false)
	public void testDeleteUser() {
		logger.info("*****Deleting user*****");
		Response response = UserEndpoints2.deleteUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*****User deleted*****");

	}
}
