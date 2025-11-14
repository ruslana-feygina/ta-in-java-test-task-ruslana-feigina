package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    private final By usernameInput = By.cssSelector("#user-name");
    private final By passwordInput = By.cssSelector("#password");
    private final By loginButton = By.cssSelector("#login-button");
    private final By errorMessage = By.cssSelector("div.error-message-container");
    private final By appLogo = By.cssSelector(".app_logo");

    public LoginPage(WebDriver driver) {
       super(driver);
    }

    public LoginPage open() {
        log.info("Opening login page");
        driver.get("https://www.saucedemo.com/");
        return this;
    }

    public void clearUsernameInput() { getUsernameInput().clear(); }

    public void clickUsernameInput() { getUsernameInput().click(); }

    public void setUsernameInput(String email) { getUsernameInput().sendKeys(email); }

    public void clearPasswordInput() { getPasswordInput().clear(); }

    public void clickPasswordInput() { getPasswordInput().click(); }

    public void setPasswordInput(String password) { getPasswordInput().sendKeys(password); }

    public void clickLoginButton() { getLoginButton().click(); }

    public void enterUsername(String email) {
        clickUsernameInput();
        clearUsernameInput();
        setUsernameInput(email);
    }

    public void enterPassword(String password) {
        clickPasswordInput();
        clearPasswordInput();
        setPasswordInput(password);
    }

    public void logIn(String email, String password) {
        enterUsername(email);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessageText() {
        return getErrorMessageContainer().getText();
    }

    public boolean isErrorMessageVisible() {
        try {
            return getErrorMessageContainer().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getAppLogoText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(appLogo));
        return driver.findElement(appLogo).getText();
    }

    @Override
    protected void waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    private WebElement getUsernameInput() { return driver.findElement(usernameInput); }

    private WebElement getPasswordInput() { return driver.findElement(passwordInput); }

    private WebElement getLoginButton() { return driver.findElement(loginButton); }

    private WebElement getErrorMessageContainer() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage);
    }
}