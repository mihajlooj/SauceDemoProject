# SauceDemoProject

Automated UI tests for the SauceDemo web application.  
This project demonstrates functional testing of login, cart, checkout and pricing features using Selenium WebDriver, TestNG, and Maven.

## Description

This project contains automated tests covering core functionalities of the SauceDemo e-commerce website:
- User login with valid, invalid, and locked-out credentials
- Cart functionalities including adding, removing, and verifying items
- Checkout process with validations for required fields
- Checkout process with validations for required fields

The tests are written in Java, follow a structured Page Object Model (POM) design, and are executed using Maven.

## Getting Started

### Dependencies

* Java Development Kit (JDK) 25 or higher
* IDE (IntelliJ IDEA) 2025.2.3 or higher
* Maven 3.9.12
* IntelliJ IDEA or other IDE supporting Maven
* Windows 11 or compatible OS
* Supported browser (e.g., Firefox)

### Installing

1. Clone this repository:
```bash
git clone https://github.com/mihajlooj/SauceDemoProject.git
```
2. Open the project in IntelliJ IDEA.
3. Make sure Maven is configured and all dependencies are downloaded.

### Executing program

#### Run all tests using Maven
```bash
mvn clean test
```

#### Run tests from IntelliJ
- Open the Maven panel
- Under **Lifecycle**, click **test**

---

## Help

If tests fail to execute:
- Ensure the `pom.xml` dependencies are resolved.
- Ensure the Excel file with user credentials is present at: `Users/Swag Labs Users.xlsx`
- Verify JDK and Maven installations are correct.
```bash
java -version
mvn -version
```

---

## Project Structure

```
SauceDemoProject
├── pom.xml
└── src
    ├── main
    │   └── java
    └── test
        ├── java
        │   ├── Base
        │   │   ├── BaseTest.java
        │   │   └── ExcelReader.java
        │   ├── Pages
        │   │   ├── HomePage.java
        │   │   ├── LogInPage.java
        │   │   ├── CartPage.java
        │   │   └── CheckoutPage.java
        │   └── Tests
        │       ├── LogInFunctionalityTest.java
        │       ├── LockedOutUserLogInFunctionalityTest.java
        │       ├── CheckoutFunctionalityTest.java
        │       ├── CartFunctionalityTest.java
        │       └── PricingTest.java
        └── resources
            └── testdata
                └── Swag Labs Users.xlsx

```

---

## Test Scenarios

### LogInFunctionalityTest
- User can log in with valid credentials
- User cannot log in with blank username/password
- User cannot log in with blank username
- User cannot log in with blank password

### LockedOutUserLogInFunctionalityTest
- Locked-out user cannot log in
- Locked-out user cannot log in with invalid password
- Locked-out user cannot log in with blank password
- Locked-out user cannot log in by spamming login button

### CheckoutFunctionalityTest
- User can checkout with properly filled form
- User cannot checkout without first name
- User cannot checkout without last name
- User cannot checkout without zip code

### CartFunctionalityTest
- User can add item to cart
- User can remove item from cart
- User can add multiple items to cart
- User can remove multiple items from cart

### PricingTest
- Price of item remains the same in cart
- Price of multiple items remains the same in cart

---

## Authors

Mihajlo Jovanovic  
Junior QA Software Tester

---

## Version History

* 1.1
    * Added first iterations of pricing tests, relocated Excel document, further refined test classes

* 1.0
    * Initial release with complete login, cart, and checkout tests

---

## License

This project is intended for educational and demonstration purposes only.  
No license is currently applied.