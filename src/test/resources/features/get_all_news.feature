Feature: Get all news
  As a user
  I want to retrieve all news from the API
  So that I can see the latest updates

  Scenario: Successfully retrieve all news
    Given Postman is opened
    And the GET method is chosen
    When I input a valid endpoint "https://backend.historycode.online/api/News/GetAll"
    And I click the 'Send' button
    Then the response status code should be 200
    And the response body should be in JSON format
    And the response should contain the following fields:
      | field          | type   |
      | id            | int    |
      | title         | string |
      | text          | string |
      | imageId       | int    |
      | url           | string |
      | image         | object |
      | creationDate  | string |
    And the image object should contain:
      | field        | type   |
      | id          | int    |
      | blobName    | string |
      | base64      | string |
      | mimeType    | string |
      | imageDetails | object |
    And the imageDetails object should contain:
      | field   | type   |
      | id      | int    |
      | title   | string |
      | alt     | string |
      | imageId | int    |
