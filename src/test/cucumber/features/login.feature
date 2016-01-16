# Created by ckovacs at 1/16/16
Feature: Login
  Log in to the application
  Scenario: Log in using wing email as a user
    Given I am viewing the login page
    When I enter username "test.user@ohwg.cap.gov"
    And I enter password "THrQ;3E8f#4vcKF"
    And I click login
    Then I am viewing the home page

  Scenario: I see my group
    Given I am logged in as a "Group 1 es officer"
    When I am viewing the home page
    Then I see the units in "Group 1"

