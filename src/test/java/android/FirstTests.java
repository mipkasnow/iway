package android;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.jupiter.api.Test;

import static android.driver.AndroidDriverContainer.androidDriver;
import static android.helpers.Actions.swipeLeftQuick;
import static android.helpers.Wrapper.elementById;
import static com.codeborne.selenide.Condition.text;
import static io.qameta.allure.Allure.step;

public class FirstTests extends BaseAndroidTest {

    @Test
    public void firstTest() {
        step("Пропускаем слайды онбординга через свайп", () -> {
            elementById("textOnboarding").shouldHave(text("View upcoming\n" + "ride details"));
            swipeLeftQuick();
            elementById("textOnboarding").shouldHave(text("Track the car\n" + "on the map"));
            swipeLeftQuick();
            elementById("textOnboarding").shouldHave(text("Chat and call\n" + "with driver"));
            swipeLeftQuick();
            elementById("textOnboarding").shouldHave(text("Rate and review\n" + "after ride"));
            elementById("buttonNext").click();
        });

        step("Вводим невалидный номер телефона", () -> {
            elementById("phoneNumber").click();
            androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_7));
            androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
            androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
            androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
            androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_8));
            androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_8));
            androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_8));
            androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_5));
            androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_5));
            androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_2));
            androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_2));
        });

        step("Проверяем алерт номера телефона 'Invalid phone number'", () -> {
            elementById("textinput_error").shouldHave(text("Invalid phone number"));
        });
    }
}
