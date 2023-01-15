package com.wipro.utilities;

import com.wipro.pages.*;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class BaseTest extends BasePage{
  protected  HomePage homePage;
  protected SandboxPage sandboxPage;
  protected NavBar navBar;
  protected FormFieldsPage formFieldsPage;
  protected TablesPage tablesPage;
  protected CalendarsPage calendarsPage;
  protected SearchBoxesPage searchBoxesPage;
  protected PopupsPage popupsPage;
  protected HoverPage hoverPage;
  protected FileUploadPage fileUploadPage;
  protected IframesPage iframesPage;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser){
       homePage = new HomePage();
       sandboxPage = new SandboxPage();
       navBar = new NavBar();
       formFieldsPage =new FormFieldsPage();
       tablesPage =new TablesPage();
       calendarsPage = new CalendarsPage();
       searchBoxesPage = new SearchBoxesPage();
       popupsPage = new PopupsPage();
       hoverPage=new HoverPage();
       fileUploadPage=new FileUploadPage();
       iframesPage=new IframesPage();

       assertTrue((goToHomePage (browser)),"An error occurred while navigate to the homepage");
    }

    @AfterMethod
    public void teardown(){
        closeBrowser();
    }

}
