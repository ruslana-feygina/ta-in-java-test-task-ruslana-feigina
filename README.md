# Java Test Automation – SauceDemo Login Tests

Automation project for testing the **SauceDemo login page** using **Java**, **Selenium WebDriver**, and **TestNG**.

---

##  Tech Stack

| Category | Tools |
|-----------|-------|
| Language | Java 21 |
| Automation Tool | Selenium WebDriver |
| Test Framework | TestNG |
| Build Tool | Maven |
| Logging | SLF4J |
| Design Patterns | Page Object, Singleton |
| Browsers | Chrome, Firefox |
| Parallel Execution | Supported (TestNG XML) |

---

##  Test Cases
1. **Empty credentials** → shows “Epic sadface: Username is required”  
2. **Only username** → shows “Epic sadface: Password is required”  
3. **Valid credentials** → redirects to inventory page with “Swag Labs”

---


##  Run Tests
- In IntelliJ → right-click `testng.xml` → **Run**
- Or via terminal:
```bash
mvn clean test
