package com.libraryApp.step_definitions;

// Importing necessary classes and libraries for the test

import com.libraryApp.pages.DashBoardPage;
import com.libraryApp.pages.LoginPage;
import com.libraryApp.utilities.BrowserUtil;
import com.libraryApp.utilities.DB_Util;
import com.libraryApp.utilities.LibraryUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ApiStepDefs {

    @Given("I logged Library api as a {string} sn")
    public void i_logged_library_api_as_a_sn(String string) {

    }
    @Given("Accept header is {string} sn")
    public void accept_header_is_sn(String string) {

    }
    @When("I send GET request to “\\/get_all_users” endpoint sn")
    public void i_send_get_request_to_get_all_users_endpoint_sn() {

    }
    @Then("status code should be {int} sn")
    public void status_code_should_be_sn(Integer int1) {

    }
    @Then("Response Content type is {string} sn")
    public void response_content_type_is_sn(String string) {

    }
    @Then("{string} field should not be null sn")
    public void field_should_not_be_null_sn(String string) {

    }
}
