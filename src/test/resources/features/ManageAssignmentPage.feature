Feature: Manage Assignment feature on the Home Page of the LMS website

Background: Navigate to LMS Portal Page
Given User logins in with valid username and password

Scenario: Validate the heading
Given User is logged on to LMS website
When User is on Manage Assignment page
Then User should see assignment page heading with text "Manage Assignment" on the page

Scenario: Validate the text below the table
Given User is logged on to LMS website
When User is on Manage Assignment page
Then User should see the text as "Showing 1 to 5 of " total entries below the table. 

Scenario: Validate the footer
Given User is logged on to LMS website
When User is on Manage Assignment page
Then User should see the footer as "In total there are " assignments

Scenario: Validate Search Feature
Given User is logged on to LMS website
When User is on Manage Assignment page
Then Text box used for search has text as 'Search...'

Scenario Outline: Search Assignment By Name
Given User is on Manage Assignment page
When User enters  "<assignment_name>" into search box.
Then Entries for "<assignment_name>" are shown.
Examples:
|assignment_name|
|Selenium Assignment|

Scenario Outline: Select Assignment 
Given User is on Manage Assignment page
When User selects assignment -> "<assignment_name>" using checkbox
Then Assignment "<assignment_name>" gets selected
Examples:
|assignment_name|
|Selenium Assignment|

@defect
Scenario: Edit Feature
Given User is on Manage Assignment page
When User clicks on Edit button for assignment
Then User lands on Assignment Details form.

Scenario: Edit Assignment Name
Given User is on Assignment Details form 
When User edits assignment Name as "Test Assignment" and saves
Then User can see updated assignment Name as "Test Assignment"

Scenario: Edit Assignment Description
Given User is on Assignment Details form
When User edits assignment Description as "Test Assignment Desc" and saves
Then User can see updated assignment Description as "Test Assignment Desc"

Scenario: Edit Assignment Grade
Given User is on Assignment Details form
When User edits Grade as "5" and saves
Then User can see update sucessfull message "Assignment Updated"

@defect
Scenario: Edit Assignment Due Date
Given User is on Assignment Details form
When User edits Assignment Due Date to "9/9/2022"
Then User can see updated Due Date as "9/9/2022"

@defect
Scenario: Click Cancel
Given User is on Assignment Details form
When User clicks Cancel button on assignment details page
Then User can see Assignment Details form disappears


Scenario Outline: Click Save
Given User is on Assignment Details form
When User enters "<assignment_name>" in assignment name text box
And User enters "<assignment_description>" in assignment description text box
Then User clicks Save button on assignment details page
And User can see 'Successful Assignment Updated' message
Examples:
|assignment_name|assignment_description|
|TestAssignment1|TestAssignmentDesc1|
|TestAssignment2|TestAssignmentDesc2|

@defect
Scenario Outline: Delete Feature - Click Yes
Given User is on Manage Assignment page
When User clicks on Delete button after selecting "<assignment_name>"
Then User lands on Confirm Deletion form.
And User clicks "Yes" button
And User can see 'Successful Assignment Deleted' message
Examples:
|assignment_name|
|Dev Assignment|


Scenario Outline: Delete Feature - Click No
Given User is on Manage Assignment page
When User clicks on Delete button after selecting "<assignment_name>"
Then User lands on Confirm Deletion form.
And User clicks "No" button
Then User can see Confirm Deletion form disappears and user is on assignments page
Examples:
|assignment_name|
|Dev Assignment|

Scenario: Select multiple Assignment 
Given User is on Manage Assignment page
When User selects more than one Assignment using checkbox
|Dev Assignment|
|Selenium Assignment|
Then Assignments get selected
|Dev Assignment|
|Selenium Assignment|

@defect
Scenario Outline: Delete Feature - Click Yes
Given User is on Manage Assignment page
When User clicks on Delete button on top left corner after selecting "<assignment_name>"
Then User lands on Confirm Deletion form.
And User clicks "Yes" button
And User can see 'Successful Assignment Deleted' message
Examples:
|assignment_name|
|Dev Assignment|

@defect
Scenario Outline: Delete Feature - Click No
Given User is on Manage Assignment page
When User clicks on Delete button on top left corner after selecting "<assignment_name>"
Then User lands on Confirm Deletion form.
And User clicks "No" button
Then User can see Confirm Deletion form disappears and user is on assignments page
Examples:
|assignment_name|
|Dev Assignment|

@defect
Scenario: validate add new assignment
Given User is on Manage Assignment page
When User clicks on New Assignment button
Then user lands on New Assignment details form

Scenario: Empty form submission
Given user lands on New Assignment details form
When User clicks Save button without entering assignment data
Then User gets message 'Name is required.' on New Assignment details form

Scenario Outline: Enter Assignment Name
Given user lands on New Assignment details form
When user enters Assignment name '<assignment_name>'
Then User can see '<assignment_name>' entered in assignment name field
Examples:
|assignment_name|
|Test AssignmentName|


Scenario Outline: Enter Assignment Description
Given user lands on New Assignment details form
When user enters Assignment Description '<assignment_description>'
Then User can see '<assignment_description>' entered in assignment description field
Examples:
|assignment_description|
|Test AssignmentDescription|

Scenario Outline: Enter Assignment Grade
Given user lands on New Assignment details form
When user enters Assignment Grade '<assignment_grade>'
Then User can see '<assignment_grade>' entered in assignment grade field
Examples:
|assignment_grade|
|2|

@defect
Scenario Outline: Enter assignment due date
Given user lands on New Assignment details form
When user enters assignment due date '<assignment_date>'
Then user can see "<assignment_date>" entered in assignment due date field
Examples:
|assignment_date|
|9/9/2022|

Scenario Outline: Enter New Assignment data and Click Save
Given user lands on New Assignment details form
When user enters Assignment name '<assignment_name>'
And user enters Assignment Description '<assignment_description>'
And user enters Assignment Grade '<assignment_grade>'
And user enters assignment due date '<assignment_date>'
Then User clicks Save button on assignment details page
And User can see 'Successful Assignment Created' message
Examples:
|assignment_name|assignment_description|assignment_date|assignment_grade|
|Test Assignment Name|Test Assignment Description|9/9/2022|5|

@defect
Scenario Outline: Enter New Assignment data and Click Cancel
Given user lands on New Assignment details form
When user enters Assignment name '<assignment_name>'
And user enters Assignment Description '<assignment_description>'
And user enters Assignment Grade '<assignment_grade>'
And user enters assignment due date '<assignment_date>'
Then User clicks Cancel button on assignment details page
And User can see Assignment Details form disappears
Examples:
|assignment_name|assignment_description|assignment_date|assignment_grade|
|Test Assignment Name|Test Assignment Description|9/9/2022|5|

Scenario: Verify that the results are displayed in Ascending order of Assignment name
Given User is on Manage Assignment page
When User clicks onthe Ascending arrow (down ) near to the Assignment name 
Then User can see the results in Ascending order of Assignment name

Scenario: Verify that the results are displayed in Ascending order of Assignment Description
Given User is on Manage Assignment page
When User clicks onthe Ascending arrow (down ) near to the Assignment Description 
Then User can see the results in Ascending order of Assignment Description

Scenario: Verify that the results are displayed in Descending order of Assignment name
Given User is on Manage Assignment page
When User clicks onthe Descending arrow (down ) near to the Assignment name
Then User can see the results in Descending order of Assignment name

Scenario: Verify that the results are displayed in Descending order of Assignment Description
Given User is on Manage Assignment page
When User clicks onthe Descending arrow (down ) near to the Assignment Description
Then User can see the results in Descending order of Assignment Description
