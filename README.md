**Rest-Assured Cucumber BDD Framework – Hotel Room Booking API**

This framework is built to test the Hotel Room Booking API using Rest-Assured and Cucumber (BDD style).
It follows a modular structure where feature files drive the test execution, POJOs handle request payloads, and schema validation ensures API responses are correct.

Key Features    

✅ API Test Automation using Rest-Assured

✅ Cucumber BDD for writing human-readable feature files

✅ Data-driven testing: Examples in .feature files feed into POJO classes dynamically

✅ Jackson Library: Converts Java objects (POJOs) into JSON request payloads

✅ Utilities layer: Common reusable methods (request setup, config reader, etc.)

✅ JSON Schema Validation using JsonSchemaValidator

✅ Example: GET booking details response validated against getbookingresponseschema.json

✅ TestNG for test execution

✅ Run tests with Cucumber Tags at feature/scenario level (@CreateroomBookinghappyflow,@Roombookingerrorflow)

⚙️ Prerequisites

Java: 8 or higher

Maven: To manage project dependencies

IDE: IntelliJ IDEA / Eclipse (recommended: IntelliJ for BDD plugins)

📦 Libraries and Tools Used

Rest-Assured – For API Testing

Cucumber – For BDD (Behavior-Driven Development)

TestNG – Test execution framework

Jackson Databind – For JSON (serialization & deserialization)

JsonSchemaValidator – For response schema validation

🚀 Installation

Clone the repository:

git clone 


Install dependencies:

mvn clean install
mvn clean test
