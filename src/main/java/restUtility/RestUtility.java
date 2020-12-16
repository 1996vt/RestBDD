package restUtility;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.json.simple.JSONObject;

import io.restassured.response.Response;

public class RestUtility {
	static Response response;
	static String url;
	static String id;

	static HashMap<String, String> hmap = new HashMap<String, String>();

	public static void initializeUrl(String api) throws Exception {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "Api.properties");
		Properties prop = new Properties();
		prop.load(fis);
		if (api.equals("register user")) {
			url = prop.getProperty("RegisterApi");
		} else if (api.equals("login user")) {
			url = prop.getProperty("LoginApi");
		} else if (api.equals("single user")) {
			url = prop.getProperty("SingleUserApi");
		}
	}

	public static void triggerPostRequest(JSONObject jsonObj) throws IOException {
		response = given().relaxedHTTPSValidation().contentType("application/json").body(jsonObj).when().post(url);

	}

	public static void selectUserId(String userId) {
		id = userId;
	}

	public static void triggerGetRequest(String userId) {
		response = given().contentType("application/json").when().get(url.replace("***", userId));
	}

	public static void validateStatus(String statusCode) {
		assertTrue(response.statusCode() == Integer.parseInt(statusCode));
		System.out.println("Status Code validated as " + statusCode);
	}

	public static void storeToken(String email) {
		hmap.put(email, (String) response.jsonPath().get("token"));
	}

	public static void validateToken(String email) {
		if (response.statusCode() == 200) {
			assertTrue(hmap.get(email).equals((String) response.jsonPath().get("token")));
			System.out.println(email + " Email validated with token " + (String) response.jsonPath().get("token"));
		} else {
			System.out.println(" " + response.body().asPrettyString());
		}
	}

	public static void validateName(String fname, String lname, int Id) {
		if (response.statusCode() == 200) {
			assertEquals(Id, response.jsonPath().get("data.id"));
			assertEquals(fname, response.jsonPath().get("data.first_name"));
			assertEquals(lname, response.jsonPath().get("data.last_name"));
			System.out.println("User Id " + Id + " Validated for " + fname + " " + lname);
		} else {
			System.out.println(" " + response.body().asPrettyString());
		}
	}

}