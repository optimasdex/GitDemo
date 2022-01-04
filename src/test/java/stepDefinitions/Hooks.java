package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks 
{
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefinition stepObj = new StepDefinition();
		if(StepDefinition.place_id==null)
		{
			stepObj.add_place_payload_with("Varun", "Eng", "Asia");
			stepObj.user_calls_with_http_request("AddPlaceAPI", "POST");
			stepObj.verify_place_id_created_maps_to_using("Varun", "GetPlaceAPI");
		}
	}
}
