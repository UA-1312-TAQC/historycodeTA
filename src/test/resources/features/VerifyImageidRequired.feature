Feature: Verify that the 'imageId' field is required when updating news using the PUT method

  Background:
    Given The PUT method is chosen
    And The valid endpoint "https://backend.historycode.online/api/News/Update" is selected

  Scenario: Verify the 'imageId' field is required
    Given The request body contains the following data:
      """
      {
        "title": "Test News",
        "text": "News Testing ",
        "imageId": "",
        "url": "news",
        "creationDate": "{{currentDateTime}}",
        "id": "{{newsId}}"
      }
      """
    When The user clicks the 'Send' button
    Then The response status code should be 400
    And The response body should contain the error message:
      """
      {
        "ImageId": "The ImageId is required."
      }
      """
