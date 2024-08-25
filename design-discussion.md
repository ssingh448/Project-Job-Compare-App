# **Design Description**

### Individual Design 1 - Chern Yee Chua

![Chern Yee’s Design](./images/design_cchua7.png) 

Chern Yee’s design is clear and concise in general. Proper relationship and data structure are used to represent the entire class diagram. The downside of this design is that the diagram lacks of job ranking mechanism and also the CurrentJob and JobOffer could have inherited from the same parent class.      

### Individual Design 2 - Sulekha Singh

![Sulekha’s Design](./images/design_ssingh448.png)

The classes and relationships are well represented, and all the instance attributes are listed with expected values. The use of two separate classes CurrentJob and JobOffer is excellent as both classes can be inherited from one parent class JobDetails, since that would allow us to add different functionalities later on. The drawback of this design is that the instance attributes should not be set as public (+), those attributes should be set as private and accessed via getter/setter method. The Comparison interface is redundant as ViewComparison() could be implemented in the selectedJobstoCompare() method. JobCatalog could also be listed as a list attribute in JobDetails.  

### Individual Design 3 - Tsoloane Lehloba

![Tsoloane’s Design](./images/design_tlehloba3.png) 

Tsoloane’s design is excellent with a clear indication of classes and their implementation behavior. The relationship that connects the ComparisonSettings and Job is brilliant. The inclusion of User class is also good even if it is not a requirement at this stage. The overall diagram is clear and easy to understand. However, the design is missing multiplicity such as 0, 1, * which can further illustrate the relationship between entities. The added User class might deviate from the original app implementation. The JobRanking class may be redundant and may just use ComparisonSettings instead since all the attributes are present in the ComparisonSettings class and the computeScore() method could be added in the ComparisonSettings class as well. The methods on the User class are also redundant with the MainMenu’s methods.  

### Individual Design 4 - Chenxuan Li

![Chenxuan’s Design](./images/design_cli648.png) 

Chenxuan’s design is very concise and easy to understand. Everything is clear and on point in demonstrating classes and methods of implementation. The use of boolean attribute isCurrentJob in the Job class is brilliant as it can be used to identify whether a Job is also the current Job or not, without using another class.  The downside of this design is that it might be difficult to extend or add other functionalities since there are only very few classes. A superclass of Job class that caters CurrentJob and JobOffer would be a better solution.  

### Team Design 

![Team’s Design](./images/design_team.png) 

After some discussion with the team members, we decided to use Chern Yee’s design as the base diagram with the following changes: (1) implement Job superclass and have both the CurrentJob and JobOffer class to inherit from, (2) include a method to show an ordered list of Jobs sorted by job rank, (3) compute jobScore on the Job class based on the calculation from ComparisonSettings class. The reason why we decided to have a Job superclass and have both CurrentJob and JobOffer to inherit from is because we want to make sure there will be only one instance of CurrentJob. By separating them into two different sub-classes, we will have a better flexibility in expanding and managing the class too. The method to show an ordered list of jobs based on the job score was not presented in the original diagram, and we decided to include that in this team design diagram as it will serve a better understanding of the design. Lastly, we added jobScore on the Job class so that we can sort the Job list by the jobScore, instead of computing for the jobScore on the fly which will be very inefficient and waste of computing resource. Job uses ComparisonSettings to calculate for the jobScore as each weight parameters are resided in ComparisonSettings class.     

### Summary

 Throughout the process, we have learned to work together as a team and come up with a design that we all agree on. During the discussion, we might have different opinions on different aspects, but eventually we can work that out without problems. Each of the team members has been contributing great amount of hard work into delivering this deliverable.    





