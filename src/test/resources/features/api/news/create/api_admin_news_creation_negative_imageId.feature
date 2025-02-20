Feature: Admin cannot create a news item without imageId using POST method

  Scenario: Verify that news cannot be created if the mandatory field 'imageId' is empty
    Given Postman is opened
    And the user is authorized
    And the POST method is selected
    And the request body contains:
      """
      {
        "id": 0,
        "title": "string",
        "text": "string",
        "imageId": "",
        "url": "string",
        "creationDate": "2024-09-11T08:57:56.061Z"
      }
      """
    When I send a POST request to "https://backend.historycode.online/api/News/Create"
    Then the response status code should be 400
    And the response body should contain the error message:
      """
      "The ImageId is required."
      """
