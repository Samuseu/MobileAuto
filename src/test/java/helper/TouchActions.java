package helper;

import org.openqa.selenium.Dimension;

import driver.EmulatorDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class TouchActions extends EmulatorDriver {
    public static void clickOnCenter() {
        // Получаем размер экрана устройства
        Dimension size = driver.manage().window().getSize();

        // Вычисляем координаты центра экрана
        int x = size.width / 2;
        int y = size.height / 2;

        // Выполняем клик по центру
        new TouchAction<>(driver)
                .tap(PointOption.point(x, y))
                .perform();
    }
}
