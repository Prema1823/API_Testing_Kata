
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
    |api/booking|userfirstname1|userlastname1|2025-10-09|2025-10-10|user1@tcs.com|1234567890|


@Roombookingerrorflow


Scenario Outline:Error validation while booking the room
  Given the user hits the endpoint "<endpointurl>"
  When user provides the details and books the room with

    | fname   | lname   | email | phone  | checkin  | checkout   |
    | <fname> | <lname> | <email> | <phone> | <checkin> | <checkout> |

  Then Verify the response status code as 400

  Examples:
    |endpointurl|fname|lname|checkin|checkout|email|phone|
    |api/booking||userlastname1|2025-10-09|2025-10-10|user1@tcs.com|1234567890|
    |api/booking|userfirstname2||2025-10-10|2025-10-11|user2@tcs.com|1234567890|
    |api/booking|userfirstname3|userlastname3||2025-10-13|user3@tcs.com|1234567890|
    |api/booking|userfirstname4|userlastname4|2025-10-14||user4@tcs.com|1234567890|
    |api/booking|userfirstname4|userlastname4|2025-10-14|2025-10-15||1234567890|
    |api/booking|userfirstname4|userlastname4|2025-10-14|2025-10-15|user4@tcs.com||
