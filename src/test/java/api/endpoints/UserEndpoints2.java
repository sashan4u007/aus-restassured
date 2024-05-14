package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {
	
	// Method created for getting url's from properties file
	static ResourceBundle getUrl() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUser(User payload) {
		String postUrl = getUrl().getString("postUrl");
		System.out.println(postUrl);
		System.out.println(payload.getEmail());
		System.out.println(payload.getUsername());
		System.out.println(payload.getFirstname());
		System.out.println(payload.getLastname());
		System.out.println(payload.getPassword());
		System.out.println(payload.getId());
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(postUrl);
		
		return response;
	}

	public static Response getUser(String username) {
		String getUrl = getUrl().getString("getUrl");
		Response response = given()
					.pathParam("username", username)
				.when()
					.get(getUrl);
		
		return response;
	}

	public static Response updateUser(String username, User payload) {
		String updateUrl = getUrl().getString("updateUrl");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(updateUrl);
		
		return response;
	}

	public static Response deleteUser(String username) {
		String deleteUrl = getUrl().getString("deleteUrl");
		Response response = given()
					.pathParam("username", username)
				.when()
					.delete(deleteUrl);
		
		return response;
	}
}
