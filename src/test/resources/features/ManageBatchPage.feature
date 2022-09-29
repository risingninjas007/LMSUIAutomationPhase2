Feature: LMS Batch Page Feature

Background: Navigate to LMS Portal Page
Given User logins in with valid username and password

Scenario: Validate the heading
Given User is logged on to LMS website
When  User is on Batch page
Then  User should see a heading with text "Manage Batch" on the page

Scenario: Validating the default state of Delete button 
Given User is logged on to LMS website
When  User is on Batch page
Then  User should see the Delete button on the top left hand side as Disabled

Scenario: Validate that number of records (rows of data in the table) displayed 
Given User is logged on to LMS website
When  User is on Batch page
Then  Verify that number of records (rows of data in the table) displayed on the page are 5

Scenario Outline: Search By Batch Name 
Given User is on Batch page
When User enters  "<batch_name>" into search box.
Then Entries for "<batch_name>" are shown.
Examples:
|batch_name|
|SDET|

Scenario: Edit Feature
Given User is on Batch page
When  User clicks on Edit button for batch
Then  User lands on Batch Details form.

Scenario: Edit Batch Name
Given User is on Batch details page
When User edits batch Name to "SDEThackathon" and selects the Save button
Then User can see updated batch Name as "SDEThackathon"

Scenario: Edit  Batch Description
Given User is on Batch details page
When user edits batch description to "Batch Description" and selects the Save button
Then User can see edited batch descricption as "Batch Description"

Scenario: Edit program name
Given User is on Batch details page
When  user edits program name 
Then  user can see edited program name

Scenario: change status
Given User is on Batch details page
When  user changes status to "Inactive" and selects the the Save button
Then  User can see updated Status as "Inactive"

Scenario: Edit No of classes
Given User is on Batch details page
When  user edits No of classes to "80" and selects the the Save button
Then  user can see updated No of classes as "80"
And  User can see 'Successful batch Updated' alert

Scenario: Click Cancel
Given User is on Batch details page
When  user clicks cancel button
Then  User can see batch page

Scenario: Select multiple Batch
Given User is on Batch page
When User selects more than one Batch using checkbox
|SDET666|
|SDET951|
Then batches get selected
|SDET666|
|SDET951|


Scenario Outline: Delete Feature - Click Yes
Given User is on Batch page
When User selects a batch "<batch_name>" using checkbox
And User clicks on Delete button on top left corner of batch page
Then User lands on Confirm Deletion form.
And User clicks "Yes" button
And User can see 'Successful Batches Deleted' alert
Examples:
|batch_name|
|ffsdf|


Scenario Outline: Delete Feature - Click No
Given User is on Batch page
When User selects a batch "<batch_name>" using checkbox
And User clicks on Delete button on top left corner of batch page
Then User lands on Confirm Deletion form.
And  User clicks "No" button
And  User searches "<batch_name>" and can see Batch is not deleted.
Examples:
|batch_name|
|ffsdf|

Scenario: Validate Add New Batch
Given User is on Batch page
When User clicks A New Batch button
Then User lands on Batch Details form.

Scenario: Empty form submission
Given User is on New Batch Details form
When User clicks Save button without entering batch data
Then User gets message 'Name is required.' for Batch

Scenario Outline: Enter Batch Name
Given User is on New Batch Details form
When User enters '<batch_name>' in batch name text box
Then User can see '<batch_name>' entered in batch name text box
Examples:
|batch_name|
|SDET NEW|

Scenario Outline: Enter Batch Description
Given User is on New Batch Details form
When User enters '<batch_desc>' in batch description text box
Then User can see '<batch_desc>' entered in batch description text box
Examples:
|batch_desc|
|BatchDescription|

Scenario Outline: Select Status
Given User is on New Batch Details form
When User selects batch status -> "<status>" using radiobutton
Then User can see "<status>" batch status selected
Examples:
|status|
|Active|
|Inactive|

Scenario Outline: Select number of classes
Given User is on New Batch Details form
When User enters "<number_of_classes>" in number of classes text box
Then User can see "<number_of_classes>" entered in number of classes text box
Examples:
|number_of_classes|
|40|


Scenario Outline: Enter batch details and click Save
Given User is on New Batch Details form
When User enters '<batch_name>' in batch name text box
And User enters '<batch_desc>' in batch description text box
And User chooses '<program_name>' from dropdown
And User enters "<number_of_classes>" in number of classes text box
Then User clicks Save button for Batch
Examples:
|batch_name|batch_desc|program_name|number_of_classes|
|SDET NEW BATCH1|BatchDesc1|Name|60|


Scenario Outline: Click Cancel
Given User is on New Batch Details form
When User enters '<batch_name>' in batch name text box
And User enters '<batch_desc>' in batch description text box
And User chooses '<program_name>' from dropdown
And User enters "<number_of_classes>" in number of classes text box
When user clicks cancel button
Examples:
|batch_name|batch_desc|program_name|number_of_classes|
|SDET NEW BATCH1|BatchDesc1|Name|60|


Scenario: Verify previous link on the first page
Given User is logged on to LMS website
When User is on Batch page 
Then Verify that previous link is disabled

Scenario Outline: Verify next link
Given User is on the page number '<prev>' 
When User clicks navigate '<navigation>' button
Then User navigated to page number '<next>'
Examples:
|prev|next|navigation|
|1|2|>|
|3|2|<|

Scenario: Verify next link on the last page
Given User is logged on to LMS website
When User is on last page of Manage Batch 
Then Verify that next link is disabled

Scenario: Verify that the results are displayed in Ascending order of Batch name
Given User is on Batch page
When User clicks onthe Ascending arrow (down) near to the Batch name 
Then User can see the results in Ascending order of Batch name

Scenario: Verify that the results are displayed in Ascending order of Batch Description
Given User is on Batch page
When User clicks onthe Ascending arrow (down) near to the Batch Description 
Then User can see the results in Ascending order of Batch Description

Scenario: Verify that the results are displayed in Ascending order of Batch Status
Given User is on Batch page
When User clicks onthe Ascending arrow (down) near to the Batch Status 
Then User can see the results in Ascending order of Batch Status

Scenario: Verify that the results are displayed in Descending order of Batch name
Given User is on Batch page
When User clicks onthe Descending arrow (down) near to the Batch name
Then User can see the results in Descending order of Batch name

Scenario: Verify that the results are displayed in Descending order of Batch Description
Given User is on Batch page
When User clicks onthe Descending arrow (down) near to the Batch Description
Then User can see the results in Descending order of Batch Description

Scenario: Verify that the results are displayed in Descending order of Batch Status
Given User is on Batch page
When User clicks onthe Descending arrow (down) near to the Batch Status
Then User can see the results in Descending order of Batch Status




