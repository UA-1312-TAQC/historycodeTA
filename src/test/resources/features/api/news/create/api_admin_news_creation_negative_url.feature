Feature: Admin cannot create a news item without url using POST method

  Scenario: Verify that news cannot be created if the mandatory field 'url' is empty
    Given Postman is opened
    And the user is authorized
    And the POST method is selected
    And the request body contains:
      """
      {
        "title": "Test News Item",
        "text": "News Item Testing ",
        "imageId": {{imgId}},
        "url": "",
        "creationDate": "{{currentDateTime}}"
      }
      """
    When I send a POST request to "https://backend.historycode.online/api/News/Create"
    Then the response status code should be 400
    And the response body should contain the error message:
      """
      "The URL field is required."
      """
