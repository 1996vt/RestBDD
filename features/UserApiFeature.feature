@UserApi
Feature: Validate different User API

  @RegisterUser
  Scenario Outline: Validate register user API response
    Given I initialize "post" request for "register user"
    When I select email "<Email>" and password "<Password>"
    And I execute the api request
    Then I should get statusCode as "<StatusCode>"
    And I should get valid token for "<Email>"
    Examples: 
      | Email                  | Password  | StatusCode |
      | tracey.ramos@reqres.in | password1 |        200 |
      | eve.holt@reqres.in     | password2 |        200 |
      | test3@gmail.com        |           |        400 |

  @LoginUser
  Scenario Outline: Validate login user API response
  Given I initialize "post" request for "login user"
    When I select email "<Email>" and password "<Password>"
    And I execute the api request
    Then I should get statusCode as "<StatusCode>"
    And I validate the token for "<Email>"
    Examples: 
      | Email                  | Password  | StatusCode |
      | tracey.ramos@reqres.in | password1 |        200 |
      | eve.holt@reqres.in     | password2 |        200 |
      | test3@gmail.com        |           |        400 |

  @SingleUser
  Scenario Outline: Validate single user API response
    Given I initialize "get" request for "single user"
    When I select userId "<UserId>"
    And I execute the api request
    Then I should get statusCode as "<StatusCode>"
    And I should get first name "<fName>" , last name "<lName>" and Id "<UserId>"

    Examples: 
      | UserId| StatusCode |	fName  	| 	lName 	|
      | 2 		| 		200		 |	Janet		|		Weaver	|
      | 5     | 		200		 |	Charles |   Morris  |
      | 23    | 		404		 |          |         	|