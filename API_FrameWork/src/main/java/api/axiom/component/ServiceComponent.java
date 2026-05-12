package api.axiom.component;

import java.io.IOException;

import api.axiom.objectrepository.CoreApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;


import static io.restassured.RestAssured.given;

//Type: Get, Return type:Response
public class ServiceComponent extends CoreApi {

	// Scenario1-Invoking the service without parameterization
	public Response invokeRestServiceEmployees(String uriFilePath, String resourceFilePath) throws Exception {

		Response response = null;
		try {
			RestAssured.baseURI = uriFilePath;
			response = given().header("content-Type", ContentType.JSON, "Accept", ContentType.JSON).when()
					.get(resourceFilePath).then().extract().response();
			System.out.println("The output is: " +response.getBody().asString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("Issue in Get method");

		}
		return response;

	}

	public int getStatusCode(Response response) {
		return response.getStatusCode();

	}

	// Scenario 2- Invoking the service with parameterization
	public Response invokeRestServiceEmployee(String uriFilePath,String employeeResourcePath) throws Exception {

		Response response = null;
		try {
			RestAssured.baseURI = uriFilePath;
			response = given().pathParam("EmployeeID", employeeID).
					when().get(employeeResourcePath+"/{EmployeeID}").
					then().extract().response();	
			//response.then().assertThat().body(matchesJsonSchemaInClasspath("./resources/employee-details.json"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("Issue in retreiving Employee details");

		}
		return response;

	}

}
