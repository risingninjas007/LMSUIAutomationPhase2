Feature: Home Page feature of the LMS website


Scenario: Verify that the title of the Home page is "LMS"
Given User landed on the Home page after logging into the LMS website
Then verify that title of the page is "LMS"

Scenario: Verifying the heading on the Home page 
Given User landed on the Home page after logging into the LMS website
Then User should see a heading with text "LMS - Learning Management System" on the Home page

Scenario: Verifying the Program button visibility
Given User landed on the Home page after logging into the LMS website
Then User should see a button with text "Program" on the menu bar

Scenario: Verifying the Batch button visibility
Given User landed on the Home page after logging into the LMS website
Then User should see a button with text "Batch" on the menu bar

Scenario: Verifying the User button visibility
Given User landed on the Home page after logging into the LMS website
Then User should see a button with text "User" on the menu bar

Scenario: Verifying the Assignment button visibility
Given User landed on the Home page after logging into the LMS website
Then User should see a button with text "Assignment" on the menu bar

Scenario: Verifying the Attendance button visibility
Given User landed on the Home page after logging into the LMS website
Then User should see a button with text "Attendance" on the menu bar

Scenario: Verifying the Logout button visibility
Given User landed on the Home page after logging into the LMS website
Then User should see a button with text "Logout" on the menu bar
