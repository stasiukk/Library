Feature: As a librarian, I want to retrieve all users from get_all_users endpoint so that I can display them in my application.

  @wip
  Scenario: Retrieve all users from the API endpoint

    Given I logged Library api as a "librarian" sn
    And Accept header is "application/json" sn
    When I send GET request to “/get_all_users” endpoint sn
    Then status code should be 200 sn
    And Response Content type is "application/json; charset=utf-8" sn
    And "id" field should not be null sn
    And "name" field should not be null sn