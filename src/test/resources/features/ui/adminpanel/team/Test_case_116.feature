Feature: Admin/Team
  As an admin
  I want to add new team member or edit existing one
  So that I can show users who were working on StreetCode project

  Background: 
    Given I am logged in as admin
    And the 'Команда' page in the left menu is opened

  Scenario: Verify if the admin can add a new team member using only the mandatory fields
    When I click the 'Створити нового члена команди' button
    And I fill in the "Прізвище та ім'я" field with "Тарас Тарасович"
    And I add a photo in the 'Фото' block
    And I click the 'Зберегти' button
    Then I should see the 'Члена команди успішно додано/оновлено!' message at the top of the modal window

  After: 
    Then I close the window
    And I delete the created user