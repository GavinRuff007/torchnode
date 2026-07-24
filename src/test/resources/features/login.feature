Feature: User Login
  As a system user
  I want to login to my account
  So that I can access different parts of the system

  Background:
    Given the user is on the login page

  Scenario: Verify login page loads successfully
    Then the page title should be "صفحه ورود به سیستم"
    And the "username" and "password" fields should be visible
    And the "ورود به سیستم" button should be enabled

  Scenario: Successful login with valid credentials
    When the user enters username "admin" and password "password123"
    And clicks the "ورود به سیستم" button
    Then the user should be redirected to the dashboard

  Scenario: Failed login due to missing required fields
    When the user clicks the "ورود به سیستم" button without entering credentials
    Then the system should display a validation error for required fields
