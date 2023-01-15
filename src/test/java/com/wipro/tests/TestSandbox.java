package com.wipro.tests;
import com.wipro.pages.FormFieldsPage;
import com.wipro.pages.PopupsPage;
import com.wipro.utilities.BaseTest;
import com.wipro.utilities.DataUtil;
import com.wipro.utilities.TestListener;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;

import static org.testng.Assert.*;

@Listeners(TestListener.class)
public class TestSandbox extends BaseTest {

    @Test(description = "verify that user can enter text",dataProviderClass = DataUtil.class,dataProvider = "dataProvider1")
    public void testUserInput(HashMap<String,String> hashMap) {
        String name = hashMap.get("name");

        navBar.goToSandboxPage()
                .clickFormsFields()
                .setName(name);

        //Verify that the above entered text done successfully
        String displayedText = formFieldsPage.getName();
        assertEquals(displayedText, name, "Name don't match");
    }

    @Test(description = "verify a user can select or unselect a checkbox")
    public void testCheckbox() {
        navBar.goToSandboxPage()
                .clickFormsFields()
                .clickCheckbox("Water");
        assertTrue(formFieldsPage.checkboxIsSelected("Water"), "check not selected ");


        //unselect Water and select Milk
        formFieldsPage.clickCheckbox("Water")
                .clickCheckbox("Milk");
        assertFalse(formFieldsPage.checkboxIsSelected("Water"), "Checkbox is selected");

    }

    @Test(description = "verify that a user can select from a dropdown manu")
    public void testSelectFromDropdown() {
        String option = "No";
        navBar.goToSandboxPage()
                .clickFormsFields()
                .selectFromDropdown(option);
        assertEquals(formFieldsPage.getDropdownText(), option, "Checkbox is selected");
    }

    @Test(description = "verify a user can select Radio button ")
    public void testSelectRadioButton() {
        String option1 = "Blue";
        String option2 = "Green";
        String option3 = "Red";

        navBar.goToSandboxPage()
                .clickFormsFields()
                .selectFromRadioButton(option1);
        assertTrue(formFieldsPage.radioButtonIsSelected(option1), "RadioButton is not selected");

        //Switch selection for option 2
        formFieldsPage.selectFromRadioButton(option2);
        assertTrue(formFieldsPage.radioButtonIsSelected(option2), "RadioButton is not selected");
        //Negative test(to check option1 is not selected only option2 is selected)
        assertFalse(formFieldsPage.radioButtonIsSelected(option1), "RadioButton is selected");

        // Switch selection for option 3
        formFieldsPage.selectFromRadioButton(option3);
        assertTrue(formFieldsPage.radioButtonIsSelected(option3), "RadioButton is not selected");
        //Negative test (to check only option3 is selected and option1 and option2 not selected )
        assertFalse(formFieldsPage.radioButtonIsSelected(option1), "RadioButton is selected");
        assertFalse(formFieldsPage.radioButtonIsSelected(option2), "RadioButton is selected");

    }

    @Test(description = "verify item price on a table ",dataProviderClass = DataUtil.class,dataProvider = "dataProvider2")
    public void testItemPrice(HashMap<String,String> hashMap) {
        navBar.goToSandboxPage()
                .clickTables();

        String price = tablesPage.getItemPrice("Laptop");
        assertEquals(price, hashMap.get("Laptop"), "Laptop's price is wrong");

        price = tablesPage.getItemPrice("Oranges");
        assertEquals(price, hashMap.get("Oranges"), "Orange's price is wrong");

    }

    @Test(description = "verify that a user can pick a date from a calendar ")
    public void testCalendar() {
        navBar.goToSandboxPage()
                .clickCalendars()
                .setDate("September", "1", "2025");

        String date = calendarsPage.getDate();
        assertEquals(date, "September 1, 2025");
    }

    @Test(description = "verify that a user can search for an article ")
    public void testBlogSearch() {
        boolean searchSuccess;

        navBar.goToSandboxPage()
                .clickSearchBoxes();

        searchSuccess = searchBoxesPage.search("rain");
        assertFalse(searchSuccess, "did not anticipate to find a result");

        searchSuccess = searchBoxesPage.search("jmeter");
        assertTrue(searchSuccess, "did not find any result");
    }

    @Test(description = "verify that a user can switch to another window")
    public void testMultipleWindow() {
        navBar.goToSandboxPage()
                .clickWindowOperations()
                .clickNewWindow()
                .switchToNewWindow();
        closeWindow();

        int numberOfOpenWindows = getNumberOfOpenWindows();
        assertEquals(numberOfOpenWindows, 1, "found more than 1 windows");
    }

    @Test(description = "verify a user can do drag and drop operation on a web element")
    public void testDragAndDrop() {
        navBar.goToSandboxPage()
                .clickGestures()
                .dragAndDropBox(100, 10);
    }

    @Test(description = "verify a user can interact with Popups")
    public void testPopups() {
        String text = "Hello";
        navBar.goToSandboxPage()
                .clickPopups()
                .clickAlertPopup();
        dismissPopup();

        popupsPage.clickConfirmPopup();
        acceptPopup();

        popupsPage.clickPromptPopup();
        // String getText();

        // String promptText= PopupsPage.getText();


    }

    @Test(description = "verify a user can wait for the text to appear")
    public void testCountdownTimer() {
        navBar.goToSandboxPage()
                .clickJavaScriptDelays()
                .clickStart()
                .waitForCountdownText("Liftoff!");


    }

    @Test(description = "verify a user can submit a modal form")
    public void testModalForm() {
        navBar.goToSandboxPage()
                .clickModals()
                .clickSimpleModal()
                .clickFormModal()
                .modalSendMessage("Reetu", "reetu@test.com", "Hey");
    }

    @Test(description = "verify a user can hover an element")
    public void testHovering() {
        navBar.goToSandboxPage()
                .clickHover()
                .hover();

        String hoverText = hoverPage.getHoverText();
        assertEquals(hoverText, "You did it!", "Hover text doesn't match");
    }

    @Test(description = "verify a user can scroll an element into view")
    public void testElementScrolling() {
        navBar.goToSandboxPage()
                .clickSpinners();

    }

    @Test(description = "verify a user can take a page screenshot")
    public void testPageScreenshot() throws IOException {
        navBar.goToSandboxPage();
        takeScreenshot();
        sandboxPage.clickFormsFields().takeScreenshot();

    }

    @Test(description = "verify a user can take an element screenshot")
    public void testElementScreenshot() {
        navBar.goToSandboxPage().screenshotTablesButton();


    }

    @Test(description = "verify a user can upload a file")
    public void testFileUpload() {
        navBar.goToSandboxPage()
                .clickFileUpload()
                .uploadFile("/Users/shyamlal/Sites/index.html");
    }

    @Test(description = "verify a user can work with iFrame")
    public void testIframes() {
        navBar.goToSandboxPage()
                .clickIframes();
        switchFrames(0);
        iframesPage.search("Lion");
        switchToDefaultFrame();
        String pageHeading=iframesPage.getPageHeading();
        assertEquals(pageHeading,"I’m an iframe","Heading didn't match");
    }

    @Test(description = "verify a user can find coundry's population in a list")
    public void testTablePagination() {
        navBar.goToSandboxPage()
                .clickTables()
                .sortByCountry();

        String populationOfTurkey = tablesPage.getPopulation("Turkey");
        assertEquals(populationOfTurkey,"84.3","The country pop is wrong");
    }

    @Test(description = "verify a user can set/clear cookies")
    public void testCookies() {

//        System.out.println("Test Starting...");
//        log.info("Test starting..");
//        log.warn("Test starting...");
//        log.error("Test starting....");
//        log.debug("Test starting.....");

        String cookieName="viewed_cookie_policy";
        setCookie(cookieName,"yes");

        Cookie myCookie= getCookie(cookieName);
        assertEquals(myCookie.getName(),cookieName,"cookie did not set");

        clearCookies();
        myCookie= getCookie(cookieName);
        assertNull(myCookie,"cookies have not been cleared");


    }


}