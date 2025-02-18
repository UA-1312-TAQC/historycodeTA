Feature: Admin cannot update news with 'url' containing special characters using PUT method

  Scenario: Verify that 'url' field does not accept special characters when updating news
    Given Postman is open
    And the user is authorized
    And the PUT method is selected
    And the request body contains:
      """
      {
        "title": "Test News",
        "text": "News Testing ",
        "imageId": {{imgId}},
        "url": "News-Item#",
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
