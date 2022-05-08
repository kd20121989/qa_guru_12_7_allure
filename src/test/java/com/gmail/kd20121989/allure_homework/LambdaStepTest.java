package com.gmail.kd20121989.allure_homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Owner("kd20121989")
public class LambdaStepTest {

    private static final String gitMainPage = "https://github.com";
    private static final String authorName = "minimaxir";
    private static final String repoName = "big-list-of-naughty-strings";
    private static final String fullRepoName = authorName + "/" + repoName;
    private static final String issueName = "Add rm -rf /";
    private static final String issueNumber = "238";
    private static final SelenideElement headerSearch = $("input.header-search-input");
    private static final SelenideElement repositoryResults = $("ul.repo-list");
    private static final SelenideElement firstRepoFound = repositoryResults.$$("li").first();
    private static final SelenideElement firstRepoFoundLink = firstRepoFound.$("a");
    private static final SelenideElement repoHeader = $("#repository-container-header");
    private static final SelenideElement repoHeaderAuthor = repoHeader.$("span.author");
    private static final SelenideElement repoHeaderRepoName = repoHeader.$("strong a");
    private static final SelenideElement issuesTab = $(".js-repo-nav a#issues-tab");
    private static final ElementsCollection issuesList = $("div[aria-label=Issues]")
            .$$(".js-issue-row");

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Searching for repository")
    @Story("Checking that matched repository on top of the list")
    public void testGithubFirstMatchedRepo() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            open(gitMainPage);
        });
        step("Search for repository " + fullRepoName, () -> {
            headerSearch.click();
            headerSearch.setValue(fullRepoName).submit();
        });
        step("Check and open first Repository " + repoName + " link", () -> {
            firstRepoFoundLink.shouldHave(Condition.exactTextCaseSensitive(fullRepoName)).click();
        });
        step("Check current repo Author name is: " + authorName, () -> {
            repoHeaderAuthor.shouldHave(Condition.exactTextCaseSensitive(authorName));
        });
        step("Check current repo name is: " + repoName, () -> {
            repoHeaderRepoName.shouldHave(Condition.exactTextCaseSensitive(repoName));
        });
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Issues")
    @Story("Checking issue name in repository")
    public void testGithubIssueName() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            open(gitMainPage);
        });
        step("Search for repository " + fullRepoName, () -> {
            headerSearch.click();
            headerSearch.setValue(fullRepoName).submit();
        });
        step("Check and open first Repository " + repoName + " link", () -> {
            firstRepoFoundLink.shouldHave(Condition.exactTextCaseSensitive(fullRepoName)).click();
        });
        step("Click on Issues tab" + repoName + " link", () -> {
            issuesTab.click();
        });
        step("Check that issue with number " + issueNumber +" have name: " + issueName, () -> {
            issuesList.findBy(Condition.id("issue_" + issueNumber))
                    .$("a").shouldHave(Condition.exactTextCaseSensitive(issueName));
            Allure.getLifecycle().addAttachment(
                    "PageSource",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}
