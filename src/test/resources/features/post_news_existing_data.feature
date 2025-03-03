Feature: Verify that a news item cannot be created with an already existing title and link

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Verify that creating a news item with the same title and link fails
    When I navigate to the "Новини" tab
    And I click on the Створити новину button
    And I am writing a new "Title" with "Тестова новина"
    And I am writing a new "Link" with "test-link"
    Then I am writing a new "Text" with 'Введіть текст'
    And I am writing a new "Image" with 'Додайте зображення'
    And I am writing a new "Date" with 'Введіть дату'
    And I save the news

    ## Trying to create the same news again

    When I navigate to the "Новини" tab
    And I click on the Створити новину button
    And I try to "Title" create the same "Тестова новина" again
    And I try to "Link" create the same "test-link" again
#    Then I try to "Text" create the same 'Введіть текст' again
#    And I try to "Image" create the same 'Додайте зображення' again
#    And I try to "Date" create the same 'Введіть дату' again
    And I save the news
    Then I should see the error notification
