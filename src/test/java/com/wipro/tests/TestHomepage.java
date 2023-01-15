package com.wipro.tests;

import com.wipro.utilities.BaseTest;
import com.wipro.utilities.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
@Listeners(TestListener.class)

public class TestHomepage extends BaseTest {

    @Test(description = "checks homepage title")
    public void testHomepageTitle(){
        String pageTitle = homePage.getPageTitle();
        assertEquals(pageTitle,"automateNow | Automation Made Easy","Page title mismatched");
    }


    @Test(description = "checks an article's title")
    public void testArticleTitle(){

        //String articleTitle= driver.findElement
        // (By.xpath("//a[normalize-space()='Cypress vs Selenium: Collision of Champions 2022']")).getText();
        //   (By.cssSelector("article[id='post-3500'] header[class='entry-header'] a")).getText();
        //   (By.partialLinkText("Cypress vs Selenium:")).getText();
        // Assert.assertTrue(articleTitle.contains("2022"),"Did not find 2022 in this");
      //(By.xpath("(//h2[normalize-space()='How to work with modals in Selenium'])[1]")).getText();

        String articleTitle = homePage.getArticleTitle();
        assertTrue(articleTitle.contains("2022"),"We Did it");

    }
}
