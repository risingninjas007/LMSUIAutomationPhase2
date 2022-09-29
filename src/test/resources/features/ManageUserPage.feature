Feature: New/Existing User features on the Home Page of the LMS website

Background: Navigate to LMS Portal Page
Given User logins in with valid username and password


Scenario: Verify landing in Manage user page
Given  User is logged on to LMS website
When User clicks the Tab "User"
Then User is on the Manage user page 

Scenario: Verify the Manage user page heading
Given User is logged on to LMS website
When User clicks the Tab "User"
Then User should see the page heading as "Manage User"

Scenario: Verify the presence of pagination
Given User is logged on to LMS website
When User is on the Manage user page
Then Verify that previous link is disabled

Scenario: Verify the Pagination control with less rows
Given User is logged on to LMS website
When User is on the Manage user page
Then Verify that next link is disabled if Manage user table has less less than or equal to 5 rows

Scenario: Verify the Pagination control with more than 5 rows
Given User is logged on to LMS website
When User is on the Manage user page
Then Verify that next link is enabled if Manage user table has more than to 5 rows

Scenario Outline: Verify prev/next link
Given User is on the page number '<prev>' of Manage User Page
When User clicks navigate '<navigation>' button
Then User navigated to page number '<next>'
Examples:
|prev|next|navigation|
|1|2|>|
|3|2|<|

Scenario: Verify next link on the last page
Given User is logged on to LMS website
When User is on last page of Manage User Page 
Then Verify that next link is disabled

Scenario: Verify previous link on the first page of Manage User page
Given User is logged on to LMS website
When User is on the Manage user page 
Then Verify that previous link is disabled

Scenario: Verifiy the entry details below data table
Given User is logged on to LMS website
When User is on the Manage user page
Then User should see the text "Showing 1 to 4 of 4 entries" below the user table. "

Scenario: Verify table footer
Given User is logged on to LMS website
When User is on the Manage user page
Then User should see the table footer as "In total there are 4 users."

Scenario: Verify the presence of Add new user button
Given User is logged on to LMS website
When User is on the Manage user page
Then User should see the button with text "Add New User"

Scenario: Verify the functionality of Add new user button
Given  User is on the Manage user page
When User clicks Add New User button
Then User should see the "User Details" dialog box

Scenario: Verify Table header
Given  User is logged on to LMS website
When User is on the Manage user page
Then User should see the user table header with options listed
|ID|
|Name|
|Email Address|
|Contact Number|
|Batch|
|Skill|

Scenario Outline: Verify the functionality of sort icon present in table header - ascending
Given User is on the Manage user page
When User clicks sort icon in table header '<header>' to sort in Ascending order
Then User can see the results in Ascending order of '<header>'
Examples:
|header|
|ID|
|Name|
|Email Address|
|Contact Number|
|Batch|
|Skill|


Scenario Outline: Verify the functionality of sort icon present in table header - descending
Given User is on the Manage user page
When User clicks sort icon in table header '<header>' to sort in Descending order
Then User can see the results in Descending order of '<header>'
Examples:
|header|
|ID|
|Name|
|Email Address|
|Contact Number|
|Batch|
|Skill|

Scenario: Check box in table header
Given User is on the Manage user page
When User checks empty checkbox in user table header
Then Check box in all the rows of user table should be checked

Scenario: Behaviour of Delete icon when no rows unchecked 
Given User is logged on to LMS website
When User is on the Manage user page
Then User should see the Delete button on the top left hand side as Disabled 

Scenario Outline: Behaviour of Delete icon when rows checked
Given User is on the Manage user page
When User checks '<user_name>' in user table
Then Delete icon at the top left corner of the user table enabled
Examples:
|user_name|
|John Dove|


Scenario Outline: Behaviour of Delete icon when rows checked
Given User is on the Manage user page
When User checks '<user_name>' in user table
And Delete icon at the top left corner of the user table enabled
Then User clicks the delete icon at the top left corner of user table
And User lands on Confirm Deletion form.
Examples:
|user_name|
|John Dove|

Scenario Outline: Delete Feature - Click No -> ValidatingConfirm dialog box by selecting No
Given User is on the Manage user page
When User checks '<user_name>' in user table
And Delete icon at the top left corner of the user table enabled
Then User clicks the delete icon at the top left corner of user table
And User lands on Confirm Deletion form.
And User clicks "No" button
And User can see Confirm Deletion form disappears and user is back on manage user page
Examples:
|user_name|
|John Dove|

Scenario Outline: Delete Feature - Click Yes -> ValidatingConfirm dialog box by selecting Yes
Given User is on the Manage user page
When User checks '<user_name>' in user table
And Delete icon at the top left corner of the user table enabled
Then User clicks the delete icon at the top left corner of user table
And User lands on Confirm Deletion form.
And User clicks "Yes" button
And User can see 'Successful Users Deleted' message
Examples:
|user_name|
|John Dove|


Scenario Outline: Delete Feature - ValidatingConfirm dialog box by selecting X
Given User is on the Manage user page
When User checks '<user_name>' in user table
And Delete icon at the top left corner of the user table enabled
Then User clicks the delete icon at the top left corner of user table
And User lands on Confirm Deletion form.
And User clicks close(x) button
And User can see Confirm Deletion form disappears and user is back on manage user page
Examples:
|user_name|
|John Dove|

Scenario: Delete Feature - Fucntionality of delete button
Given User is on the Manage user page
When User clicks on Delete button for user
Then User lands on Confirm Deletion form.

Scenario: Edit Feature
Given User is on the Manage user page
When User clicks on Edit button for user
Then User should see the "User Details" dialog box

Scenario: Verify the presence of Input field for searching
Given User is logged on to LMS website
When User is on the Manage user page
Then Text box used for search has text as "Search..."

Scenario Outline: Search User  
Given User is on the Manage user page
When User enters  "<user_name>" into search box.
Then Entries for "<user_name>" are shown.
Examples:
|user_name|
|John|
|Johny|


Scenario: Verify ID functionality
Given User is on the Manage user page
When User clicks ID in any row
Then User details dialog box displayed with user information

Scenario: Verify the heading of User details window
Given User is on the Manage user page
When User clicks Add New User button
Then User should see the "User Details" dialog box

Scenario: Verify the presence of Cancel button in user detais window
Given User is on the Manage user page
When User clicks Add New User button
Then User should see a button with text Cancel in user details window

Scenario: Fucntionality of Cancel button
Given User is on User details window
When User clicks A cancel button
Then User details window should be closed

Scenario: Verify the presence of Cancel(x) icon in user detais window
Given User is on the Manage user page
When User clicks Add New User button
Then User should see a cancel(x) in user details window

Scenario: Fucntionality of Cancel(x) icon
Given User is on User details window
When User clicks close(x) button
Then User details window should be closed


Scenario: Verify the presence of Submit button in user detais window
Given User is on the Manage user page
When User clicks Add New User button
Then User should see a button with text Submit in user details window


Scenario: Verify the presence of Label Texts
Given User is on the Manage user page
When User clicks Add New User button
Then User should see the placeholders for user data
|First name|
|Middle name|
|Last name|
|Email address|
|Phone no|
|Address|
|City|
|State|
|Postal Code|
|Program|
|UG Program|
|PG Program|
|Skill|
|Experience|
|User Role|
|Visa status|
|Batch|
|Comments|
|User name|
|Password|
|File type|

@defect
Scenario: verify drop down menu
Given User is on User details window
When User clicks the drop down icon for state
Then User should be able to select state from the drop down menu

@defect
Scenario: verify drop down menu
Given User is on User details window
When User clicks the drop down icon for User Role
Then User should be able to select user role from the drop down menu

@defect
Scenario: Validating the User details window with all fields empty
Given User is on User details window
When User clicks save button
Then User should see a message "Mandatory Fields cannot be empty"

Scenario: Adding new User
Given User is on User details window
When User enters mandatory details
|First Name|Last Name|Email|Contact Number|Batch|Skill|
|Test|User|testuser@numpy.com|123456789|SDET|Selenium|
And User clicks Submit button 
Then New User Should be Saved. 
|First Name|Last Name|Email|Contact Number|Batch|Skill|
|Test|User|testuser@numpy.com|123456789|SDET|Selenium|

Scenario: Validating the presence of Address2 button
Given User is on the Manage user page
When User clicks Add New User button
Then User should see the add button with text "+ Add C/O, Apt, Suite, Unit"

Scenario: Validating Address2 in User details window
Given User is on User details window
When User clicks the button Add C/O, Apt, Suite, Unit
Then User should see the input field with Label "Address 2"

Scenario: Validating the presence of input number arrows in postal code input field
Given User is on User details window
When User clicks postal code input field
Then User should see the input Number arrows in the postal code input field

