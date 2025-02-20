Feature: Retrieve news by ID using API

  Scenario: Admin retrieves news by valid ID using GET method
    Given Postman is opened
    And the GET method is chosen
    And there are existing news in the system
    When I input the valid endpoint "https://backend.historycode.online/api/News/GetById"
    And I input a valid "id" value as a parameter
    And I click the "Send" button
    Then the news with the given "id" value should be displayed
    And the status code should be "200 OK"
