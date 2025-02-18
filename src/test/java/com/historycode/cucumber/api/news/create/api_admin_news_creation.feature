Feature: Admin creates a news item using POST method

  Scenario: Verify that a news item is created with all required data
    Given Postman is opened
    And the user is authorized
    And an image with 'imageId' was previously created
    And the POST method is selected
    And the request body contains:
      """
      {
        "title": "Test News Item",
        "text": "News Item Testing",
        "imageId": {{imgId}},
        "url": "news-item",
        "creationDate": "{{currentDateTime}}"
      }
      """
    When I send a POST request to "https://backend.historycode.online/api/News/Create"
    Then the response status code should be 200
    And the response body should match:
      """
      {
        "title": "string",
        "text": "string",
        "imageId": 0,
        "url": "string",
        "creationDate": "2025-01-12T10:11:11.396Z"
      }
      """
