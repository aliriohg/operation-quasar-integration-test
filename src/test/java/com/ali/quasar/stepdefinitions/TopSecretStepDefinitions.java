package com.ali.quasar.stepdefinitions;

import com.ali.quasar.entities.*;
import com.ali.quasar.utils.ResponseWrapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class TopSecretStepDefinitions {
    @Value("${quasar.endpoint}")
    private String endpoint;
    @Autowired
    private ResponseWrapper response;

    @When("the user send request to topsecret")
    public void theUserSendRequestToTopsecret(List<SatelliteMessage> messages) {
        response.setResponse(RestAssured.given().log().all().contentType(ContentType.JSON).baseUri(endpoint).body(TopSecret.builder().satellites(messages).build())
                .when().post("topsecret").then().log().all().extract().response());
    }

    @Then("should response with message {string} and position")
    public void shouldResponseWithMessageAndPosition(String message, Location location) {
        MessageResponse actual = response.getResponse().as(MessageResponse.class);
        MessageResponse expected = MessageResponse.builder().message(message).position(location).build();
        assertEquals(expected, actual);
    }

    @Then("should response with error {int} and messageError")
    public void shouldResponseWithErrorAndMessageError(int statusCode, ErrorMessage expected) {
        response.getResponse().then().statusCode(statusCode);
        ErrorMessage actual = response.getResponse().as(ErrorMessage.class);
        expected.setDatetime(actual.getDatetime());
        assertEquals(expected, actual);
    }
}
