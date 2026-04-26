package stellar.poclasses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final String REGISTER_URL = "https://stellarburgers.education-services.ru/register";

    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By enterFromRegisterFormButton = By.xpath("//a[text()='Войти']");
    private final By loginFormTitle = By.xpath("//h2[text()='Вход']");
    private final By passwordError = By.xpath("//p[contains(text(),'Некорректный пароль')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Открыть страницу регистрации")
    public void openPage() {
        driver.get(REGISTER_URL);
    }

    @Step("Нажать кнопку регистрации")
    public void clickRegisterButton() {
        if (!driver.getCurrentUrl().contains("/register")) {
            driver.get(REGISTER_URL);
        }
    }

    @Step("Заполнить форму регистрации")
    public void fillRegistrationForm(String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    @Step("Отправить форму регистрации")
    public void submitRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Нажать кнопку входа из формы регистрации")
    public void clickEnterFromRegisterForm() {
        if (!driver.getCurrentUrl().contains("/register")) {
            driver.get(REGISTER_URL);
        }
        wait.until(ExpectedConditions.elementToBeClickable(enterFromRegisterFormButton)).click();
    }

    @Step("Проверка видимости формы входа")
    public boolean isLoginFormVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginFormTitle)).isDisplayed();
    }

    @Step("Проверка ошибки некорректного пароля")
    public boolean isPasswordErrorVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
    }
}