Feature: Get all news
  As a user
  I want to retrieve all news from the API
  So that I can see the latest updates

  Background:
    Given I input a valid endpoint

  Scenario: Successfully retrieve all news
    When Get all News
    Then the response status code should be 200
    And the response body should be in JSON format
    And the response should contain the following fields:
      | field        | type   |
      | id          | int    |
      | title       | string |
      | text        | string |
      | imageId     | int    |
      | url         | string |
      | image       | object |
      | creationDate | string |
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
