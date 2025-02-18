Feature: StreetCode Catalog Navigation

  Scenario: Verify navigation to the Catalog page via "Стріткоди" breadcrumb
    Given I am on the StreetCode page
    When I click on the "Стріткоди" breadcrumb in the top right corner
    Then I should be navigated to the Catalog page
    And I should see a list of streetcodes
