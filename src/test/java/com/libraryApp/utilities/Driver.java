package com.libraryApp.utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {


    // Creating a private constructor, we are closing access to the
    // object of this class from outside the class
    private Driver() {
    }

    // We make WebDriver private, because we want to close access from outside of class
    // We make it static, because we will use it inside static method
    //
    //private static WebDriver driver; // value is null by default

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();


    // Create a re-usable utility method which will return same driver instance when we call it
    public static WebDriver getDriver() {

        // it will check if driver is null and if it is we will set up browser inside if statement
        // if you already setup driver and using it again for following line of codes, it will return to same driver
        if (driverPool.get() == null) {
            String browserName = System.getProperty("browser") != null ? browserName = System.getProperty("browser") : ConfigurationReader.getProperty("browser");


            switch (browserName) {

                case "chrome":

                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "firefox":

                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
            }

        }

        return driverPool.get();

    }

    // This method will make sure our driver value is always null after using quit() method
    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit(); // this line will terminate the existing driver session. with using this driver will not be even null
            driverPool.remove();  //driver = null
        }

    }
}