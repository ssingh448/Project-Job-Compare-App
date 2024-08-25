# Project Plan 
 
**Author**: Team 166 
 
## 1 Introduction 
 
Team 166 is tasked into developing a mobile app to compare job offers. This entails comparing benefits, location, salary, and other related aspects. 
 
## 2 Process Description 

Here’s the list of activities throughout project development process: 

#### Enter/Edit current job details 

The user will first open the app and enter the current job details. This entails Title, Company, Location, Yearly Salary, Yearly Bonus, Leave Time, Stock Options, Home Buying Program Fund and Wellness Fund. The user saves the current job details to the database and exits the app, and when the user navigates back to the current job details again, he/she should see the job details he/she inserts earlier (data is persistent across the app) and edits the details again.

**Entrance criteria**: User clicks Current Job button on the main screen

**Exit criteria**: User clicks Save button after inserting all the fields and the current job is saved to database successfully, or user clicks Back button to exit current screen

#### Enter Job Offers

For this activity, the user will be able to enter the job offer details similar to current job details, which are Title, Company, Location, Yearly Salary, Yearly Bonus, Leave Time, Stock Options, Home Buying Program Fund and Wellness Fund. The user will be able to enter another job offer only if the current job offer is saved to database successfully. The user will also be able to compare the current job offer with current job if current job has been saved to database before.

**Entrance criteria**: User clicks on the Job Offers button on the main screen, or user clicks on Add Another button after the current job offer is saved to database successfully

**Exit criteria**: User clicks the Back button to exit current screen

#### Adjust the Comparison Settings

This activity enables the user to adjust the comparison weight for the job offers, the weight parameters are yearly salary, yearly bonus, leave time, number of stock option shares offered, home buying program fund, and wellness fund.The comparison settings is saved to database, which means the next time user opens the app, the comparison settings will still be there.

**Entrance criteria**: User clicks Comparison Settings button on the main screen

**Exit criteria**: User clicks Save button after adjusting the weight parameters and the current comparison weight settings is saved to database successfully, or user clicks Back button to exit current screen

#### Compare Job Offers

This activity will display a list of job offers including the current job (if any) for the user to compare, ranked from the best to the worst. The user can only compare two jobs at a time. After the user selects two jobs and clicks Compare button, the user will navigate to another screen that shows a table with both the job details side-by-side. The user can click Compare Another button to do another comparison, or click Back to go back to main menu.

**Entrance Criteria**: User clicks Compare Jobs button on the main screen, selects two job offers from the job offer list, and then clicks Compare button to compare two jobs 

**Exit Criteria**: User clicks Back button to exit current screen

## 3 Team

**Team members' names**: Sulekha Singh, Chern Yee Chua, Chenxuan Li, Tsoloane Joseph Lehloba

Table showing the roles with short description:

| Role Name         	| Description                                                   	|
|-------------------	|---------------------------------------------------------------	|
| Team Leader       	| Manages the team and oversees the development of the project.  	|
| App GUI Designer  	| Design App GUI for all activities of the app.                 	|
| SQL DB Designer   	| Design and develop database structure for the app.            	|
| Android Developer 	| Develop the application based on the activity specifications. 	|
| App Tester        	| Implement and perform unit testing for the app.               	|


Table showing team members with the roles: 

| Role Name         	| Team Member                	|
|-------------------	|----------------------------	|
| Team Leader       	| Chern Yee Chua             	|
| App GUI Designer  	| Sulekha Singh              	|
| SQL DB Designer   	| Tsoloane Lehloba           	|
| Android Developer 	| Chenxuan Li, Sulekha Singh 	|
| App Tester        	| All                        	|