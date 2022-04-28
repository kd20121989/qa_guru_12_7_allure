package com.gmail.kd2012.allure;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.WebDriverRunner;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class LambdaStepTest {

    private static final String repoName = "eroshenkoam/allure-example";
    private static final String issueNumber = "#76";
    private static final SelenideElement headerSearch = $("input.header-search-input");

    @Test
    public void testGithubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            open("https://github.com");
        });
        step("Search for repository " + repoName, () -> {
            headerSearch.click();
            headerSearch.setValue(repoName).submit();
        });
        step("Open repository " + repoName + " link", () -> {
            $(linkText(repoName)).click();
        });
        step("Click on Issues tab", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Check that issue with number " + issueNumber +" exists", () -> {
            $(withText(issueNumber)).click();
            Allure.getLifecycle().addAttachment(
                    "PageSource",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
                    );
        });

    }
}
