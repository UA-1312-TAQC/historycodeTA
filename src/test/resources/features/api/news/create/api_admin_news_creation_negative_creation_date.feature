Feature: Admin cannot create a news item without creationDate using POST method

  Scenario: Verify that news cannot be created if the mandatory field 'creationDate' is empty
    Given Postman is opened
    And the user is authorized
    And the POST method is selected
    And the request body contains:
      """
      {
        "title": "Test News Item",
        "text": "News Item Testing ",
        "imageId": {{imgId}},
        "url": "news-item",
        "creationDate": ""
      }
      """
    When I send a POST request to "https://backend.historycode.online/api/News/Create"
    Then the response status code should be 400
    And the response body should contain the error message:
      """
      "The Creation Date field is required."
      """
