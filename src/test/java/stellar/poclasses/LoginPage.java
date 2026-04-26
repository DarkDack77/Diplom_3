package stellar.poclasses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final String LOGIN_URL = "https://stellarburgers.education-services.ru/login";
    private final String FORGOT_PASSWORD_URL = "https://stellarburgers.education-services.ru/forgot-password";

    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");
    private final By enterFromRestorePasswordButton = By.xpath("//a[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть страницу входа")
    public void openPage() {
        driver.get(LOGIN_URL);
    }

    @Step("Нажать ссылку регистрации")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Нажать ссылку восстановления пароля")
    public void clickForgotPassword() {
        driver.get(FORGOT_PASSWORD_URL);
    }

    @Step("Нажать кнопку входа из формы восстановления пароля")
    public void clickEnterFromRestorePassword() {
        wait.until(ExpectedConditions.elementToBeClickable(enterFromRestorePasswordButton)).click();
    }

    @Step("Авторизоваться")
    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}
