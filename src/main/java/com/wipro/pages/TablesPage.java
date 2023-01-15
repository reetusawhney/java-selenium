package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TablesPage extends BasePage {

    private By countryHeading = By.cssSelector(".column-2.sorting");
    private By noNextBtn = By.cssSelector(".paginate_button.next.disabled");
    private By nextBtn = By.id("tablepress-1_next");

    public String getItemPrice(String item) {
        return getText(By.xpath("//td[text()='" +item + "' ]/following-sibling::td"));
    }

    public TablesPage sortByCountry() {
        click(countryHeading);
        return this;
    }

    public String getPopulation(String country){
        boolean foundCountry = false;

        while (!foundCountry){
            //checks if country is listed on current page
            List<WebElement> countryListedOnCurrentPage =getDriver().findElements
                    (By.xpath("//table[@id='tablepress-1']//td[text()='"+ country + "']"));
            //check if next button is disabled
            List<WebElement> disabledNextBtn =getDriver().findElements(noNextBtn);


            if(!countryListedOnCurrentPage.isEmpty()){
                //execute if we find the country on current page
                foundCountry=true;
            }else if(disabledNextBtn.isEmpty()){
                //execute if we do not find the country on current page
                scrollElementIntoView(nextBtn);
                click(nextBtn);
            }else {
                //execute if we country is not found
                return "-1";
            }
        }
        return getText(By.xpath("//td[text()='"+ country +"']/following-sibling::td"));
    }
}
