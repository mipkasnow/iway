package android.helpers;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.TouchAction;
import io.qameta.allure.Step;

import java.time.Duration;

import static android.driver.AndroidDriverContainer.androidDriver;
import static com.codeborne.selenide.Condition.appear;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.fail;

public class Actions {

    public static void refreshPageViaSwipe() {
        int deviceWidth = androidDriver.manage().window().getSize().getWidth();
        int deviceHeight = androidDriver.manage().window().getSize().getHeight();
        int midX = (deviceWidth / 2);
        int midY = (deviceHeight / 2);
        int bottomEdge = (int) (deviceHeight * 0.85f);
        new TouchAction(androidDriver)
                .press(point(midX, midY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(midX, bottomEdge))
                .release().perform();
    }

    public static void closeModalWindowViaClick(SelenideElement window) {
        int windowWidth = window.getSize().getWidth();
        int windowHeight = window.getSize().getHeight();
        int midX = (windowWidth / 2);
        int midY = (windowHeight / 2);
        int clickY = windowHeight - midY;
        new TouchAction(androidDriver)
                .press(point(midX, clickY))
                .release().perform();
    }

    private static void swipeUp(int timeOfSwipe) {
        var size = androidDriver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        new TouchAction(androidDriver)
                .press(point(x, startY))
                .waitAction(waitOptions(ofMillis(timeOfSwipe)))
                .moveTo(point(x, endY))
                .release()
                .perform();

    }

    @Step("Перезагрузка приложения")
    public static void terminateAppAndActivate() {
        androidDriver.terminateApp("ru.iway.iwayapp");
        androidDriver.activateApp("ru.iway.iwayapp");
    }

    private static void swipeDown(int timeOfSwipe) {
        var size = androidDriver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.2);
        int endY = (int) (size.height * 0.8);

        new TouchAction(androidDriver)
                .press(point(x, startY))
                .waitAction(waitOptions(ofMillis(timeOfSwipe)))
                .moveTo(point(x, endY))
                .release()
                .perform();

    }

    private static void swipeRight(int timeOfSwipe) {
        var size = androidDriver.manage().window().getSize();
        int y = size.height / 2;
        int startX = (int) (size.width * 0.1);
        int endX = (int) (size.width * 0.9);

        new TouchAction(androidDriver)
                .press(point(startX, y))
                .waitAction(waitOptions(ofMillis(timeOfSwipe)))
                .moveTo(point(endX, y))
                .release()
                .perform();

    }

    private static void swipeLeft(int timeOfSwipe) {
        var size = androidDriver.manage().window().getSize();
        int y = size.height / 2;
        int startX = (int) (size.width * 0.9);
        int endX = (int) (size.width * 0.1);

        new TouchAction(androidDriver)
                .press(point(startX, y))
                .waitAction(waitOptions(ofMillis(timeOfSwipe)))
                .moveTo(point(endX, y))
                .release()
                .perform();

    }

    public static void swipeUpQuick() {
        swipeUp(200);
    }

    public static void swipeDownQuick() {
        swipeDown(200);
    }

    public static void swipeRightQuick() {
        swipeRight(300);
    }

    public static void swipeLeftQuick() {
        swipeLeft(300);
    }

    public static void swipeUpToFindElement(SelenideElement element) {
        int swipes = 0;

        while (!element.isDisplayed()) {
            swipeUpQuick();
            swipes++;

            if (swipes == 8) {
                fail("Превышено количество свайпов!");
            }
        }
        element.should(appear);
    }

    public static void swipeDownToFindElement(SelenideElement element) {
        int swipes = 0;

        while (!element.isDisplayed()) {
            swipeDownQuick();
            swipes++;

            if (swipes == 8) {
                fail("Превышено количество свайпов!");
            }
        }
        element.should(appear);
    }

    public static void swipeElementFromCenterToLeft(SelenideElement element) {
        int leftX = element.getLocation().getX();
        int rightX = (int) ((leftX + element.getSize().getWidth()) * 0.8);
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        new TouchAction(androidDriver)
                .press(point(rightX, middleY))
                .waitAction(waitOptions(ofMillis(300)))
                .moveTo(point(leftX, middleY))
                .release()
                .perform();
    }

    @Step("Свернуть и открыть приложение")
    public static void minimizeAppAndReopen() {
        androidDriver.runAppInBackground(Duration.ofSeconds(2));
    }
}
