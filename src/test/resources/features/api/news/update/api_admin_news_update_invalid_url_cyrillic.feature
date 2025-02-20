Feature: Admin cannot update news with a 'url' containing Cyrillic letters using PUT method

  Scenario: Verify that 'url' field does not accept Cyrillic letters when updating news
    Given Postman is opened
    And the user is authorized
    And the PUT method is selected
    And the request body contains:
      """
      {
        "title": "Test News",
        "text": "News Testing ",
        "imageId": {{imgId}},
        "url": "новина",
        "creationDate": "{{currentDateTime}}",
        "id": {{newsId}}
      }
      """
    When I send a PUT request to "https://backend.historycode.online/api/News/Update"
    Then the response status code should be 400
    And the response body should contain the error message:
      """
      "Url Is Invalid"
      """
