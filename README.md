# DemoBlaze Automation Testing Project

## PPT Link
[Capstone Project Presentation - Harshit Kumar](https://docs.google.com/presentation/d/1Yi9w3JvyFaGgTY3pUz93X0_Mhew-ECh2CaDzxu6-wps/edit?usp=sharing)

## How to Run This Code
1. Import the project as a **Maven Project** into **Eclipse** or **IntelliJ**.
2. Update Maven dependencies:
   ```
   File → Maven → Update Project
   ```
3. Navigate to the **TestNG suite** XML file inside the project.
4. Right-click on the file → **Run As → TestNG Suite**.
5. The automation tests will start executing, and reports will be generated.

## Project Overview
The goal of this project is to **automate the testing workflow** for the **DemoBlaze website**, ensuring smooth end-to-end functionality from **user signup** to **order confirmation**.

## Scope of Automation
- **Authentication** → Signup & Login
- **Shopping Flow** → Product Selection & Cart Management
- **Checkout** → Payment & Order Confirmation

## Tools & Technologies
- **IDE** → Eclipse
- **Automation Framework** → Selenium WebDriver
- **Build Tool** → Maven
- **Testing Framework** → TestNG
- **Version Control** → Git & GitHub
- **CI/CD Tool** → Jenkins
- **Project Management** → Jira + Zephyr

## Test Workflow
1. **User Registration** → Verify account creation functionality.
2. **User Login** → Validate successful login for registered users.
3. **Navigate Categories** → Browse through available product categories.
4. **Select Product** → Open and verify product details.
5. **Add Product to Cart** → Add an item and confirm successful addition.
6. **Place Order & Verify Confirmation** → Submit an order and validate the confirmation message with an order ID.

## Project Management & Integration

### Jira for Project Management
- Used for tracking tasks, test cases, and issues.
- Organized automation tasks on a Jira board for better collaboration.

### Zephyr for Test Management
- Integrated with Jira for managing test cases.
- Used to create, execute, and report automated test cases linked to project requirements.

### Jenkins Integration
- Configured Jenkins for **CI/CD** to automate the testing pipeline.
- Jenkins fetches the latest code from GitHub, triggers the build, runs tests, and generates reports automatically.
- Ensures **faster feedback**, **automation**, and **reliable test execution**.
