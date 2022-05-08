package com.gmail.kd20121989.allure;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.*;

import static com.codeborne.selenide.Selenide.$;

public class LabelsTest {

    private static final String repoName = "eroshenkoam/allure-example";
    private static final String issueNumber = "#76";
    private static final SelenideElement headerSearch = $("input.header-search-input");

    @Test
    @Owner("eroshenkoam")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Issues in Repository")
    @Story("Watching created issues in repository")
    @IssueShow
    @DisplayName("My favourite test")
    @Link(value = "Testing", url = "https://github.com")
    public void testGithubIssue() {

    }

    @Test
    public void testCode() {
        Allure.label("owner", "eroshenkoam");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.feature("Issues in Repository");
        Allure.story("Watching created issues in repository");
        Allure.link("Testing", "https://github.com");

    }

    @Documented
    @Owner("eroshenkoam")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Issues in Repository")
    @Story("Watching created issues in repository")
    @Target({ ElementType.TYPE, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface IssueShow {

    }
}
