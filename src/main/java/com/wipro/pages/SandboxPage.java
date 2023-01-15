package com.wipro.pages;

import com.wipro.utilities.BasePage;
import org.openqa.selenium.By;

import java.util.jar.JarEntry;

public class SandboxPage extends BasePage {
    private By formFields = By.xpath("//a[normalize-space()='Form Fields']");
    private By tables = By.xpath("//a[normalize-space()='Tables']");
    private By calendars = By.xpath("//a[normalize-space()='Calendars']");
    private By searchBoxes =By.xpath("//a[normalize-space()='Search Boxes']");
    private By windowOps =By.xpath("//a[normalize-space()='Window Operations']");
    private By gestures = By .xpath("//a[normalize-space()='Gestures']");
    private By popups = By .xpath("//a[normalize-space()='Popups']");
    private By jsDelays= By .xpath("//a[normalize-space()='JavaScript Delays']");
    private By modals= By .xpath("//a[normalize-space()='Modals']");
    private  By hover= By.xpath("//a[normalize-space()='Hover']");
    private  By spinners= By.xpath("//a[normalize-space()='Spinners']");
    private  By fileupload = By.xpath("//a[normalize-space()='File Upload']");
    private  By iframes = By.xpath("//a[normalize-space()='Iframes']");


    public FormFieldsPage clickFormsFields() {
        click(formFields);
        return new FormFieldsPage();
    }
    public TablesPage clickTables() {
        click(tables);
        return new TablesPage();
    }
    public CalendarsPage clickCalendars() {
        click(calendars);
        return new CalendarsPage();
    }
    public SearchBoxesPage clickSearchBoxes() {
        click(searchBoxes);
        return new SearchBoxesPage();
    }
    public WindowOperationsPage clickWindowOperations() {
        click(windowOps);
        return new WindowOperationsPage();
    }
    public GesturesPage clickGestures() {
        click(gestures);
        return new GesturesPage();
    }
    public  PopupsPage clickPopups(){
        click(popups);
        return new PopupsPage();
    }
    public JavaScriptDelaysPage clickJavaScriptDelays() {
        click(jsDelays);
        return new JavaScriptDelaysPage();
    }
    public ModalsPage clickModals() {
        click(modals);
        return  new ModalsPage();
    }
    public HoverPage clickHover() {
        click(hover);
        return new HoverPage();
    }
    public SpinnersPage clickSpinners() {
        scrollElementIntoView(spinners);
        click(spinners);
        return new SpinnersPage();
    }

    public SandboxPage screenshotTablesButton(){
        takeElementScreenshot(tables);
        return this;
    }
    public FileUploadPage clickFileUpload() {
        click(fileupload);
        return new FileUploadPage();

    }

    public IframesPage clickIframes() {
        click(iframes);
        return new IframesPage();
    }
}