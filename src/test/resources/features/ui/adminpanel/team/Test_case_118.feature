Feature: Admin/Team
  As an admin
  I want to add a new team member or edit an existing one
  So that I can show users who were working on the StreetCode project

  Background:
    Given I am logged in as an admin
    And the 'Команда' page in the left menu is opened

  Scenario: Verify that the admin can add the position from the existing dropdown list
    When I click on the 'Створити нового члена команди' button
    And I fill in all mandatory fields
    And I click on the 'Позиції' field 
	And I choose an existing position from the dropdown list
    And I click on the 'Зберегти' button
    And I close the modal window
    Then the chosen position should be displayed for the newly added team member
	
  After:
    Given I delete the created user