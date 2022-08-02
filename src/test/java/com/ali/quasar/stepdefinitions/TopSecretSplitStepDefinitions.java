package com.ali.quasar.stepdefinitions;

import com.ali.quasar.entities.SatelliteMessage;
import com.ali.quasar.entities.TopSecret;
import com.ali.quasar.utils.ResponseWrapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class TopSecretSplitStepDefinitions {
    @Value("${quasar.endpoint}")
    private String endpoint;
    @Autowired
    private ResponseWrapper response;

    @Given("the user send message to satellite {string} with")
    public void theUserSendMessageToSatelliteWith(String name, SatelliteMessage message) {
        response.setResponse(RestAssured.given().log().all().contentType(ContentType.JSON).baseUri(endpoint)
                .body(message)
                .when().post("topsecret_split/" + name).then().log().all().extract().response());
    }

    @When("the user request the message and location")
    public void theUserRequestTheMessageAndLocation() {
        response.setResponse(RestAssured.given().log().all().contentType(ContentType.JSON).baseUri(endpoint)
                .when().get("topsecret_split").then().log().all().extract().response());
    }
}
