package android.driver;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.URL;

import static android.driver.AndroidDriverContainer.androidDriver;

public class AndroidDriverWithReset implements WebDriverProvider {

    @SneakyThrows
    private static URL getAppiumServerUrl() {
        return new URL("http://0.0.0.0:4723/wd/hub");
    }

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        File app = getApp();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName("Android");
        options.setDeviceName("Pixel 5");
        options.setPlatformVersion("12.0");
        options.setApp(app.getAbsolutePath());
        options.setAppPackage("ru.iway.iwayapp");
        options.setAppActivity(".ui.modules.SplashActivity");
        options.setNoReset(false);
        options.autoGrantPermissions();
        options.setLanguage("en");
        options.setLocale("en".toUpperCase());

        AndroidDriverContainer.androidDriver = new AndroidDriver(getAppiumServerUrl(), options);
        return androidDriver;
    }

    private File getApp() {
        return new File("src/test/resources/app/iway.com.apk");
    }
}
