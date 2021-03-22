package apitesting;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Chucknorris_Testing {
	
	String baseURIString = "https://api.chucknorris.io/jokes";
	RequestSpecification httpRequest;
	
	List<String> categoryList = new ArrayList<String>();
	
	public Chucknorris_Testing() {
		categoryList = getCategories();
	}

	@Test
    public void verifyListOfFixedCategories()
    {
		 Assert.assertTrue(categoryList.size() > 0, "Categories api returns no categories");
	}
	
	@Test
	public void verifyCategoryValueInQuerySearch()
	{
		Random rand = new Random();
	    String randomQueryElement = categoryList.get(rand.nextInt(categoryList.size()));
	    RestAssured.baseURI = baseURIString + "/search";
		httpRequest  = RestAssured.given();
		Response response = httpRequest.request(Method.GET , "?query=" + randomQueryElement);
		List<String> responseValues = response.getBody().jsonPath().getList("result.value");
		Boolean isValueContainsCategory = false;
		for(String value : responseValues) {
			if(value.contains(randomQueryElement)) {
				isValueContainsCategory = true;
				continue;
			} else {
				break;
			}
		}
		Assert.assertTrue(isValueContainsCategory, "The returned items doesn't contain the requested category");
	}
	
	
	public List<String> getCategories() {
		 RestAssured.baseURI = baseURIString + "/categories";
		 httpRequest  = RestAssured.given();
		 Response response = httpRequest.request(Method.GET);
		 return response.getBody().jsonPath().getList("$");
	}

}
