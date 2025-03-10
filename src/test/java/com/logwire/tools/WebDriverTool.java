package com.logwire.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions; 
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;



public class WebDriverTool {

    static public WebDriver driver;

    static public void setUpDriver(){
        String browser = System.getProperty("browser","chrome");
        String node = System.getProperty("node", "http://192.168.1.28:4444/wd/hub");
        
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                
                try {
                driver = new RemoteWebDriver(new URL(node), chromeOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("[SETUP] URL du Selenium Grid invalide !");
                }
                break;


            case "firefoxe":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                try {
                driver = new RemoteWebDriver(new URL(node), firefoxOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("[SETUP] URL du Selenium Grid invalide !");
                }
                break;
                
            default:
                driver = new ChromeDriver();
                break;

        }
        //driver.manage().window().maximize();
        
    }

    static public WebDriver getDriver(){
        return driver;
    }

    static public void tearDown(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
