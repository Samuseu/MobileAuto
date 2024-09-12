package tests;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import io.qameta.allure.Description;
import pages.MainPage;
import pages.SearchPage;

public class MainMenuTests extends BaseTest {

    private static MainPage mainPage;

    @BeforeAll
    public static void init() {
        mainPage = new MainPage();
    }

    @Description("Категории на главном экране имеют текст")
    @Test
    public void testCategories10items(){
        List<String> categoriesNameExpected = Arrays.asList(
                "Каталог", "Скидки недели", "Ликвидация",
                "Быстрая доставка", "Одежда и обувь", "Электроника",
                "Авто и мото", "Дом и сад", "Стать продавцом", "Ozon для бизнеса");
        mainPage.closePopUp()
                .checkCategoriesHasTexts(categoriesNameExpected);
    }

    @Description("Переход в другое меню делает кнопку не активной")
    @Test
    public void testGoToAnotherMenuIconNotSelected(){
        mainPage.closePopUp();
        mainPage.checkMainMenuButtonIsSelected();
        mainPage.openCatalog().isMainMenuIconSelected();
        mainPage.checkMainMenuButtonIsNotSelected();
    }

    @Test
    @Description("Добавляем товар в корзину")
    public void testAddItemToCart()   {
        String searchItem = "projector";
        mainPage.closePopUp();
        SearchPage searchPage = new SearchPage();
        searchPage.search(searchItem);
        String fullName = searchPage.getFirstFoundedFullItemName();

        searchPage.openFirstFoundItemByTextContains()
                .addToCart()
                .openCart()
                .closePop()
                .assertItemsCount(1)
                .assertItemNameInCart(fullName);
    }

}
