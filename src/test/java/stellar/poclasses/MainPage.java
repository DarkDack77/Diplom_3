package stellar.poclasses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final String BASE_URL = "https://stellarburgers.education-services.ru/";

    private final By enterAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By logoButton = By.xpath("//div[contains(@class,'AppHeader_header__logo')]");
    private final By makeOrderButton = By.xpath("//button[text()='Оформить заказ']");

    private final By bunsSection = By.xpath("//span[text()='Булки']");
    private final By saucesSection = By.xpath("//span[text()='Соусы']");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть главную страницу")
    public void openPage() {
        driver.get(BASE_URL);
    }

    @Step("Нажать кнопку Войти в аккаунт")
    public void clickEnterAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterAccountButton)).click();
    }

    @Step("Нажать кнопку Личный кабинет")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Нажать кнопку Конструктор")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    @Step("Нажать логотип Stellar Burgers")
    public void clickLogoButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoButton)).click();
    }

    @Step("Проверка успешной авторизации")
    public boolean isUserLoggedIn() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton)).isDisplayed();
    }

    @Step("Переход к разделу Соусы")
    public void clickSaucesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesSection)).click();
    }

    @Step("Переход к разделу Начинки")
    public void clickFillingsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsSection)).click();
    }

    @Step("Переход к разделу Булки")
    public void clickBunsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsSection)).click();
    }
}