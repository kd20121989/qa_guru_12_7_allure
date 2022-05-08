package com.gmail.kd20121989.allure_homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {
    private static final String gitMainPage = "https://github.com";
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

    @Step("Open main page")
    public void openMainPage () {
        open(gitMainPage);
    }

    @Step("Search for repository {repo}")
    public void searchForRepository (String repo) {
        headerSearch.click();
        headerSearch.setValue(repo).submit();
    }

    @Step("Check and open first Repository {fullRepoName} link")
    public void checkAndOpenFirstRepoLink (String fullRepoName) {
        firstRepoFoundLink.shouldHave(Condition.exactTextCaseSensitive(fullRepoName)).click();
    }

    @Step("Check current repo Author name is: {authorName}")
    public void checkCurrentRepoAuthorName (String authorName) {
        repoHeaderAuthor.shouldHave(Condition.exactTextCaseSensitive(authorName));
    }

    @Step("Check current repo name is: {repoName}")
    public void checkCurrentRepoName (String repoName) {
        repoHeaderRepoName.shouldHave(Condition.exactTextCaseSensitive(repoName));
    }

    @Step("Click on Issues tab")
    public void openIssuesTab () {
        issuesTab.click();
    }

    @Step("Check that issue with number {number} exists")
    public void shouldSeeIssueWithNumber(int number) {
        $(withText("#" + number)).shouldBe(Condition.visible);
        attachScreenshot();
    }

    @Step("Check that issue with number {issueNumber} have a name: {issueName}")
    public void checkIssueName(String issueNumber, String issueName) {
        issuesList.findBy(Condition.id("issue_" + issueNumber))
                .$("a").shouldHave(Condition.exactTextCaseSensitive(issueName));
        attachScreenshot();
    }

    @Attachment(value = "My favourite Screenshot", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
