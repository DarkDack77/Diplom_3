package stellar;

import io.qameta.allure.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import stellar.api.UserApiHelper;
import stellar.model.UserData;
import stellar.poclasses.LoginPage;
import stellar.poclasses.MainPage;
import stellar.poclasses.RegisterPage;

import java.util.Objects;

@RunWith(Parameterized.class)
@Epic("UI Tests")
@Feature("Authentication")
public class LoginTests extends BaseTest {

    private UserData testUser;
    private UserApiHelper userApiHelper;
    private final String loginMethod;

    public LoginTests(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    @Parameterized.Parameters(name = "Способ авторизации: {0}")
    public static Object[][] getLoginData() {
        return new Object[][]{
                {"Через кнопку «Войти в аккаунт» на главной"},
                {"Через кнопку «Личный кабинет»"},
                {"Через кнопку в форме регистрации"},
                {"Через кнопку в форме восстановления пароля"}
        };
    }

    @Before
    @Step("Подготовка: создание тестового пользователя через API")
    public void setUpUser() throws Exception {
        userApiHelper = new UserApiHelper();
        testUser = userApiHelper.generateUniqueUser();
        userApiHelper.registerUser(testUser);
    }

    @After
    @Step("Очистка: удаление тестового пользователя через API")
    public void tearDownUser() {
        if (testUser != null && testUser.getAccessToken() != null) {
            userApiHelper.deleteUser(testUser.getAccessToken());
        }
    }

    @Test
    @Story("Successful login")
    @Description("Проверка авторизации пользователя разными способами")
    public void loginTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.openPage();

        if (Objects.equals(loginMethod, "Через кнопку «Войти в аккаунт» на главной")) {
            mainPage.clickEnterAccountButton();
        }

        if (Objects.equals(loginMethod, "Через кнопку «Личный кабинет»")) {
            mainPage.clickPersonalAccountButton();
        }

        if (Objects.equals(loginMethod, "Через кнопку в форме регистрации")) {
            mainPage.clickEnterAccountButton();
            loginPage.clickRegisterLink();
            registerPage.clickEnterFromRegisterForm();
        }

        if (Objects.equals(loginMethod, "Через кнопку в форме восстановления пароля")) {
            mainPage.clickEnterAccountButton();
            loginPage.clickForgotPassword();
            loginPage.clickEnterFromRestorePassword();
        }

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        Assert.assertTrue(
                "Пользователь не авторизовался",
                mainPage.isUserLoggedIn()
        );
    }
}