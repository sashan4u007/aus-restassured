package api.utilities;

import java.io.File;
import java.io.FileInputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

import org.json.JSONObject;
import org.json.JSONTokener;

import io.restassured.response.Response;

public class SchemaValidator {
	public static boolean isResponseBodyMatchWithSchema(String schemaName, Response response) {

		try {

			FileInputStream f = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\schemas\\" + schemaName);
			JSONObject schemaJson = new JSONObject(new JSONTokener(f));
			Schema schema = SchemaLoader.load(schemaJson);
			// Incoming JSON data from API request
			JSONObject requestData = new JSONObject(new JSONTokener(response.getBody().asInputStream()));
			// Validate incoming data against JSON Schema
			try {
				schema.validate(requestData);
				return true;
			} catch (Exception e) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
