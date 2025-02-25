Feature: Admin/Team
  As an admin
  I want to add a new team member or edit an existing one
  So that I can show users who were working on the StreetCode project

  Background: 
    Given I am logged in as an admin
    And at least one team member has been added
	
  Scenario: Verify that the admin can delete the team member
    Given the 'Команда' page in the left menu is opened
    When I click the 'Delete' button next to the desired team member
    And I click the 'OK' button in the confirmation modal window
    Then the team member should no longer be displayed in the list of team members