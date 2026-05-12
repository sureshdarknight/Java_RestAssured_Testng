package api.axiom.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import api.axiom.component.ServiceComponent;
import api.axiom.objectrepository.CoreApi;
import io.restassured.response.Response;

public class Scenario1_ToPickApiLinkFromConfigFile extends CoreApi {
	ServiceComponent servicecomponent = new ServiceComponent();
	Response response = null;

	// Scenario1- Assertion to verify 200
	public void assertionStatusCode() {
		try {
			System.out.println("Scenario 1: Assertion Status code begins");
			// Get Response
			response = servicecomponent.invokeRestServiceEmployees(CoreApi.uri, CoreApi.employeesresources);
			// Assert Status code as 200
			Assert.assertEquals(200, response.getStatusCode());
			logger.log(Status.PASS, MarkupHelper.createLabel("Scenario 1: Status code from API returns successfully: " +response.getStatusCode(), ExtentColor.GREEN));
			System.out.println("Status code is successfully validated as " + response.getStatusCode());
			System.out.println("Scenario 1: Assertion status code end");
		} catch (Exception e) {
			logger.log(Status.FAIL, MarkupHelper.createLabel("Scenario 1: Status code from API is " +response.getStatusCode()+ " instead of 200", ExtentColor.RED));
			System.out.println(e.getMessage());
			System.out.println("Scenario 1: Assertion status code end");
		}

	}

	// Scenario 2-Assertion to verify profile image is blank for all employee

	public void assertionProfileImage() throws Exception {
		System.out.println("Scenario 2: Assertion Profile Image for all employees begins");
		String profile_image = null;
		int size = response.jsonPath().getList("data.id").size();
		for (int i = 0; i < size; i++) {
			profile_image = response.jsonPath().getString("data.profile_image[" + i + "]");
			try {
				Assert.assertEquals(profile_image, "");
				logger.log(Status.PASS, MarkupHelper.createLabel("Profile image for the following Employee " +response.jsonPath().getString("data.employee_name[" + i + "] ") +" is blank as expected", ExtentColor.GREEN));
				System.out.println(response.jsonPath().getString("data.employee_name[" + i + "] ")
						+ " is having the profile image as blank");
				System.out.println("Scenario 2: Assertion Profile Image for all employees ends");
			} catch (Exception e) {
				logger.log(Status.FAIL, MarkupHelper.createLabel("Profile image for the following Employee " +response.jsonPath().getString("data.employee_name[" + i + "] ") + " is not blank", ExtentColor.RED));
				System.out.println(response.jsonPath().getString("data.employee_name[" + i + "] ")
						+ " is not having the blank profile image");
				System.out.println("Scenario 2: Assertion Profile Image for all employees ends");
			}

		}

	}
}
