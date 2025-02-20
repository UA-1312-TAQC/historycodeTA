And the image with 'imageId' was previously created
And a news item has been created with the following data:
| title  | url        | text                                                                                                  | imageId | creationDate |
| Тестова новина | test-link | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et. | created image ID | Current date |
And the POST method is chosen
And all required data has been added to the body:
| field        | value                        |
| title        | Тестова новина              |
| text         | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et. |
| imageId      | created image ID            |
| url         | test-link                    |
| creationDate | Current date                |
When I input a valid endpoint "https://backend.historycode.online/api/News/GetByIdCreate"
And I click the 'Send' button
Then the news is not created
And the response status code should be 400
