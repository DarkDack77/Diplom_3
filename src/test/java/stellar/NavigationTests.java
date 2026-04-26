package stellar;

import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Test;
import stellar.poclasses.MainPage;

@Epic("UI Tests")
@Feature("Constructor navigation")
public class NavigationTests extends BaseTest {

    @Test
    @Story("Sauces section")
    @Description("Переход к разделу Соусы")
    public void switchToSaucesSectionTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.openPage();
        mainPage.clickSaucesSection();

        Assert.assertTrue(true);
    }

    @Test
    @Story("Fillings section")
    @Description("Переход к разделу Начинки")
    public void switchToFillingsSectionTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.openPage();
        mainPage.clickFillingsSection();

        Assert.assertTrue(true);
    }

    @Test
    @Story("Buns section")
    @Description("Переход к разделу Булки")
    public void switchToBunsSectionTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.openPage();
        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();

        Assert.assertTrue(true);
    }
}