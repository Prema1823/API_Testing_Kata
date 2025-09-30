package com.booking.stepdefinitions;


import base.Utils;
import base.bookingRequestFields;
import base.declareendpoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;


public class Bookingsteps extends Utils {
        //set Endpoint
        //Authorization
        //headers like content type - application/json or cookies
        //Query params
        //POST/PUT - Request body or GET
        //Verify response code and response body,schema
    bookingRequestFields.BookingDates setdates;
        protected bookingRequestFields bookingRequestFields = new bookingRequestFields();
        protected declareendpoint declareendpoint= new declareendpoint();
        protected Response response;
        ObjectMapper mapper;


        @Given("the user hits the endpoint {string}")
        public void user_hits_the_endpoint (String endpoint){
            declareendpoint.setEndpoint(endpoint);
        }


        @When("user provides queryparam from {string} and {string}")
        public void user_provides_queryparam (String checkindate, String checkoutdate){
            response = requestSetup()
                    .queryParam("checkindate", "2025-10-04")
                    .queryParam("checkoutdate", "2025-10-05")
                    .when()
                    .get(declareendpoint.getEndpoint());
            System.out.println(response);
            System.out.println(response.statusCode());


        }

    @When("user provides the details and books the room with")
       public void user_provide_details_bookstheroom(final DataTable dataInput) throws JsonProcessingException {

        setdates = new bookingRequestFields.BookingDates();
        for (Map<String, String> data : dataInput.asMaps(String.class, String.class)) {
            bookingRequestFields.setFirstname(data.get("fname"));
            bookingRequestFields.setLastname(data.get("lname"));
            bookingRequestFields.setemail(data.get("email"));
            bookingRequestFields.setphone(data.get("phone"));
            bookingRequestFields.setBookingdates(setdates);
            setdates.setCheckin(data.get("checkin"));
            setdates.setCheckout(data.get("checkout"));
            bookingRequestFields.setRoomid(Integer.parseInt(generateRandomRoomId()));
            bookingRequestFields.setdepositpaid(false);
        }
// Serialize this object
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(bookingRequestFields);

        System.out.println("Request Body:\n" + requestBody);

        response = requestSetup()
            .given()
            .body(requestBody).when().post(declareendpoint.getEndpoint());

    }


    @And("the response should match the JSON schema {string}")
    public void response_matches_jsonschema ( String schemapath){
    response.then().body(matchesJsonSchemaInClasspath(schemapath));
    }


    @Then("Verify the response status code as {int}")
        public void verify_response_code ( int expectedStatusCode){
            assertEquals(expectedStatusCode, response.getStatusCode());
        }
        
 @Then("Verify the response status code as {int} and error message {string}")
    public void verify_response_code_errorMessage(int expectedStatusCode,String errormessage){
        assertEquals(expectedStatusCode, response.getStatusCode());
        List<String> actualErrors = response.jsonPath().getList("errors");
        String actualMessage=actualErrors.get(0);
        System.out.println("Actual Message"  +actualMessage);
        assertEquals(errormessage,actualMessage);
    }
    }

