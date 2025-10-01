Feature: Delete the booking

  Background:
    Given the user hits the endpoint "api/auth/login"
    When user logs in as admin generates token and authenticate using "admin" and "password"
    Then Verify the response status code as 200

    @Deletebookinghappyflow
  Scenario Outline: Delete the booking as admin
    Given the user hits the endpoint "<endpointurl>"
    When user provides the details and books the room with

      | fname   | lname   | email | phone  | checkin  | checkout   |
      | <fname> | <lname> | <email> | <phone> | <checkin> | <checkout> |
    Then Verify the response status code as 201
    Given the user hits the endpoint "<endpointurl>"
    When user logs in as admin and request details by roomid
    Then Verify the response status code as 200
    Given the user hits the endpoint "api/booking/"
    When admin deletes the booking using booking id
    Then Verify the response status code as 200

    Examples:
      |endpointurl|fname|lname|checkin|checkout|email|phone|
      |api/booking|Rath|Cath|2025-11-06|2025-11-07|rathcathr@tcs.com|07907945853|
