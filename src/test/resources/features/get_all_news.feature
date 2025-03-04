Feature: Get all news
  As a user
  I want to retrieve all news from the API
  So that I can see the latest updates

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Successfully retrieve all news
    When I navigate to the "Новини" tab
    Then I Get all News
    And I click on the first News
    And the News should contain the information:
      | title        | string |
      | text         | string |
      | imageId      | int    |
      | image        | object |
      | creationDate | string |