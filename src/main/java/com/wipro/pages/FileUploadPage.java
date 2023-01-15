package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

public class FileUploadPage extends BasePage {
    private By chooseFileBtn= By.id("file_upload");
    private By uploadBtn= By.cssSelector("input[value='Upload It!']");
    private By uploadStatus= By.cssSelector(".wpcf7-response-output");

    public FileUploadPage uploadFile(String path) {
        getDriver().findElement(chooseFileBtn).sendKeys(path);
        click(uploadBtn);
        waitForElementText(uploadStatus,"File upload complete");
        return this;
    }
}
