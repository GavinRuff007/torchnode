package org.example.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginSteps {


    private WebDriver driver;

    public void setUp() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Headless mode is essential for CI/CD pipelines (Jenkins)
            driver = new ChromeDriver(options);
        }
    }

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        setUp();
        driver.get("http://localhost:8081/");
    }

    @Then("the page title should be {string}")
    public void pageTitleShouldBe(String expectedTitle) {
        assertEquals(expectedTitle, driver.getTitle());
    }

    @And("the {string} and {string} fields should be visible")
    public void fieldsShouldBeVisible(String usernameId, String passwordId) {
        WebElement usernameInput = driver.findElement(By.id(usernameId));
        WebElement passwordInput = driver.findElement(By.id(passwordId));

        assertTrue(usernameInput.isDisplayed(), "Username field is not visible");
        assertTrue(passwordInput.isDisplayed(), "Password field is not visible");
    }

    @And("the {string} button should be enabled")
    public void submitButtonShouldBeEnabled(String buttonText) {
        WebElement loginButton = driver.findElement(By.cssSelector("button.login-btn"));
        assertTrue(loginButton.isDisplayed());
        assertTrue(loginButton.isEnabled());
        assertEquals(buttonText, loginButton.getText().trim());
    }

    @When("the user enters username {string} and password {string}")
    public void userEntersCredentials(String username, String password) {
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.clear();
        usernameInput.sendKeys(username);

        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    @And("clicks the {string} button")
    public void clickOnSubmitButton(String buttonText) {
        WebElement loginButton = driver.findElement(By.cssSelector("button.login-btn"));
        loginButton.click();
    }

    @Then("the user should be redirected to the dashboard")
    public void userRedirectedToDashboard() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/dashboard") || currentUrl.contains("/home"),
                "User was not redirected to the dashboard. Current URL: " + currentUrl);
        driver.quit();
    }

    @When("the user clicks the {string} button without entering credentials")
    public void userClicksSubmitWithoutCredentials(String buttonText) {
        WebElement loginButton = driver.findElement(By.cssSelector("button.login-btn"));
        loginButton.click();
    }

    @Then("the system should display a validation error for required fields")
    public void validationErrorShouldBeShown() {
        WebElement usernameInput = driver.findElement(By.id("username"));
        String validationMessage = usernameInput.getAttribute("validationMessage");

        assertNotNull(validationMessage);
        assertFalse(validationMessage.isEmpty(), "HTML5 Validation message should not be empty");
        driver.quit();
    }
}
