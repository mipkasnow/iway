package android.helpers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Wrapper {

    private static final String IWAY_ID = "ru.iway.iwayapp:id/";

    public static SelenideElement elementById(String id) {
        return $(By.id(IWAY_ID + id));
    }
}
