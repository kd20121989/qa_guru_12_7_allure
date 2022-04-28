package com.gmail.kd2012.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

public class AnnotatedStepTest {

    private static final String repoName = "eroshenkoam/allure-example";
    private static final int issueNumber = 76;

    @Test
    public void testGithubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(repoName);
        steps.clickOnRepositoryLink(repoName);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(issueNumber);


    }
}
