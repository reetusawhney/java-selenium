package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

import java.util.Base64;

public class HoverPage extends BasePage {
    private By textBox =By.id("mouse_over");
    public HoverPage hover() {
        hoverElement(textBox);
        return this;
    }

    public String getHoverText() {
        return getText(textBox);
    }
}
