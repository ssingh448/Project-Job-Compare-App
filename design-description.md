# **Design Description**

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  

	**App class is the main entrance of the whole program. When the user launches the app, a main menu with 4 different options stated above will be presented for the user to select from. After the user choose the  selection, the user will be presented with its associated screen page.**

2. When choosing to enter current job details, a user will:
	* a. Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:
		* i. Title
		* ii. Company
		* iii. Location (entered as city and state)
		* iv. Yearly salary adjusted for cost of living
		* v. Yearly bonus adjusted for cost of living
		* vi. Leave time (in days)
		* vii. Number of stock option shares offered
		* viii. Home Buying Program fund (one-time dollar amount up to 15% of Yearly Salary)
		* ix. Wellness Fund ($0 to $5,000 inclusive annually)
	* b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu. 
	
	**When user select the first (1) option on main menu, the user will be brought to a screen page that contains job related input fields listed above. These fields will be auto-populated if the current job information has been inserted and saved by the user before. The user can edit the current job details and save it by calling editJobDetail() method. If the current job is not created before, the user will need to fill in all the input fields (no fields can be left blank) and click the save button at the bottom of the screen. The enterCurrentJob() method will be called to save the job details, or the user can click cancel button to return to the main menu. Some input validation rules are applied on those input fields, ex: salary field must be a positive integer, wellness fund is ranged from $0 to $5000, etc.**
	
3. When choosing to enter job offers, a user will:
	* a. Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
	* b. Be able to either save the job offer details or cancel.
	* c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
	
	**When the user select the second (2) option on main menu, the user will be brought to a screen page that contains job related fields, similar to entering current job details. Likewise, the user can click save button to save the job offer details, or click cancel to return to the main menu. After the user clicks save button, the enterJobOffers() method will be called and the current job offer will be saved. The save and cancel button will disappear and 3 more buttons will appear at the bottom of the screen page, which are the (1) enter another offer, (2) return to main menu, and (3) compare the offer (if they saved it) with current job details (if present). If selecting enter another offer, the user will be brought to the same screen page and the user can repeat the process of entering new job offer and save the job offer, or click cancel button to go back to main menu. If selecting compare the offer, the user will be brought to the job offer compare screen page, once the other job offer is selected, the compareTwoJobOffers(Job, Job) method will be called and the user will be able to compare these two jobs side by side (see (5) for more information). If selecting return to main menu, then the user will be brought back to main menu.**
	
4. When adjusting the comparison settings, the user can assign integer weights to:
	* a. Yearly salary
	* b. Yearly bonus
	* c. Leave time
	* d. Number of shares offered
	* e. Home Buying Program Fund
	* f. Wellness Fund
	If no weights are assigned, all factors are considered equal.
	
	**When the user select the third (3) option on main menu, the user will be presented with a screen page that contains 6 adjustable weight input fields. All of the weight input fields are required to fill in and the minimum value for each weight field is 1 (the default is also 1). Simple input validation rules are set on each field to make sure that only positive integer that is greater or equal to 1 is accepted. The user can then click the save button, the adjustComparisonSettings() method will be called to save current comparison settings, or the user can click cancel to return to the main menu without saving.**
	
5. When choosing to compare job offers, a user will:
	* a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
	* b. Select two jobs to compare and trigger the comparison.
	* c. Be shown a table comparing the two jobs, displaying, for each job:
		* i. Title
		* ii. Company
		* iii. Location 
		* iv. Yearly salary adjusted for cost of living
		* v. Yearly bonus adjusted for cost of living
		* vi. Leave time
		* vii. Number of shares offered
		* viii. Home Buying Program fund (one-time up to 15% of Yearly Salary)
		* ix. Wellness Fund fund ($0 to $5,000 inclusive annually)
	* d. Be offered to perform another comparison or go back to the main menu.
	
	**When the user select the fourth (4) option on main menu, the user will be brought to a screen page that contains a list of job offers, displayed as Title and Company order by job ranking (see (6) for more information). If the currentJob is present, it will also appear in the job list. There will be a checked icon displayed next to the job title to indicate it is the current job offer. Each of the job offer item will have a checkbox displayed next to the job title, and the user can select up to two job offers to compare. After the user has selected two job offers, the compareTwoJobOffers(Job, Job) method will be called, a table will appear under the job offer list and display the details of these two jobs side by side. If the user has selected more than 2 job offers, then the previously selected job offer will be automatically unchecked. At any point that the user selects two job offers, the table will be automatically updated based on the job offers the user selected. A button to return to main menu will be presented at the bottom of the screen page as well. If the user clicks the compare the offer with current job details from (2), then the current job offer will be automatically selected for job comparison.**
	
6. When ranking jobs, a job’s score is computed as the weighted sum of:
	AYS + AYB + (LT \* AYS / 260) + (CSO/2) + HBP + WF
	
	where:
	AYS = yearly salary adjusted for cost of living
	AYB = yearly bonus adjusted for cost of living
	LT = leave time
	CSO = Company shares offered (assuming a 2-year vesting schedule and a price-per-share of $1)
	HBP = Home Buying Program 
	WF= Wellness Fund

	For example, if the weights are 2 for the yearly salary, 2 for the yearly bonus, 2 for wellness fund, and 1 for all other factors, the score would be computed as:

	2/9 \* AYS + 2/9 \* AYB + 1/9 \* (LT \* AYS / 260) + 1/9 \* (CSO/2) + 1/9 \* HBP + 2/9 \* WF
	
	**For each job offer including the current job if presented, the calculateJobScore(Job) from ComparisonSettings will be able to calculate a score for each job based on the formula provided above. Whenever the attributes in ComparisonSettings Class are changed, the jobScore of each job offer will be updated by calling the claculateJobScore(job) again. The showJobOffersByRank() will be called whenever the interface needs to display a list of job offers ordered by the job rank. The job offer list will be sorted by the score in descending order to indicate the rank in ascending order. The job offer with the highest jobScore will appear at the top (number 1 in rank), vice versa.**

7. The user interface must be intuitive and responsive.

	**This is not represented in my design, as it will be handled entirely within the GUI implementation.**

8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

	**There will be a single instance that is running at a time. The lifecycle of the app begins when the user launches the app and ends when the user terminates the app.**







