package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

public class GesturesPage extends BasePage {
  //  private By map= By.cssSelector("canvas[aria-label='Map']");
     private By box= By.id("moveMeHeader");

    public GesturesPage dragAndDropBox (int x_axis, int y_axis) {
      // dragAndDrop(map, x_axis, y_axis);
       dragAndDrop(box, x_axis, y_axis);
       return this;
   }
}
