Feature: Manage Program feature on the Home Page of the LMS website

Background: Navigate to LMS Portal Page
Given User logins in with valid username and password

Scenario: Validate the heading
Given User is logged on to LMS website
When User is on Program page
Then User should see program page heading with text "Manage Program" on the page

Scenario: Validate the text below the table
Given User is logged on to LMS website
When User is on Program page
Then User should see the text as "Showing 1 to 5 of " total entries below the table. 

Scenario: Validate the footer
Given User is logged on to LMS website
When User is on Program page
Then User should see the footer as "In total there are " programs

Scenario: Validating the default state of Delete button 
Given User is logged on to LMS website
When User is on Program page
Then User should see the Delete button on the top left hand side as Disabled 

Scenario: Validate that number of records (rows of data in the table) displayed 
Given User is logged on to LMS website
When User is on Program page
Then Verify that number of records (rows of data in the table) displayed on the page are 5

Scenario: Validate Search Feature 
Given User is logged on to LMS website
When User is on Program page
Then Text box used for search has text as "Search..."

Scenario Outline: Search Program By Name 
Given User is on Program page
When User enters  "<program_name>" into search box.
Then Entries for "<program_name>" are shown.
Examples:
|program_name|
|java|

Scenario Outline: Search Program By Name 
Given User is on Program page
When User enters  "<program_description>" into search box.
Then Entries for "<program_description>" are shown.
Examples:
|program_description|
|java|

@defect 
Scenario Outline: Search Program By Status
Given User is on Program page
When User enters  "<status>" into search box.
Then Entries for status "<status>" are shown.
Examples:
|status|
|inactive|
|active|


Scenario Outline: Select Program
Given User is on Program page
When User selects "<program_name>" Program using checkbox
Then "<program_name>" Program gets selected
Examples:
|program_name|
|TestsProgram|

Scenario: Edit Feature
Given User is on Program page
When User clicks on Edit button
Then User lands on Program Details form.

Scenario: Edit Program Name
Given User is on Program Details form
When User edits Name to "TestsProgram" and selects the Save button
Then User can see updated Name as "TestsProgram"

Scenario: Edit Program Description
Given User is on Program Details form
When User edits Description to "Test decription" and selects the Save button
Then User can see updated Description as "Test decription"

Scenario Outline: Change Program Status
Given User is on Program Details form for "<program_name>"
When User changes status to "<status>" and selects the Save button
Then User can see updated Status for program "<program_name>" should be "<status>"
Examples:
|program_name|status|
|TestsProgram|active|
|TestsProgram|inactive|

Scenario: Click Cancel
Given User is on Program Details form
When User clicks Cancel button
Then User can see Program Details form disappears

Scenario: Click Save
Given User is on Program Details form
When User clicks Save button
Then User can see 'Successful Program Updated' message

Scenario: Delete Feature
Given User is on Program page
When User clicks on Delete button
Then User lands on Confirm Deletion form.

Scenario: Click Yes
Given User is on Confirm Deletion form
When User clicks 'Yes' button
Then User can see 'Successful Program Deleted' message

Scenario: Click No
Given User is on Confirm Deletion form
When User clicks 'No' button
Then User can see Confirm Deletion form disappears

Scenario: Validate Add New Program
Given User is on Program page
When User clicks A New Program button
Then User lands on Program Details form.

Scenario: Empty form submission
Given User is on New Program Details form
When User clicks Save button without entering data
Then User gets message 'Name is required.'

Scenario Outline: Enter Program Name
Given User is on New Program Details form
When User enters '<program_name>' in program name text box
Then User can see '<program_name>' entered in program name text box
Examples:
|program_name|
|TestName|


Scenario Outline: Enter Program Description
Given User is on Program Details form
When User enters '<program_desc>' in program description text box
Then User can see '<program_desc>' entered in program description text box
Examples:
|program_desc|
|TestDescription|


Scenario Outline: Select Status
Given User is on Program Details form
When User selects "<status>" using radiobutton
Then User can see "<status>" status selected
Examples:
|status|
|Active|
|Inactive|


Scenario Outline: Enter program details and click Save
Given User is on New Program Details form
When User enters "<program_name>" in program name text box
And User enters "<program_description>" in program description text box
And User selects "<status>" using radiobutton
Then User clicks Save button
And User can see 'Successful Program Created' message
Examples:
|program_name|program_description|status|
|TestProgram1|TestProgramDesc1|Active|
|TestProgram2|TestProgramDesc2|Inactive|


Scenario Outline: Enter program details and click Cancel
Given User is on New Program Details form
When User enters "<program_name>" in program name text box
And User enters "<program_description>" in program description text box
And User selects "<status>" using radiobutton
When User clicks Cancel button
Then User can see Program Details form disappears
Examples:
|program_name|program_description|status|
|TestProgram1|TestProgramDesc1|Active|
|TestProgram2|TestProgramDesc2|Inactive|


Scenario: Select multiple Program
Given User is on Program page
When User selects more than one Program using checkbox
|TestProgram1|
|TestProgram2|
Then Programs get selected
|TestProgram1|
|TestProgram2|


Scenario Outline: Delete Feature - Click Yes
Given User is on Program page
When User selects a "<program_name>" using checkbox
And User clicks on Delete button on top left corner
Then User lands on Confirm Deletion form.
And User clicks "Yes" button
And User can see 'Successful Programs Deleted' message
Examples:
|program_name|
|TestProgram1|


Scenario Outline: Delete Feature - Click No
Given User is on Program page
When User selects a "<program_name>" using checkbox
And User clicks on Delete button on top left corner
Then User lands on Confirm Deletion form.
And User clicks "No" button
And User can see Confirm Deletion form disappears
Examples:
|program_name|
|TestProgram1|


Scenario: Verify previous link on the first page
Given User is logged on to LMS website
When User is on Program page 
Then Verify that previous link is disabled

Scenario Outline: Verify prev/next link
Given User is on the page number '<prev>' 
When User clicks navigate '<navigation>' button
Then User navigated to page number '<next>'
Examples:
|prev|next|navigation|
|1|2|>|
|3|2|<|


Scenario: Verify next link on the last page
Given User is logged on to LMS website
When User is on last page of Manage Program 
Then Verify that next link is disabled


Scenario: Verify that the results are displayed in Ascending order of Program name
Given User is on Program page
When User clicks onthe Ascending arrow (down ) near to the Program name 
Then User can see the results in Ascending order of Program name


Scenario: Verify that the results are displayed in Ascending order of Program Description
Given User is on Program page
When User clicks onthe Ascending arrow (down ) near to the Program Description 
Then User can see the results in Ascending order of Program Description


Scenario: Verify that the results are displayed in Ascending order of Program Status
Given User is on Program page
When User clicks onthe Ascending arrow (down ) near to the Program Status 
Then User can see the results in Ascending order of Program Status


Scenario: Verify that the results are displayed in Descending order of Program name
Given User is on Program page
When User clicks onthe Descending arrow (down ) near to the Program name
Then User can see the results in Descending order of Program name


Scenario: Verify that the results are displayed in Descending order of Program Description
Given User is on Program page
When User clicks onthe Descending arrow (down ) near to the Program Description
Then User can see the results in Descending order of Program Description


Scenario: Verify that the results are displayed in Descending order of Program Status
Given User is on Program page
When User clicks onthe Descending arrow (down ) near to the Program Status
Then User can see the results in Descending order of Program Status
