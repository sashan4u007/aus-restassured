package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

	@Test(priority = 1, dataProvider = "data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName, String fName, String lName, String useremail, String pwd, String ph) {
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setFirstname(fName);
		userPayload.setLastname(lName);
		userPayload.setEmail(useremail);
		userPayload.setUsername(userName);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response =  UserEndpoints.createUser(userPayload);
//		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "usernames", dataProviderClass = DataProviders.class)
	public void testDeleteUsers(String username) {
		
		Response response = UserEndpoints.deleteUser(username);
//		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}
}
