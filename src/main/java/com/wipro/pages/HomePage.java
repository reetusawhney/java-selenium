package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private By articleTitle = By.partialLinkText("Cypress vs Selenium:");

    public String getPageTitle(){
        return getDriver().getTitle();
    }

    public String getArticleTitle() {
        return getDriver().findElement(articleTitle).getText();
    }
}
