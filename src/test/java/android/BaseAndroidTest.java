package android;

import android.driver.AndroidDriverWithReset;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import common.jupiter.ScreenAfterFail;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;

import static android.driver.AndroidDriverContainer.androidDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.step;

@ScreenAfterFail
public class BaseAndroidTest extends AndroidDriverWithReset {

    private static boolean setUpIsDone = false;

    @BeforeAll
    public static void setUp() {
        if (setUpIsDone) {
            return;
        }

        Configuration.browser = AndroidDriverWithReset.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 5000;

        setUpIsDone = true;
    }

    @BeforeEach
    public void startDriver() {
        addListener("AllureSelenide", new AllureSelenide()
                .includeSelenideSteps(false)
                .savePageSource(false)
                .screenshots(false));
        open();
        startVideoRecording();
    }

    @AfterEach
    public void tearDown() {
        step("Close driver", Selenide::closeWebDriver);
    }

    private void startVideoRecording() {
        androidDriver.startRecordingScreen(new AndroidStartScreenRecordingOptions()
                .withVideoSize("1280x720")
                .withTimeLimit(Duration.ofSeconds(200)));
    }
}
