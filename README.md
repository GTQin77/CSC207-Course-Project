# CSC207-Course-Project
### Introduction
This repository stores the course project for CSC207 from Team JAJA. 
We are making a day trip planner app, where based on user input and location, it will return a sequence of 
local meals, activities, and events for them to experience throughout the day.

As a user, you will be able to get three meals and activity recommendations by simply inputting a sentence to 
describe what vibe you want for the day. You will also be able to delete events that you don't want and view past 
events that you had for the previous day's plan. At the end of the day, you will get three words that summarize the 
vibe of the day. 

___________________________________________________________________________________________________________________
### Features so far
* User account: user can create an account by entering username, password and repeated password to ensure the password was entered correctly. Our program can check whether the user is already exist. If not, and the password matched the repeated password, then the user creation will be successful. Otherwise, the account creation will failed.
* Generate the most basic dayplan: user can input the number of meals and activities, along with a description of what the user wants the day to be.

Note: the corresponding use case names for these two are: user and dayplanList in the program. The other use cases are not implemented yet, and implementation for business detail is in progress.
___________________________________________________________________________________________________________________
### APIs
OpenAI:
* takes in the user description and select a category in Yelp Fusion's category list matching the description.
* look at the user's day plan and return three words matching the vibe of the day.

Yelp Fusion:
* search activities and restuarants with the selected category from ChatGPT so that the program can put together a day plan for the user!

___________________________________________________________________________________________________________________
# How the Program Works (Phase 1)
To begin with, we need to install maven to run the code.
Then, we need to set up the environment variable with 2 api tokens for app/Main.java. Without this step, the dayplan will not work.

Go to app/Main.java and click run. The program will first direct you to the sign up stage in the console. After signing up, you can proceed with entering the number of meals and activities you want in your day plan. You will be also ask to put in the city that you are in and a sentence that describe how you want your day to be. An example sentence will be, "I want an exciting day!". After entering all these things, you can wait for the program to generate a dayplan for you! Check the console for the day plan and start your amazing day.

Note: Do not use a real username and password for now.

___________________________________________________________________________________________________________________
### Thoughts that goes into the Current Design
In the current design, we focused a lot of time designing the enetities and making them to be a good fit of the goal of our program. In addition, we packaged our program by use cases, so that it is easier to navigate and maintain. The program currently involves using console to get users' input and output day plan, but we are ready to create an UI for our program in phase 2. 

___________________________________________________________________________________________________________________
### Authors (JAJA)
Janice, Amelia, Joshua, Ashley

### Future Work/Extendability
The functionality of this program (after implementing all use cases as promised by the design blueprint) can be extended by the following ways:
* enabling user to generate a mysterious day plan without inputting anything
* allow user to delete the account
* allow user to select the model of GPT

  

