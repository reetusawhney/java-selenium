package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

public class PopupsPage extends BasePage {
    private By alertPopupsBtn = By.id("alert");
    private By confirmPopupBtn = By.id("confirm");
    private By promptPopupBtn = By.id("prompt");


    public PopupsPage clickAlertPopup() {
        click(alertPopupsBtn);
        return this;
    }

    public PopupsPage clickConfirmPopup() {
        click(confirmPopupBtn);
        return this;
    }

    public PopupsPage clickPromptPopup() {
        click(promptPopupBtn);
        return this;
    }

    }
