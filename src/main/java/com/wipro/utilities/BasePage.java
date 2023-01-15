package com.wipro.utilities;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;

public class BasePage {
    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static Logger log = LogManager.getLogger();
    public String baseURL;
    public Properties properties;

    private synchronized void loadProperties() {
        FileInputStream fis = null;

        try {
            properties = new Properties();
            fis = new FileInputStream("src/main/java/com/wipro/config/config.properties");
            properties.load(fis);


            baseURL = properties.getProperty("baseURL");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static WebDriver getDriver() {return driver.get();} // here we are getting the copy
    private void openBrowser(String browser) {

        switch (browser.toLowerCase()){
            case "chrome":
            ChromeOptions options = new ChromeOptions();
//          options.addArguments("--headless");
//          options.addArguments("--window-size=1920, 1080");
//          driver = new ChromeDriver(options); // with option
            driver.set(ThreadGuard.protect(new ChromeDriver())); //without options //here we are setting the copy
                try {
                    driver.set(ThreadGuard.protect(new RemoteWebDriver(new URL("http://localhost:4444/"), options)));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "safari" :
                SafariOptions safariOptions= new SafariOptions();
                try {
                    driver.set(ThreadGuard.protect(new RemoteWebDriver(new URL("http://localhost:4444/"), safariOptions)));
                } catch (MalformedURLException e) {

                }
                break;

            default:
                log.error("Invalid browser property " +browser );
        }
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //Implicit wait
        getDriver().manage().window().maximize();
    }

    public void closeBrowser() {
        getDriver().quit();
        driver.remove(); // here we used driver instead of getDriver.referring to ThreadLocal
    }
    public boolean goToHomePage(String browser) {
        try {
            loadProperties();
            openBrowser(browser);
            getDriver().get(baseURL);
        } catch (Exception e) {
            System.out.println("unable to navigate to the homepage");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void click(By locator) {
        getDriver().findElement(locator).click();
    }

    public void setText(By locator, String text) {
        getDriver().findElement(locator).clear();
        getDriver().findElement(locator).sendKeys(text);
        tab(locator);
    }

    public String getText(By locator) {
        String text = getDriver().findElement(locator).getText();
        if (text.isEmpty()) {
            return getDriver().findElement(locator).getAttribute("value");
        } else {
            return text;
        }
    }

    private void tab(By locator) {
        getDriver().findElement(locator).sendKeys(Keys.TAB);
    }

    public void goBack() {
        getDriver().navigate().back();
    }

    public void closeWindow() {
        getDriver().close();
    }

    public int getNumberOfOpenWindows() {
        return getDriver().getWindowHandles().size();
    }

    public void dragAndDrop(By locator, int x, int y) {
        WebElement element = getDriver().findElement(locator);
        Actions actions = new Actions(getDriver());
        actions.dragAndDropBy(element, x, y).perform();
    }

    public void dismissPopup() {
        getDriver().switchTo().alert().dismiss();
    }

    public void acceptPopup() {
        getDriver().switchTo().alert().accept();
    }

//    public String promptPopup(){
//        driver.switchTo().alert().getText();
//        return null;
//    }

    public void waitForElementValue(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(12));
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }

    public void waitForElementText(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds((3)));
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }

    public void hoverElement(By locator) {
        WebElement element = getDriver().findElement(locator);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }

    public void scrollElementIntoView(By locator) {
        WebElement element = getDriver().findElement(locator);
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].scrollIntoView();", element);

        //Selenium 4 update: scroll into view(scroll to bottom of page)(good if no blocker on bottom)
//        new Actions(driver)
//                .scrollToElement(element)
//                .perform();

        //Selenium 4 update: scroll into by amount(recommended)
        int deltaY = element.getRect().y;
        new Actions(getDriver())
                .scrollByAmount(0, deltaY)
                .perform();

    }

    public void takeScreenshot() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH-mm-SSS");
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            takeScreenshot(localDateTime.format(formatter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void takeScreenshot(String name) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)getDriver();
        File file = screenshot.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(file, new File("./screenshotF/" + name + " .png"));
    }

    public void takeElementScreenshot(By locator) {
        WebElement element = getDriver().findElement(locator);
        File file = element.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(file, new File("./screenshot/table.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void switchToDefaultFrame() {
        getDriver().switchTo().defaultContent();
    }
    public void switchFrames(int frame) {
        getDriver().switchTo().frame(frame);
    }
    private void refresh() {
        getDriver().navigate().refresh();
    }
    public void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        getDriver().manage().addCookie(cookie);
        refresh();

    }
    public Cookie getCookie(String name) {
        return getDriver().manage().getCookieNamed(name);
    }
    public void clearCookies() {
        getDriver().manage().deleteAllCookies();
        refresh();
    }
}

