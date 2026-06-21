@orangehrm

Feature: OrangeHRM Login Functionality

  @ValidLogin
  Scenario: Login with valid credentials

    Given User launches OrangeHRM application
    When User enters username "Admin"
    And User enters password "admin123"
    And User clicks Login button
    Then User should navigate to Dashboard page

  @InvalidLogin
  Scenario: Login with invalid credentials

    Given User launches OrangeHRM application
    When User enters username "Admin"
    And User enters password "Invalid123"
    And User clicks Login button
    Then Error message should be displayed