package common;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static android.driver.AndroidDriverContainer.androidDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Attach {

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{attachName}", type = "video/mp4", fileExtension = ".mp4")
    public static byte[] videoAs(String attachName) {
        return Base64.getDecoder().decode(androidDriver.stopRecordingScreen());
    }

}
