Feature: Login page feature for the LMS website

Scenario: Validating the Login functionality with no credentials entered
Given User is on the Login Page
When User clicks the Login button 
Then User should receive the message "Invalid username and password Please try again"
And User should receive "Please enter your user name" message under username
And User should receive "Please enter your password" message under password

Scenario: Validating the Login functionality with invalid username field
Given User is on the Login Page
When User clicks the Login button after entering invalid username
Then User should receive the message "Invalid username and password Please try again"

Scenario: Validating the Login functionality with invalid password field
Given User is on the Login Page
When User clicks the Login button after entering invalid password
Then User should receive the message "Invalid username and password Please try again"
And User should receive "Please enter your user name" message under username

Scenario: Validating the Login functionality with valid username and password
Given User is on the Login Page
When User clicks the Login button after entering valid username and password
Then User should see the LMS Home page.
