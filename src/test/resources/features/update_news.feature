Feature: News API Testing
  As a user
  I want to verify different API functionalities
  So that I can ensure correct behavior

  Scenario: Successfully update an existing news item
    Given Postman is opened
    And the user is authorized
    And there are existing news in the system
    And I retrieve a valid "id" using "https://backend.historycode.online/api/News/GetAll"
    And the PUT method is chosen
    And all required data has been added to the body:
      | field         | value                  |
      | title         | Test News              |
      | text          | News Testing           |
      | imageId       | {{imgId}}              |
      | url          | news                   |
      | creationDate  | {{currentDateTime}}    |
      | id           | {{newsId}}             |
    When I input a valid endpoint "https://backend.historycode.online/api/News/Update"
    And I click the 'Send' button
    Then the news is updated
    And the response status code should be 200
    And the response body should be in JSON format
    And the response should contain the following fields:
      | field         | type   |
      | title         | string |
      | text          | string |
      | imageId       | int    |
      | url          | string |
      | creationDate  | string |
      | id           | int    |
