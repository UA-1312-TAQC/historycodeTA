Feature: Verify that a news item cannot be created with an already existing title and link

  Background:
    Given The image with 'imageId' was previously created
    And A news item has been created in the system with the following data:
      | Title  | Тестова новина |
      | Link   | test-link |
      | Text   | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et. |
      | Image  | from previous point |
      | Date   | Current date |
    And The POST method is chosen

  Scenario: Verify that creating a news item with the same title and link fails
    Given The request body contains the following data:
      """
      {
        "title": "Тестова новина",
        "text": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et.",
        "imageId": "Id of created in point 2 image",
        "url": "test-link",
        "creationDate": "Current date"
      }
      """
    And The valid endpoint "https://backend.historycode.online/api/News/GetByIdCreate" is selected
    When The user clicks the 'Send' button
    Then The response status code should be 400
    And The news item should not be created
