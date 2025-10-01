@HotelBooking
Feature: Testing end to end flow as admin

  Background:
    Given the user hits the endpoint "api/auth/login"
    When user logs in as admin generates token and authenticate using "admin" and "password"
    Then Verify the response status code as 200

  Scenario Outline: Testing end to end flow as a admin
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
      | BNPP    | Sagar1   	 | premsagar@gmail.com | 09876543212   | 2025-10-18 | 2025-10-19|
    Then Verify the response status code as 200
    When admin deletes the booking using booking id
    Then Verify the response status code as 200

    Examples:
      |endpointurl|fname|lname|checkin|checkout|email|phone|
      |api/booking|Rithaan|Sidhaan|2025-12-13|2025-12-14|ridheasidh@tcs.com|07907846852|
