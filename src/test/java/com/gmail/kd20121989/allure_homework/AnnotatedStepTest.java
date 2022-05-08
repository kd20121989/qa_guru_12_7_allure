package com.gmail.kd20121989.allure_homework;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;


@Owner("kd20121989")
public class AnnotatedStepTest {

    private static final String authorName = "minimaxir";
    private static final String repoName = "big-list-of-naughty-strings";
    private static final String fullRepoName = authorName + "/" + repoName;
    private static final String issueName = "Add rm -rf /";
    private static final String issueNumber = "238";


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Searching for repository")
    @Story("Checking that matched repository on top of the list")
    public void testGithubFirstMatchedRepo() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(fullRepoName);
        steps.checkAndOpenFirstRepoLink(fullRepoName);
        steps.checkCurrentRepoAuthorName(authorName);
        steps.checkCurrentRepoName(repoName);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Issues")
    @Story("Checking issue name in repository")
    public void testGithubIssueName() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(fullRepoName);
        steps.checkAndOpenFirstRepoLink(fullRepoName);
        steps.openIssuesTab();
        steps.checkIssueName(issueNumber, issueName);
    }
}
