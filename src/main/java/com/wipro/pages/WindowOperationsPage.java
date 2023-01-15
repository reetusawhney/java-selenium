package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

import java.util.Iterator;
import java.util.Set;

public class WindowOperationsPage extends BasePage {
    private By  newWindowBtn= By.cssSelector("button[onclick='newWindow()'] b");
    public WindowOperationsPage clickNewWindow() {
        click(newWindowBtn);
        return this;
    }

    public void switchToNewWindow() {
        //Get current window handle
        String  currentWindow =getDriver().getWindowHandle();

        //Get all window handles
        Set<String> handles = getDriver().getWindowHandles();

        //Switch to new window
        Iterator <String> iter = handles.iterator();
        String newWindow;
        while (iter.hasNext()){
            newWindow=iter.next();
            if(!currentWindow.equals(newWindow)){
                getDriver().switchTo().window(newWindow);
            }
        }



    }
}
