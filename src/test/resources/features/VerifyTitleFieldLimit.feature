Feature: Verify that the 'title' field does not accept values exceeding the 100-character limit when updating news using the PUT method

  Background:
    Given The API is accessible at "https://backend.historycode.online/api/"
    And An authorization token has been obtained through the "/auth/login" API
    And A test news item has been created via a POST request to "/News" to obtain a valid id

  Scenario: Verify the 'title' field rejects values exceeding 100 characters
    Given The request body contains the following data:
      """
      {
        "title": "{{100CharTitle}}q",
        "text": "News Testing ",
        "imageId": "{{imgId}}",
        "url": "news",
        "creationDate": "",
        "id": "{{newsId}}"
      }
      """
    And The endpoint "https://backend.historycode.online/api/News/Update" is used
    And The authorization token is included in the request header
    When The user sends the request
    Then The response status code should be 400
    And The response body should contain the error message:
      """
      {
        "Title": "Max Length is 100"
      }
      """

  Scenario: Clean up test data
    Given The test news item is deleted using a DELETE request to "/News/{{newsId}}"

