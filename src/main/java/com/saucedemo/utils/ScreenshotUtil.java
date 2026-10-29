package com.saucedemo.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {
    private static final Path SCREENSHOTS_DIR = Paths.get("screenshots");
    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtil() {
    }

    public static Path take(WebDriver driver, String name) {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path target = SCREENSHOTS_DIR.resolve(name + "_" + LocalDateTime.now().format(TIMESTAMP) + ".png");
        try {
            Files.createDirectories(SCREENSHOTS_DIR);
            Files.copy(source.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException("Could not save screenshot " + target, e);
        }
        return target;
    }
}
