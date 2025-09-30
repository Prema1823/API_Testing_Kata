
Feature:Create Room Booking for the particular dates

  @CreateroomBookinghappyflow
  Scenario Outline:Create a room booking
    Given the user hits the endpoint "<endpointurl>"
    When user provides the details and books the room with

      | fname   | lname   | email | phone  | checkin  | checkout   |
      | <fname> | <lname> | <email> | <phone> | <checkin> | <checkout> |

    Then Verify the response status code as 201

    Examples:
    |endpointurl|fname|lname|checkin|checkout|email|phone|
    |api/booking|userfirstname1|userlastname1|2025-10-15|2025-10-16|user1@tcs.com|1234567890|


@Missingmandatoryfields


Scenario Outline:Error validation while booking the room
  Given the user hits the endpoint "<endpointurl>"
  When user provides the details and books the room with

    | fname   | lname   | email | phone  | checkin  | checkout   |
    | <fname> | <lname> | <email> | <phone> | <checkin> | <checkout> |

  Then Verify the response status code as 400 and error message "<errorMessage>"

  Examples:
    |endpointurl|fname|lname|checkin|checkout|email|phone|errorMessage|
    |api/booking||userlastname1|2025-10-09|2025-10-10|user1@tcs.com|1234567890|Firstname should not be blank|
    |api/booking|userfirstname2||2025-10-10|2025-10-11|user2@tcs.com|1234567890|size must be between 11 and 21|
    |api/booking|userfirstname3|userlastname3||2025-10-13|user3@tcs.com|1234567890|must not be null|
    |api/booking|userfirstname4|userlastname4|2025-10-14||user4@tcs.com|1234567890|must not be null|
    |api/booking|userfirstname4|userlastname4|2025-10-14|2025-10-15||1234567890|Failed to create booking|
    |api/booking|userfirstname4|userlastname4|2025-10-14|2025-10-15|user4@tcs.com||Failed to create booking|


  @invalidfields
  Scenario Outline:Error validation while booking the room
    Given the user hits the endpoint "<endpointurl>"
    When user provides the details and books the room with

      | fname   | lname   | email | phone  | checkin  | checkout   |
      | <fname> | <lname> | <email> | <phone> | <checkin> | <checkout> |

    Then Verify the response status code as 400 and error message "<errorMessage>"

    Examples:
      |endpointurl|fname|lname|checkin|checkout|email|phone|errorMessage|
      |api/booking|userfirstname1|userlastname1|2025-10-09|2025-10-10|user1@tcs.com|123456789|size must be between 11 and 21|
      |api/booking|userfirstname1|userlastname1|2025-10-09|2025-10-10|user2tcs.com|1234567890|must be a well-formed email address|
      |api/booking|userfirstname1|userlastname1|2025-10|2025-10-10|user2@tcs.com|1234567890|Failed to create booking|
