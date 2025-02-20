Feature: Validate updating a partner with an excessively long target URL

  Scenario: Updating a partner with a targetUrl exceeding 255 characters
    Given Postman is opened
    And the "PUT" method is chosen in Postman
    And the request URL is "https://backend.historycode.online/api/Partners/Update"
    And the request body is:
      """
      {
        "isKeyPartner": false,
        "isVisibleEverywhere": false,
        "title": "Reece James",
        "description": "",
        "targetUrl": null,
        "logoId": 6879,
        "urlTitle": null,
        "partnerSourceLinks": [
          {
            "id": 0,
            "logoType": 1,
            "targetUrl": "https://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVuhttps://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVuhttps://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGFtYzVuhttps://www.instagram.com/reel/DD79EYxvGvo/?igsh=bjF0czV4ZGF1"
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
          "message": "Максимальна довжина поля 'Посилання на соціальну мережу' - 255"
        }
      ]
      """
