Feature: Validate partner creation with duplicate name

  Scenario: Creating a partner with an existing name
    Given Postman is opened
    And the "POST" method is chosen in Postman
    And the request URL is "https://backend.historycode.online/api/Partners/Create"
    And the request body is:
      """
      {
        "isKeyPartner": false,
        "isVisibleEverywhere": false,
        "title": "Reece James",
        "description": "",
        "targetUrl": null,
        "logoId": 6878,
        "urlTitle": null,
        "partnerSourceLinks": [
          {
            "id": 0,
            "logoType": 1,
            "targetUrl": "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVu"
          }
        ],
        "streetcodes": []
      }
      """
    When I click the "Send" button
    Then the response status should be 400 Bad Request
    And the response body should contain:
      """
      [
        {
          "message": "Партнер з такою назвою вже існує"
        }
      ]
      """