package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class FormFieldsPage extends BasePage {
    private By inputField = By.id("g1103-name");
    private By dropDown =By.id("g1103-doyouhaveanysiblings");

    public FormFieldsPage setName(String name) {
        setText(inputField,name);
        return this;
    }

    public String getName() {
        return getText(inputField);
    }

    public FormFieldsPage clickCheckbox(String value) {
        click(By.cssSelector("input[value='" + value + "']"));
        return this;
    }
    public boolean checkboxIsSelected(String value) {
        return getDriver().findElement(By.cssSelector("input[value='" + value + "']")).isSelected();
    }
    public FormFieldsPage selectFromDropdown(String option) {
        Select dDown = new Select(getDriver().findElement(dropDown));
        dDown.selectByVisibleText(option);
        return this;
    }
    public String getDropdownText() {
        Select dDown = new Select(getDriver().findElement(dropDown));
        return dDown.getFirstSelectedOption().getText();
    }
    public FormFieldsPage selectFromRadioButton(String option) {
        click(By.xpath("//input[@value='" + option + "']"));
        return this;
    }
    public boolean radioButtonIsSelected(String option) {
        return getDriver().findElement(By.xpath("//input[@value='" + option + "']")).isSelected();
    }
}
