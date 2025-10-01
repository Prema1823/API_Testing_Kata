@HotelBooking

Feature:Get the room availability for the particular dates
  @getroomavailability
  Scenario Outline:Get available rooms for the given dates
    Given the user hits the endpoint "<endpointurl>"
    When user provides queryparam from "<startDate>" and "<EndDate>"
    Then Verify the response status code as 200
    And the response should match the JSON schema "<schemapath>"

    Examples:
      |startDate|EndDate|endpointurl|schemapath|
      |2025-10-04|2025-10-05|api/room|schemas/getroomavailability.json|
