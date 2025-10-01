**Rest-Assured Cucumber BDD Framework – Hotel Room Booking API**

This framework is built to test the Hotel Room Booking API using Rest-Assured and Cucumber (BDD style).
It follows a modular structure where feature files drive the test execution, POJOs handle request payloads, and schema validation ensures API responses are correct.

Key Features    

✅ API Test Automation using Rest-Assured

✅ Cucumber BDD framework -Gherkin language

✅ Data-driven testing: Examples in .feature files feed into POJO classes dynamically

✅ Jackson Library: Converts Java objects (POJOs) into JSON request payloads

✅ JSON Schema Validation using JsonSchemaValidator
    Example: GET room availability details response validated against getroomavailability.json

✅ TestNG for test execution
     Run tests with Cucumber Tags at feature/scenario level (@CreateroomBookinghappyflow,@Missingmandatoryfields)

⚙️ Prerequisites

Java: 24

Maven: To manage project dependencies

IDE: IntelliJ IDEA 

📦 Libraries and Tools Used

Rest-Assured – For API Testing
Cucumber – For BDD (Behavior-Driven Development)
TestNG – Test execution framework
Jackson Databind – For JSON (serialization & deserialization)
JsonSchemaValidator – For response schema validation

🚀 Installation

Clone the repository:
git clone https://github.com/Prema1823/API_Testing_Kata.git

Install dependencies:
mvn clean install

Executing the test scripts:
1. Update the appropriate tags in TestRunner file. 
    Eg. For Executing E2EAdmin.feature, pass @endtoendflowhotelbooking in TestRunner file under tags section
2. mvn clean test

Reports:


Note: 

Provided swagger URL is not working, so I have used the given URL--> console values and hit postman for verifying request and response
