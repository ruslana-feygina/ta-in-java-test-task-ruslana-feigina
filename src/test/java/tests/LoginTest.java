package tests;

import driver.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import driver.DriverSingleton;

public class LoginTest {
    private LoginPage loginPage;
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());
        loginPage = new LoginPage(DriverSingleton.getDriver(browserType)).open();
    }

    @AfterMethod
    public void tearDown() {
        DriverSingleton.closeDriver();
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"", "", "Username is required"},
                {"standard_user", "", "Password is required"}
        };
    }

    @Test(dataProvider = "invalidCredentials")
    public void testLoginWithInvalidCredentials(String username, String password, String expectedMessage) {
        log.info("UC-1; UC-2: Testing login with invalid credentials: user='{}', pass='{}'", username, password);

        loginPage.logIn(username, password);

        Assert.assertTrue(loginPage.isErrorMessageVisible(),
                "Error message should be visible for invalid login.");
        Assert.assertTrue(loginPage.getErrorMessageText().contains(expectedMessage),
                "Error message text should match expected value.");

        log.info("Validation passed for expected message: {}", expectedMessage);
    }

    @Test
    public void testLoginWithValidCredentials() {
        log.info("UC-3: Testing login with valid credentials");

        loginPage.logIn("standard_user", "secret_sauce");

        String actualTitle = loginPage.getAppLogoText();
        Assert.assertEquals(actualTitle, "Swag Labs",
                "App logo text should match after successful login.");

        log.info("UC-3 Passed: Login successful, logo text = {}", actualTitle);
    }
}