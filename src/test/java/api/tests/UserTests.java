package api.tests;

import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.SchemaValidator;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserTests {
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
		Response response =  UserEndpoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/createUserSchema.json"));
		logger.info("*****User created*****");
		
		assertTrue(SchemaValidator.isResponseBodyMatchWithSchema("createUserSchema.json", response));
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		logger.info("*****Reading user info*****");
		Response response = UserEndpoints.getUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/userSchema.json"));
		logger.info("*****User info displayed*****");
	}

	@Test(priority = 3)
	public void testUpdateUser() {
		logger.info("*****Updating user*****");
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndpoints.updateUser(userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/createUserSchema.json"));
		testGetUser();
		logger.info("*****User is updated*****");
	}

	@Test(priority = 4)
	public void testDeleteUser() {
		logger.info("*****Deleting user*****");
		Response response = UserEndpoints.deleteUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/createUserSchema.json"));
		logger.info("*****User deleted*****");
	}
}
