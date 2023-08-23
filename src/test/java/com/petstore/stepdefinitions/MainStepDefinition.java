package com.petstore.stepdefinitions;

import com.petstore.utils.Constant;
import com.petstore.utils.DataUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * Created by sekarayukarindra
 */
public class MainStepDefinition {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainStepDefinition.class);

    @Given("^user sends POST request \"([^\"]*)\" with body:$")
    public void userSendsPostRequestWithBody(String arg0, String arg1) {

        DataUtils.setResponse(SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(arg1)
                .when().post(Constant.BASEURL + arg0));
    }

    @Given("user sends GET request with param {string}")
    public void userSendsGetRequestWithParam(String arg0) {

        DataUtils.setResponse(SerenityRest.given()
                .contentType(ContentType.JSON)
                .when().get(Constant.BASEURL + arg0));
    }

    @Then("list of {string} pets are displayed")
    public void listOfPetsAreDisplayed(String arg0) {
        List<String> statuses = List.of("available", "pending", "sold");
        assert statuses.contains(arg0);

        DataUtils.getResponse().then().assertThat().statusCode(200);
        List<String> statusList = DataUtils.getResponse().getBody().jsonPath().getList("status");
        for (String stat : statusList) {
            assert stat.equalsIgnoreCase(arg0);
        }
    }

    @Then("new pet is added with {string} equals to {int}")
    public void newPetIsAddedWithEqualsTo(String arg0, int arg1) {
        if (arg0.equals("tags.id")){
            List<Integer> tempList = DataUtils.getResponse().getBody().jsonPath().getList(arg0);
            for (int id: tempList){
                assert id == arg1;
            }
        } else {
            assert DataUtils.getResponse().getBody().jsonPath().get(arg0).equals(arg1);
        }
    }

    @And("new pet is added with {string} equals to {string}")
    public void newPetIsAddedWithEqualsTo(String arg0, String arg1) {
        if (arg0.equals("tags.name") || arg0.equals("photoUrls")){
            List<String> tempList = DataUtils.getResponse().getBody().jsonPath().getList(arg0);
            for (String item: tempList){
                assert item.equals(arg1);
            }
        } else {
            assert DataUtils.getResponse().getBody().jsonPath().get(arg0).equals(arg1);
        }
    }

    @When("user sends POST request to upload image with id {int} and image {string}")
    public void userSendsPOSTRequestToUploadImageWithIdAndImage(int arg0, String arg1) {
        File file = new File(arg1);
        DataUtils.setResponse(SerenityRest.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .multiPart("file", file, "image/jpeg")
                .when().post(Constant.BASEURL +"/pet/"+ arg0 + "/uploadImage"));
    }

    @Then("image {string} is uploaded")
    public void imageIsUploaded(String arg0) {
        DataUtils.getResponse().then().assertThat().statusCode(200);
        DataUtils.getResponse().then().assertThat().body("message", Matchers.containsStringIgnoringCase(arg0));
    }

    @When("user sends DELETE request {string}")
    public void userSendsDELETERequest(String arg0) {
        DataUtils.setResponse(SerenityRest.given()
                .accept(ContentType.JSON)
                .when().delete(Constant.BASEURL + arg0));
    }

    @Then("pet with id {string} is deleted")
    public void petWithIdIsDeleted(String arg0) {
        DataUtils.getResponse().then().assertThat().statusCode(200);
        DataUtils.getResponse().then().assertThat().body("message", Matchers.equalTo(arg0));
    }
}
