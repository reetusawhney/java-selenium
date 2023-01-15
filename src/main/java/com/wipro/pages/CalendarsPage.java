package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

public class CalendarsPage extends BasePage {
    private By calendarField = By.id("g1065-selectorenteradate");
    private By calendarMonth = By.cssSelector(".ui-datepicker-month");
    private  By calendarYear = By.cssSelector(".ui-datepicker-year");
    private  By calendarRightArrow = By.cssSelector("a[title='Next']");

    public CalendarsPage setDate(String month, String day, String year) {
        String currentMonth;
        String currentYear;
        click(calendarField);

        while (true){
            currentMonth =getText(calendarMonth);
            currentYear = getText(calendarYear);

            if (currentMonth.equals(month) && currentYear.equals(year)){
                break;
            }
            click(calendarRightArrow);
        }

        click(By.xpath("//a[normalize-space()='" + day + "']"));
        return this;
    }

    public String getDate() {
        return getText(calendarField);
    }
}
