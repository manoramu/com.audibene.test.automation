Feature: Characteristics testing  

Scenario: Verify Success Login for herokuapp
Given User hits the URL: https://the-internet.herokuapp.com/login
When User enters the Username tomsmith and Password SuperSecretPassword! to login
And User clicks Login
Then User is successfully logged into the application and could see the success message You logged into a secure area!

Scenario: Verify Failure Login for herokuapp
Given User hits the URL: https://the-internet.herokuapp.com/login
When User enters the Username tomsmith1 and Password SuperSecretPassword! to login
And User clicks Login
Then User is not logged into the application and could see the failure message Your username is invalid!

Scenario: Verify exit intent is shown
Given User hits the URL: https://the-internet.herokuapp.com/exit_intent
When Mouse hover outside the viewport
Then Verify exit intent alert is shown

Scenario: Verify sorting of lastname column
Given User hits the URL: https://the-internet.herokuapp.com/tables
When Clicking on the column lastname 
Then Verify lastname column is sorted ascending

Scenario: Verify sorting of firstname column
Given User hits the URL: https://the-internet.herokuapp.com/tables
When Clicking on the column firstname 
Then Verify firstname column is sorted descending

Scenario: Verify click here opens a new tab
Given User hits the URL: https://the-internet.herokuapp.com/windows
When User clicks the button click here
Then Verify click here opens a new tab with URL: https://the-internet.herokuapp.com/windows/new

Scenario: Verify dynamic controls functionality
Given User hits the URL: https://the-internet.herokuapp.com/dynamic_controls
When User clicks enable button
Then Verify text box is enabled to provide input enabled
When User clicks disable button
Then Verify text box is disable and still it has the input enabled