Feature: Create a News Item

  As an admin
  I want to create a news item
  So that it appears on the site

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Successfully create a 'news' item
    When I navigate to the "Новини" tab
    And I click on the Створити новину button
    And I fill in the "Title" field with "Тестова новина"
    And I fill in the "Link" field with "test-link"
    And I fill in the "Text" field with "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et."
    And I fill in the "Image" field with "https://github.com/user-attachments/assets/45379661-79bc-4c9e-a351-c09739380522"
    And I fill in the "Date" field with "current date"




#  Background:
#    Given I am on the site
#    And I log in as an admin
#
#  Scenario: Successfully create a news item
#    When I navigate to the 'Новини' tab
#    And I click on the 'Створити новину' button
#    And I fill in all mandatory fields with valid data:
#      | Field  | Value |
#      | Title  | Тестова новина |
#      | Link   | test-link |
#      | Text   | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et. |
#      | Image  | https://github.com/user-attachments/assets/45379661-79bc-4c9e-a351-c09739380522 |
#      | Date   | Current date |
#    And I click on the "Зберегти" button
#    Then I should see the notification 'Новина успішно додана/оновлена'
#    And the new news item should be published
#
#  Scenario Cleanup: Delete the created news item
#    Given the news item 'Тестова новина' exists
#    When I delete the news item
#    Then the news item should no longer be visible