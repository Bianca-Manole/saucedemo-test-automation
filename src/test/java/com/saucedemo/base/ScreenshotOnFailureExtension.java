package com.saucedemo.base;

import com.saucedemo.utils.ScreenshotUtil;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.LifecycleMethodExecutionExceptionHandler;

import java.nio.file.Path;

// Both hooks run before @AfterEach, so the browser is still open when the screenshot is taken.
public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback, LifecycleMethodExecutionExceptionHandler {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            takeScreenshot(context);
        }
    }

    @Override
    public void handleBeforeEachMethodExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        takeScreenshot(context);
        throw throwable;
    }

    private void takeScreenshot(ExtensionContext context) {
        if (!(context.getRequiredTestInstance() instanceof BaseTest test) || test.getDriver() == null) {
            return;
        }

        String name = context.getRequiredTestClass().getSimpleName() + "_" + context.getRequiredTestMethod().getName();
        try {
            Path screenshot = ScreenshotUtil.take(test.getDriver(), name);
            System.out.println("Screenshot saved: " + screenshot.toAbsolutePath());
        } catch (RuntimeException e) {
            System.err.println("Could not take screenshot for " + name + ": " + e.getMessage());
        }
    }
}
