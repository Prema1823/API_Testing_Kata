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
import org.json.JSONObject;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
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
    int bookingid;

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
        response.then().assertThat()
        .body(matchesJsonSchemaInClasspath(schemapath));
    }


    @Then("Verify the response status code as {int}")
    public void verify_response_code ( int expectedStatusCode){
        System.out.println("Response status code" +response.getStatusCode());
        assertEquals(expectedStatusCode, response.getStatusCode(),"Response Code mistmatches!");

    }


    @Then("Verify the response status code as {int} and error message {string}")
    public void verify_response_code_errorMessage(int expectedStatusCode,String errormessage){
        assertEquals(expectedStatusCode, response.getStatusCode());
        List<String> actualErrors = response.jsonPath().getList("errors");
        String actualMessage=actualErrors.get(0);
        System.out.println("Actual Message"  +actualMessage);
        assertEquals(errormessage,actualMessage);
    }

    @When("user logs in as admin generates token and authenticate using {string} and {string}")
    public void user_logs_asadmin_generates_token(String uname,String pword){

        JSONObject loginAuth = new JSONObject();
        loginAuth.put("username", uname);
        loginAuth.put("password", pword);
        response = requestSetup().body(loginAuth.toString())
                .when()
                .post(declareendpoint.getEndpoint());
        String token = response.jsonPath().getString("token");
        declareendpoint.setToken(token);
    }


    @When("user logs in as admin and request details by roomid")
    public void user_logsasadmin_requestby_roomid(){
        response = requestSetup()
                .cookie("token", declareendpoint.getToken())
                .queryParam("roomid", bookingRequestFields.getRoomid())
                .when()
                .get(declareendpoint.getEndpoint());
        bookingid = response.jsonPath().getInt("bookings[0].bookingid");
        System.out.println("Booking ID of the booked room = " + bookingid);
        //bookingRequestFields.setbookingid(bookingid);
    }

    @When("admin edits the room details")
    public void admin_edits_room_details(final DataTable editInput) throws JsonProcessingException {

        setdates = new bookingRequestFields.BookingDates();
        for (Map<String, String> editdata : editInput.asMaps(String.class, String.class)) {
            bookingRequestFields.setFirstname(editdata.get("fname"));
            bookingRequestFields.setLastname(editdata.get("lname"));
            bookingRequestFields.setemail(editdata.get("email"));
            bookingRequestFields.setphone(editdata.get("phone"));
            bookingRequestFields.setBookingdates(setdates);
            setdates.setCheckin(editdata.get("checkin"));
            setdates.setCheckout(editdata.get("checkout"));
            bookingRequestFields.setRoomid(Integer.parseInt(generateRandomRoomId()));
            bookingRequestFields.setdepositpaid(false);
            bookingRequestFields.setbookingid(bookingid);
            // Serialize this object
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(bookingRequestFields);

            System.out.println("Request Body:\n" + requestBody);
            System.out.println( declareendpoint.getEndpoint() +bookingid);
            response = requestSetup()
                    .cookie("token", declareendpoint.getToken())
                    .body(requestBody)
                    .when()
                    .put(declareendpoint.getEndpoint() + bookingid);


        }
    }

    @And("verify the updated fields in the response body")
    public void verify_updatedfields_response_body(){
        String respfirstname = response.jsonPath().getString("bookings[0].firstname");
        String resplastname = response.jsonPath().getString("bookings[0].lastname");
        String respemail = response.jsonPath().getString("bookings[0].email");
        String respcheckin = response.jsonPath().getString("bookings[0].bookingdates.checkin");
        String respCheckout = response.jsonPath().getString("bookings[0].bookingdates.checkout");
        //Assert response values from Edit with the input passed
        Assert.assertEquals(respfirstname, bookingRequestFields.getFirstname(), "Firstname mismatch!");
        Assert.assertEquals(resplastname, bookingRequestFields.getLastname(), "Lastname mismatch!");
        Assert.assertEquals(respemail, bookingRequestFields.getemail(), "Email mismatch!");
        Assert.assertEquals(respcheckin, bookingRequestFields.getBookingdates().getCheckin(), "Check-in date mismatch!");
        Assert.assertEquals(respCheckout, bookingRequestFields.getBookingdates().getCheckout(), "Check-out date mismatch!");

    }

    @When("admin deletes the booking using booking id")
    public void admin_deletes_booking_using_bookingid(){
        System.out.println(declareendpoint.getEndpoint() +bookingid);
        response = requestSetup()
                .cookie("token",declareendpoint.getToken())
                .when()
                .delete(declareendpoint.getEndpoint() +bookingid);
    }
}
