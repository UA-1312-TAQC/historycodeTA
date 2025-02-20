Feature: Validate adding a partner with a non-existing street code

  Scenario: Adding a partner with an invalid street code
    Given Postman is opened
    And the "Post" method is chosen in Postman
    And the request URL is "https://backend.historycode.online/api/Partners/Create"
    And the request body is:
      """
      {
        "isKeyPartner": false,
        "isVisibleEverywhere": false,
        "title": "Diego Lopes",
        "description": "",
        "targetUrl": null,
        "logoId": 6882,
        "urlTitle": null,
        "partnerSourceLinks": [
          {
            "id": 0,
            "logoType": 1,
            "targetUrl": "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVuhttps"
          }
        ],
        "streetcodes": [
          {
            "id": 100,
            "title": "Дієго Лопес"
          },
          {
            "id": 200,
            "title": "Алекс Перейра"
          }
        ]
      }
      """
    When I click the "Send" button
    Then the response status should be 400 Bad Request
    And the response body should contain:
      """
      [
        {
          "message": "Обрані \"streetcodes\" не існує у системі"
        }
      ]
      """
