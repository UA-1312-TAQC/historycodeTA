Feature: Edit an existing news article

  Scenario: Admin successfully edits an existing news article
    Given the user is logged in as an admin
    And the 'Новини' tab is opened
    And there are existing news items in the news table
    When I click on the "pencil" icon of any existing news
    Then I should be able to edit the news article

    And I change the title field to "New Title"
    And I change the Link field to "new-link"
    
    And I add a new sentence to the text field
    And I change the text style to "Heading 1"
    And I make the text bold using the "Bold" icon
    And I italicize the text using the "Italic" icon
    And I make the text strikethrough using the “Strikethrough” icon
    And I underline the text using the “Underline” icon
    And I add a hyperlink to the text
    And I create a numbered list
    And I create a bulleted list
    And I clear the text format using the "Clear" icon
    And I delete part of the text
    
    And I change the image to a new one
    And I change the date field to the new date
    
    When I click on the "Зберегти" button
    Then the news article should be updated with the new changes
    And I should see a confirmation message indicating that the news article was successfully updated
