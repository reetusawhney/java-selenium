package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

public class NavBar extends BasePage {
    private By sandboxTab = By.id("menu-item-420");

    public SandboxPage goToSandboxPage(){
        click(sandboxTab);
        return new SandboxPage();

    }
}
