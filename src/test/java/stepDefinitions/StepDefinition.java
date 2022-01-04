package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GmapsAddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils
{
	RequestSpecification req;
	ResponseSpecification resSpec;
	Response response;
	static String place_id;
	
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions

		req = given().log().all().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		{
			response = req.when().post(resourceAPI.getResource());
//				.then().spec(resSpec).body("scope", equalTo("APP"))
//				.header("server", equalTo("Apache/2.4.18 (Ubuntu)")).extract().response();
		}
		else if(method.equalsIgnoreCase("GET"))
		{
			response = req.when().get(resourceAPI.getResource());
//				.then().spec(resSpec).body("scope", equalTo("APP"))
//				.header("server", equalTo("Apache/2.4.18 (Ubuntu)")).extract().response();
		}
		else if(method.equalsIgnoreCase("DELETE"))
		{
			response = req.when().delete(resourceAPI.getResource());
		}
	}
	@Then("the API call gets success with status code {int}")
	public void the_api_call_gets_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}
	@And("{string} in response body is {string}")
	public void in_response_body_is(String expectedkey, String expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(getJsonPath(response, expectedkey), expectedvalue);
	}
	@And("verify place_id created	maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String Expectedvalue, String resource) throws IOException
	{
	    // Write code here that turns the phrase above into concrete actions
		place_id = getJsonPath(response, "place_id");
		req = given().log().all().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		assertEquals(getJsonPath(response,"name"),Expectedvalue);
	}
	
	@Given("Deleteplace Payload")
	public void deleteplace_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		req = given().log().all().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
	}
}


















