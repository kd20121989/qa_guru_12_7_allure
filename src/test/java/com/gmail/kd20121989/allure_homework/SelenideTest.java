package com.gmail.kd20121989.allure_homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Owner("kd20121989")
public class SelenideTest {

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

        open(gitMainPage);

        headerSearch.click();
        headerSearch.setValue(fullRepoName).submit();

        firstRepoFoundLink.shouldHave(Condition.exactTextCaseSensitive(fullRepoName)).click();
        repoHeaderAuthor.shouldHave(Condition.exactTextCaseSensitive(authorName));
        repoHeaderRepoName.shouldHave(Condition.exactTextCaseSensitive(repoName));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Issues")
    @Story("Checking issue name in repository")
    public void testGithubIssueName() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(gitMainPage);

        headerSearch.click();
        headerSearch.setValue(fullRepoName).submit();

        firstRepoFoundLink.shouldHave(Condition.exactTextCaseSensitive(fullRepoName)).click();
        issuesTab.click();
        issuesList.findBy(Condition.id("issue_" + issueNumber))
                .$("a").shouldHave(Condition.exactTextCaseSensitive(issueName));

    }
}
