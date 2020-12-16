package stepdefinition;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import restUtility.RestUtility;

public class UserApiStedefinition extends RestUtility {

	static String methodName;
	static JSONObject jsonObj;
	static String userId;

	@Given("^I initialize \"([^\"]*)\" request for \"([^\"]*)\"$")
	public void setHttpMethod(String methodType, String url) throws Exception {
		methodName = methodType;
		initializeUrl(url);
	}

	@Then("^I select email \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void setData(String email, String password) {
		HashMap<String, String> reqBody = new HashMap<String, String>();
		reqBody.put("email", email);
		reqBody.put("password", password);
		jsonObj = new JSONObject(reqBody);
	}

	@And("I execute the api request")
	public void triggerRequest() throws IOException {
		if (methodName.equals("post")) {
			triggerPostRequest(jsonObj);
		} else if (methodName.equals("get")) {
			triggerGetRequest(userId);
		}
	}

	@And("^I should get statusCode as \"([^\"]*)\"$")
	public void validateRes(String statusCode) {
		validateStatus(statusCode);
	}

	@And("^I should get valid token for \"([^\"]*)\"$")
	public void saveToken(String email) {
		storeToken(email);
	}

	@And("^I validate the token for \"([^\"]*)\"$")
	public void getToken(String email) {
		validateToken(email);
	}

	@And("^I select userId \"([^\"]*)\"$")
	public void setId(String id) {
		userId = id;
	}

	@And("^I should get first name \"([^\"]*)\" , last name \"([^\"]*)\" and Id \"([^\"]*)\"$")
	public void validateGet(String fname, String lname, String Id) {
		validateName(fname, lname, Integer.parseInt(Id));
	}
}
