package common.jupiter;

import common.Attach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class ScreenAfterFailExtension implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Attach.screenshotAs("Screenshot of failed step");
        Attach.videoAs("Video of failed test");
        throw throwable;
    }
}
