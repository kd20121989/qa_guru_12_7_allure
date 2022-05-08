package com.gmail.kd20121989.allure;

import com.codeborne.selenide.Condition;
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
    private static final SelenideElement headerSearch = $("input.header-search-input");

    @Step("Open main page")
    public void openMainPage () {
        open("https://github.com");
    }

    @Step("Search for repository {repo}")
    public void searchForRepository (String repo) {
        headerSearch.click();
        headerSearch.setValue(repo).submit();
    }

    @Step("Open repository {repo} link")
    public void clickOnRepositoryLink (String repo) {
        $(linkText(repo)).click();
    }

    @Step("Click on Issues tab")
    public void openIssuesTab () {
        $(partialLinkText("Issues")).click();
    }

    @Step("Check that issue with number {number} exists")
    public void shouldSeeIssueWithNumber(int number) {
        $(withText("#" + number)).shouldBe(Condition.visible);
        attachScreenshot();
    }

    @Attachment(value = "My favourite Screenshot", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
