Feature: Verify that the 'imageId' field is required when updating news using the PUT method

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Verify the 'imageId' field is required
    When I navigate to the "Новини" tab
    And I click on the Створити новину button
    And I create the news with "Title" to "Ukraine Modern"
    And I create the news with "Link" to "ukraine-modern"
    And I create the news with "Text" to "Ukrainian Modern is a fusion of traditional culture.."
    And I create the news with "Image" to ''
    And I create the news with "Date" to 'Введіть дату'
    And I click on the Save button
    Then The news item should be error
