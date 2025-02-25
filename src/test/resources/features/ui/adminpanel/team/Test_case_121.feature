Feature: Admin/Team
  As an admin
  I want to add a new team member or edit an existing one
  So that I can show users who were working on the StreetCode project

  Background: 
    Given I am logged in as an admin
    And the 'Команда' page in the left menu is opened

  Scenario: Verify that the admin can mark a member as a "Key member" via a radiobutton
    When I click the 'Створити нового члена команди' button
    And I fill in all mandatory fields
    And I click on the 'Ключовий член команди' radiobutton
    And I click the 'Зберегти' button
    And I close the window and go to the main page
    Then a star should be displayed next to the team member's name to indicate a key team member
    And the team member should be displayed in the 'Команда' block
  
  After:
    Given I delete the created user