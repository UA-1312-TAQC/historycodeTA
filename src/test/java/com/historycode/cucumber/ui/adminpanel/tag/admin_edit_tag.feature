Feature: Admin can edit an existing tag

  Background:
    Given I am on the site
    And I log in as an admin

  Scenario: Verify that admin can edit an existing tag
    Given I am logged in as an admin
    And I am on the admin panel page
    And at least one tag exists in the tags list
    When I click on the "Едітор" button
    And I navigate to the "Теги" tab
    And I select a tag from the list
    And I click on the "Pencil" icon in the "Дії" column
    And the modal window appears
    And I edit the tag title
    And I click the "Зберегти" button
    Then the modal window should close
    And the edited tag should display the updated title
