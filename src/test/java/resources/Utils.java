package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils 
{
	public static RequestSpecification reqSpec;
	public RequestSpecification requestSpecification() throws IOException
	{

//		RestAssured.baseURI = "https://rahulshettyacademy.com";
		if(reqSpec==null)
		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return reqSpec;
		}
		return reqSpec;
	}

	public String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\Workspace\\TestProject\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key)
	{
	    String resp = response.asString();
	    JsonPath js = new JsonPath(resp);
	    // branch code
	    System.out.println("GIT Branch code");
	    return js.getString(key);
	    

	}
}
