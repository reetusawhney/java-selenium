package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

public class IframesPage extends BasePage {

    private By magnifyingGlass = By.xpath("//a[@aria-label='Search']//*[name()='svg']");
    private By magnifyingGlass2 = By.xpath("//button[@aria-label='Search']//*[name()='svg']");
    private By inputField = By.cssSelector("input[placeholder='Search']");
    private By pageHeading = By.xpath("//p[contains(text(),'I’m an iframe')]");

    public IframesPage search(String query) {
        click(magnifyingGlass);
        setText(inputField, query);
        click(magnifyingGlass2);
        return this;
    }

    public String getPageHeading() {
        return getText(pageHeading);
    }
}

