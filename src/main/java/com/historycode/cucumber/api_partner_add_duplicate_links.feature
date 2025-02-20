Feature: Validate partner creation constraints

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

  Scenario: Creating a partner with duplicate social media links
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
        "logoId": 6877,
        "urlTitle": null,
        "partnerSourceLinks": [
          {
            "id": 0,
            "logoType": 1,
            "targetUrl": "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVu"
          },
          {
            "id": 1,
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
          "message": "Посилання на таку соціальну мережу вже додано"
        }
      ]
      """
