
@HotelBooking
Feature:Get list of rooms by roomid for booking
  @getlistofroomsbyroomidhappyflow
  Scenario Outline:Get list of rooms by roomid
    Given the user hits the endpoint "<endpointurl>"
    When the user sends a GET request by passing roomid "<roomid>"
    Then Verify the response status code as 200

    Examples:
      |endpointurl|roomid|
      |api/room/|2|

  @invalidroomid
  Scenario Outline:Get list of rooms by roomid
    Given the user hits the endpoint "<endpointurl>"
    When the user sends a GET request by passing roomid "<roomid>"
    Then Verify the response status code as 404

    Examples:
      |endpointurl|roomid|
      |api/room/|a|

  @roomiddoesnotexist
  Scenario Outline:Get list of rooms by roomid
    Given the user hits the endpoint "<endpointurl>"
    When the user sends a GET request by passing roomid "<roomid>"
    Then Verify the response status code as 500

    Examples:
      |endpointurl|roomid|
      |api/room/|114|

    @emptyroomid
    Scenario Outline:Get list of rooms by roomid
      Given the user hits the endpoint "<endpointurl>"
      When the user sends a GET request by passing roomid "<roomid>"
      Then Verify the response status code as 200

      Examples:
        |endpointurl|roomid|
        |api/room/||