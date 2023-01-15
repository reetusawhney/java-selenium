package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

public class JavaScriptDelaysPage extends BasePage {
    private By startBtn = By.id("start");
    private By countdownTxt = By.id("delay");

    public JavaScriptDelaysPage clickStart() {
            click(startBtn);
            return this;
    }

    public JavaScriptDelaysPage waitForCountdownText(String text) {
        waitForElementValue(countdownTxt,text);
        return this;
    }
}
