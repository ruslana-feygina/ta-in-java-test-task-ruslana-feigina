package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By usernameInput = By.cssSelector("#user-name");
    private final By passwordInput = By.cssSelector("#password");
    private final By loginButton = By.cssSelector("#login-button");
    private final By errorMessage = By.cssSelector("div.error-message-container");
    private final By appLogo = By.cssSelector(".app_logo");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        initElements();
    }

    private void initElements() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    private WebElement getUsernameInput() { return driver.findElement(usernameInput); }

    public void clearUsernameInput() { getUsernameInput().clear(); }

    public void clickUsernameInput() { getUsernameInput().click(); }

    public void setUsernameInput(String email) { getUsernameInput().sendKeys(email); }

    private WebElement getPasswordInput() { return driver.findElement(passwordInput); }

    public void clearPasswordInput() { getPasswordInput().clear(); }

    public void clickPasswordInput() { getPasswordInput().click(); }

    public void setPasswordInput(String password) { getPasswordInput().sendKeys(password); }

    private WebElement getLoginButton() { return driver.findElement(loginButton); }

    public void clickLoginButton() { getLoginButton().click(); }

    private WebElement getErrorMessageContainer() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage);
    }

    public String getErrorMessageText() { return getErrorMessageContainer().getText(); }

    public boolean isErrorMessageVisible() {
        try {
            return getErrorMessageContainer().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    private void enterUsername(String email) {
        clickUsernameInput();
        clearUsernameInput();;
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

    public String getAppLogoText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(appLogo));
        return driver.findElement(appLogo).getText();
    }
}