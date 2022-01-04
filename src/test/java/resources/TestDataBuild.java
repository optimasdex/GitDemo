package resources;

import java.util.ArrayList;

import pojo.GmapsAddPlace;
import pojo.Location;

public class TestDataBuild 
{
	public GmapsAddPlace addPlacePayload(String name, String language, String address)
	{
		GmapsAddPlace place1 = new GmapsAddPlace();
		place1.setAccuracy(50);
		place1.setAddress(address);
		place1.setLanguage(language);
		place1.setName(name);
		place1.setPhone_number("(+91) 983 893 3937");
		place1.setWebsite("http://google.com");

		ArrayList<String> aList= new ArrayList<String>(); 
		aList.add("shoe park");
		aList.add("shop");
		place1.setTypes(aList);

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		place1.setLocation(loc);
		
		return place1;
	}
	
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
	}

}
