Feature: As a librarian, I want to retrieve all users from get_all_users endpoint so that I can display them in my application.

  @wip
  Scenario: Retrieve all users from the API endpoint

    Given I logged Library api as a "librarian" as
    And Accept header is "application/json" as
    When I send GET request to “/get_all_users” endpoint as
    Then status code should be 200 as
    And Response Content type is "application/json; charset=utf-8" as
    And "id" field should not be null as
    And "name" field should not be null as