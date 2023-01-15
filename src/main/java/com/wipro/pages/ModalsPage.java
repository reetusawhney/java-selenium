package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

public class ModalsPage extends BasePage {
    private By formModal = By.id("formModal");
    private By simpleModal = By.id("simpleModal");
    private By mName = By.id("g1051-name");
    private By mEmail = By.id("g1051-email");
    private By mMessage = By.id("contact-form-comment-g1051-message");
    private By submitbtn = By.cssSelector("button[class='pushbutton-wide']");
    private By closeBtn = By.cssSelector("div[id='popmake-1318'] button[aria-label='Close']");


    public ModalsPage clickSimpleModal() {
        click(simpleModal);
        click(closeBtn);
        return this;
    }

    public ModalsPage clickFormModal() {
        click(formModal);
        return this ;
    }
    public ModalsPage modalSendMessage(String name, String email, String message) {
        setText(mName,name);
        setText(mEmail,email);
        setText(mMessage,message);
        click(submitbtn);
        return this;
    }



}

