Feature: Admin/Team
  As an admin
  I want to add a new team member or edit an existing one
  So that I can show users who were working on the StreetCode project
  Background: 
    Given I am logged in as an admin
    And at least one team member has been added

  Scenario: Verify that the admin can add 8 social links to the team member
    When I click the 'Створити нового члена команди' button
    And I fill in all mandatory fields
    And I add 8 valid social links to the 'Соціальна мережа' fields
    And I click the 'Зберегти' button
    Then the new team member should be displayed in the list of team members with 8 social media icons representing the provided links
	
  After:
    Given I delete the created user