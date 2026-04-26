package stellar;

import io.qameta.allure.*;
import org.junit.*;

import stellar.api.UserApiHelper;
import stellar.model.UserData;
import stellar.poclasses.MainPage;
import stellar.poclasses.RegisterPage;

@Epic("UI Tests")
@Feature("Registration")
public class RegisterTests extends BaseTest {

    private UserData testUser;
    private UserApiHelper userApiHelper = new UserApiHelper();

    @Before
    @Step("Подготовка: генерация тестового пользователя")
    public void setUpUser() {
        testUser = userApiHelper.generateUniqueUser();
    }

    @After
    @Step("Очистка: удаление тестового пользователя через API")
    public void tearDownUser() {
        if (testUser != null && testUser.getAccessToken() != null) {
            userApiHelper.deleteUser(testUser.getAccessToken());
        }
    }

    @Test
    @Story("Successful registration")
    @Description("Проверка успешной регистрации нового пользователя")
    public void successfulRegistrationTest() {

        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.openPage();
        mainPage.clickEnterAccountButton();

        registerPage.clickRegisterButton();
        registerPage.fillRegistrationForm(
                testUser.getName(),
                testUser.getEmail(),
                testUser.getPassword()
        );
        registerPage.submitRegistration();

        Assert.assertTrue(
                "После успешной регистрации не открылась форма входа",
                registerPage.isLoginFormVisible()
        );
    }
}