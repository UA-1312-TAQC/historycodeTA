Feature: Delete a context from the Streetcode page

  Scenario: Admin deletes a context
    Given I am logged in as 'Admin'
    And at least one context has been created
    When I go to the 'Eдітор' section on the left navigation panel
    And I click on 'Контексти'
    And I find the context with the name "Голодомор"
    And I click on the "trash bin" button next to the context
    And I click on the "OK" button on the pop-up confirmation window
    Then the context "Голодомор" should be deleted
    And I should not be able to find the context in the list
