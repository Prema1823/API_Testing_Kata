
@HotelBooking
Feature:Get list of rooms available for booking
  @getlistofrooms
  Scenario Outline:Get list of rooms availabe for booking
    Given the user hits the endpoint "<endpointurl>"
    When the user sends a GET request to the room api
    Then Verify the response status code as 200
    And the response should match the JSON schema "<schemapath>"

    Examples:
      |endpointurl|schemapath|
      |api/room|schemas/getlistofrooms.json|