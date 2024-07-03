**Mock Rest-API Automation Framework:**

This is a comprehensive API automation framework designed for testing the mock REST APIs of a hypothetical online bookstore. 
The framework leverages Java for programming, WireMock for creating mock APIs, Rest Assured for testing the APIs, Maven for build automation, TestNG for test assertions, Faker API for generating test data,
Lombok library for reducing the boilerplate code and Extent Report for generating detailed test reports.


**Requirements:**<br>
* Java Development Kit (JDK) 11 or higher<br>
* Rest-assured version 5 or higher<br>
* Apache Maven 3.3 or higher<br>
* wiremock-standalone 3.0.1 or higher<br>
* Java faker API version 1.0.2 or higher<br>
* TestNG 3.7 or higher
* Extent reports 3.1.3 or higher<br>
* An IDE like IntelliJ IDEA or Eclipse<br>
* Lombok Java library 1.18.20 or higher


**Framework Structure:**

**base**:This package consists of all the reusable methods which common and reusable throughout the framework

**requestBuilder**:This package consists of the requestBuilder(RequestBody) methods for different API requests

**responseParser**:This package consists of the response validation methods for the different APIs.

**utils**: This package consists of all the utilities which are common and reusable throughout the framework

**resources**: 

**config.properties** - It contains the base urls, different environments and different APIs endpoints.

**testData**:It contains the input test Data(.csv) files

**RequestResponseSchema.json**:It contains the API request and response specifications/schemas jsons.

**test**: It contains all the Success and Error test case scenarios.

**mockserver**: This package contains MockApiServer class for invoking the mock service to execute the tests.

**pom.xml**: It contains all the required dependencies and libraries to run the project.

**testng.xml**: It contains all the tests/packages to be run in the test suite.



How to run test cases:

* Click on testng.xml to execute the complete suite

* Eclipse/IntelliJ -Right click to any test class and run

**Running from CMD Line :**

1.Navigate to framework/project path and use the below command

2.mvn clean install 

**test-output:**

Execution Reports(open REST-API-AutomationReport.html in a browser to see the detailed report)