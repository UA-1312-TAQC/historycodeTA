Feature: Admin/Team
  As an admin
  I want to add a new team member or edit an existing one
  So that I can show users who were working on the StreetCode project

  Background:
    Given the user has admin privileges

  Scenario: Verify if the admin can open the 'Додати нового члена команди' modal window to add a new team member
    When the admin logs in to the admin panel
    And the admin navigates to the 'Команда' page in the left menu
    And the admin clicks the 'Створити нового члена команди' button
    Then the 'Додати нового члена команди' modal window should open