Feature: Admin/Team
  As an admin
  I want to add new team member or edit existing one
  So that I can show users who were working on StreetCode project

  Background:
    Given I am logged in as admin
    And the 'Команда' page in the left menu is opened

  Scenario: Verify that the new team member is immediately displayed in the list of team members
    When I click the 'Створити нового члена команди' button
    And I fill in the required fields with "Тарас Тарасович" and a photo
    And I click the 'Зберегти' button
    And I close the modal window
    And I click on the last page of the pagination if necessary
    Then I should see the new team member displayed in the list of team members

  After:
    Given I delete the created user