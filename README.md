# Audibene Automation Project

Please find below the steps to setup this project in eclipse and to run the project in the same

### Basic Requirements
- Java 1.8 or greater
- eclipse ide with maven

### Project Setup

  1. Clone this repository in your local machine
  2. Open eclipse and import this project into your eclipse workspace as below
        1. File Menu > Import > Projects from Folder or Archive
        2. Click on Directory button in Import wizard and select the path where you have cloned this repository
        3. Click on Finish to import the repository as project in eclipse
  3. Either right click on the project and select **Maven > Update project...** or go to command prompt to this project location and type `mvn clean install`
  4. Right click on the project in eclipse and select **Properties** where you will land up in Properties window in eclipse
        1. Click on **Add Library** button which launches Add Library Window
        2. Select **TestNG** in the options provided 
        3. Click on Next and then Finish
        4. Back in Properties window, click on **Apply and Close** button
  
### Run Project

#### To Run UI Test

1. Open src/main/java/runner/TestRunner file in eclipse
2. Right click and select **Run As > TestNG test**

#### To Run API Test

1. Open src/main/java/apitesting/Chucknorris_Testing.java file in eclipse
2. Right click and select **Run As > TestNG test**

You will be able to run the UI test in different browsers - viz Chrome Driver, Firefox Driver with the help of testng.xml. By default Chrome is enabled.

### Test Result Report

1. Open test-output/Default Suite folder  and open Default test.html to view the test results report


