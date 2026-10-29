package com.saucedemo.tests.api;

import com.saucedemo.api.clients.SauceDemoClient;
import com.saucedemo.api.models.WebManifest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class SauceDemoGetApiTests {
    private static final String APP_NAME = "Swag Labs";

    private final SauceDemoClient sauceDemo = new SauceDemoClient();

    @Test
    public void homePageIsServedAsHtml() {
        Response response = sauceDemo.getHomePage();

        Assertions.assertEquals(200, response.statusCode(), "Home page should return 200");
        Assertions.assertTrue(response.contentType().startsWith("text/html"), "Home page should be HTML");
        Assertions.assertTrue(response.asString().contains("<title>" + APP_NAME + "</title>"), "Page title should be " + APP_NAME);
        Assertions.assertTrue(response.asString().contains("<div id=\"root\">"), "Page should contain the React root element");
    }

    @Test
    public void homePageRespondsWithinTwoSeconds() {
        Response response = sauceDemo.getHomePage();

        Assertions.assertTrue(response.timeIn(TimeUnit.MILLISECONDS) < 2000,
                "Home page should respond in under 2 s, took " + response.timeIn(TimeUnit.MILLISECONDS) + " ms");
    }

    @Test
    public void manifestDescribesTheSwagLabsApp() {
        Response response = sauceDemo.getManifest();

        Assertions.assertEquals(200, response.statusCode(), "manifest.json should return 200");
        Assertions.assertTrue(response.contentType().startsWith("application/json"), "manifest.json should be JSON");
        WebManifest manifest = response.as(WebManifest.class);
        Assertions.assertEquals(APP_NAME, manifest.name(), "App name should match");
        Assertions.assertEquals(APP_NAME, manifest.shortName(), "App short name should match");
        Assertions.assertFalse(manifest.icons().isEmpty(), "Manifest should list the app icons");
    }

    @Test
    public void manifestMatchesJsonSchema() {
        Response response = sauceDemo.getManifest();

        assertThat("manifest.json should match the web manifest schema",
                response.asString(), matchesJsonSchemaInClasspath("schemas/manifest-schema.json"));
    }

    @Test
    public void everyIconListedInManifestIsAvailable() {
        WebManifest manifest = sauceDemo.getManifest().as(WebManifest.class);

        for (WebManifest.ManifestIcon icon : manifest.icons()) {
            Response response = sauceDemo.getResource(icon.src());
            Assertions.assertEquals(200, response.statusCode(), "Icon " + icon.src() + " should return 200");
            Assertions.assertEquals(icon.type(), response.contentType(), "Icon " + icon.src() + " should have the declared type");
        }
    }

    @Test
    public void javascriptBundleReferencedByHomePageIsServed() {
        Matcher script = Pattern.compile("src=\"(/assets/[^\"]+\\.js)\"").matcher(sauceDemo.getHomePage().asString());
        Assertions.assertTrue(script.find(), "Home page should reference a JavaScript bundle");

        Response response = sauceDemo.getResource(script.group(1));

        Assertions.assertEquals(200, response.statusCode(), "JavaScript bundle should return 200");
        Assertions.assertTrue(response.contentType().contains("javascript"), "Bundle should be served as JavaScript");
    }

    @Test
    public void robotsTxtAllowsAllCrawlers() {
        Response response = sauceDemo.getRobotsTxt();

        Assertions.assertEquals(200, response.statusCode(), "robots.txt should return 200");
        Assertions.assertTrue(response.asString().contains("User-agent: *"), "robots.txt should address all crawlers");
    }

    // Inner pages exist only in the browser (React routing), so the server does not know them.
    @ParameterizedTest
    @ValueSource(strings = {"/inventory.html", "/cart.html", "/checkout-step-one.html", "/page-that-does-not-exist"})
    public void pagesNotOnServerReturnNotFound(String path) {
        Response response = sauceDemo.getResource(path);

        Assertions.assertEquals(404, response.statusCode(), path + " should return 404 when requested directly");
    }
}
