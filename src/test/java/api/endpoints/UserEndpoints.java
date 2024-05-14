package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	
	public static Response createUser(User payload) {
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routers.postUrl);
		
		return response;
	}

	public static Response getUser(String username) {
		Response response = given()
					.pathParam("username", username)
				.when()
					.get(Routers.gettUrl);
		
		return response;
	}

	public static Response updateUser(String username, User payload) {
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(Routers.updateUrl);
		
		return response;
	}

	public static Response deleteUser(String username) {
		Response response = given()
					.pathParam("username", username)
				.when()
					.delete(Routers.deleteUrl);
		
		return response;
	}
}
