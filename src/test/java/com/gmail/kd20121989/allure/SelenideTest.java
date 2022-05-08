package com.gmail.kd20121989.allure;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

@Owner("eroshenkoam")
@Severity(SeverityLevel.BLOCKER)
@Feature("Issues in Repository")
@Story("Watching created issues in repository")
public class SelenideTest {

    @Test
    public void testGithubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        String repoName = "eroshenkoam/allure-example";

        open("https://github.com");

        SelenideElement headerSearch = $("input.header-search-input");

        headerSearch.click();
        headerSearch.setValue(repoName).submit();

        $(linkText(repoName)).click();
        $(partialLinkText("Issues")).click();
        $(withText("#76")).click();
    }
}
