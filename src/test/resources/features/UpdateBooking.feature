
@HotelBooking
Feature: Edit the booking details by logging  as admin

  Background:
    Given the user hits the endpoint "api/auth/login"
    When user logs in as admin generates token and authenticate using "admin" and "password"
    Then Verify the response status code as 200

    @happyflowwithallfields
  Scenario Outline: Edit the booking details
    Given the user hits the endpoint "<endpointurl>"
    When user provides the details and books the room with

      | fname   | lname   | email | phone  | checkin  | checkout   |
      | <fname> | <lname> | <email> | <phone> | <checkin> | <checkout> |
    Then Verify the response status code as 201
    Given the user hits the endpoint "<endpointurl>"
    When user logs in as admin and request details by roomid
    Then Verify the response status code as 200
    Given the user hits the endpoint "api/booking/"
    When admin edits the room details
      | fname | lname | email              | phone         | checkin    | checkout   |
      | Prem1    | Sagar1   	 | premsagar@gmail.com | 09876543212   | 2025-10-18 | 2025-10-19|
    Then Verify the response status code as 200

    Examples:
      |endpointurl|fname|lname|checkin|checkout|email|phone|
      |api/booking|BNPPb|accountb|2025-12-10|2025-12-11|bnppaccount1@mail.com|07907945853|

      @MissingmandatoryfieldsinEditBooking
      Scenario Outline: Edit the booking details
        Given the user hits the endpoint "<endpointurl>"
        When user provides the details and books the room with

          | fname   | lname   | email | phone  | checkin  | checkout   |
          | <fname> | <lname> | <email> | <phone> | <checkin> | <checkout> |
        Then Verify the response status code as 201
        Given the user hits the endpoint "<endpointurl>"
        When user logs in as admin and request details by roomid
        Then Verify the response status code as 200
        Given the user hits the endpoint "api/booking/"
        When admin edits the room details
          | fnameedit | lname | email              | phone         | checkin    | checkout   |
          |     | Ruth   	 | premsagar@gmail.com | 09876543212   | 2025-10-18 | 2025-10-19|
        Then Verify the response status code as 400 and error message "<errorMessage>"

        Examples:
          |endpointurl|fname|lname|checkin|checkout|email|phone|errorMessage|
          |api/booking|Prabhu|Ruth|2025-11-16|2025-11-17|premsagar@tcs.com|07907945852|Firstname should not be blank|