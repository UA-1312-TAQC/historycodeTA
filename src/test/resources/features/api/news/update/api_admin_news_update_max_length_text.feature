Feature: Verify Maximum Length Restriction for the "text" Field When Updating News

  Scenario: Verify that the 'text' field does not accept more than 15000 characters when updating news
    Given the user is authenticated with a valid token
    And the PUT method is selected
    And the request body contains:
      """
      {
        "title": "Test News",
        "text": "{{15000CharText}}q",
        "imageId": {{imgId}},
        "url": "news",
        "creationDate": "",
        "id": {{newsId}}
      }
      """
    When I send a PUT request to "https://backend.historycode.online/api/News/Update"
    Then the response status code should be 400
    And the response body should contain the error message:
      """
      "Text": "Max Length is 15000"
      """
