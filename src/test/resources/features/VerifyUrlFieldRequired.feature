Feature: Verify that the 'url' field does not accept values exceeding the 200-character limit when updating news using the PUT method

  Background:
    Given The HTTP method is set to PUT
    And The valid endpoint "https://backend.historycode.online/api/News/Update" is selected

  Scenario: Verify the 'url' field rejects values exceeding 200 characters
    Given The request body contains the following data:
      """
      {
        "title": "Test News",
        "text": "News Testing ",
        "imageId": "{{imgId}}",
        "url": "{{200CharUrl}}q",
        "creationDate": "",
        "id": "{{newsId}}"
      }
      """
    When The user clicks the 'Send' button
    Then The response status code should be 400
    And The response body should contain the error message:
      """
      {
        "URL": "Max Length is 200"
      }
      """
