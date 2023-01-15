package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

public class SearchBoxesPage extends BasePage {
    private By searchBox = By.id("wp-block-search__input-1");
    private By searchBtn = By.xpath("//button[contains(text(),'Search')]");
    private By noSearchResult = By.cssSelector(".page-title");

    public boolean search(String query) {
        setText(searchBox, query);
        click(searchBtn);

        if (!getDriver().findElements(noSearchResult).isEmpty()) {
            goBack();
            return false;
        }

        return true;
    }
}
