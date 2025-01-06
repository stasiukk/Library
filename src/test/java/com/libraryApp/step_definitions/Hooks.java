package com.libraryApp.step_definitions;

import com.libraryApp.utilities.ConfigurationReader;
import com.libraryApp.utilities.DB_Util;
import com.libraryApp.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {

    @Before
    public void setBaseURI() {
        System.out.println("----- Initializing API Base URI -----");
        // Setting up the Base URI for API requests
        RestAssured.baseURI = ConfigurationReader.getProperty("base_url");
    }

    @After
    public void logScenarioResult(Scenario scenario) {
        // Logs the test result for each scenario
        System.out.println("----- Scenario Completed -----");
        System.out.println("Scenario Name: " + scenario.getName());
        System.out.println("Scenario Status: " + scenario.getStatus());
    }

    @Before("@db")
    public void initializeDatabaseConnection() {
        System.out.println("----- Establishing Database Connection -----");
        // Establishing connection to the database
        DB_Util.createConnection();
    }

    @After("@db")
    public void closeDatabaseConnection() {
        System.out.println("----- Closing Database Connection -----");
        // Closing the database connection after scenario
        DB_Util.destroy();
    }

    @Before("@ui")
    public void setUpUI() {
        System.out.println("----- Setting Up WebDriver for UI Tests -----");
        // Navigating to the application URL
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        // Maximizing the browser window
        Driver.getDriver().manage().window().maximize();
        // Setting implicit wait for WebDriver
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After("@ui")
    public void tearDownUI(Scenario scenario) {
        System.out.println("----- Tearing Down WebDriver for UI Tests -----");
        // Capturing a screenshot if the scenario failed
        if (scenario.isFailed()) {
            System.out.println("----- Capturing Screenshot for Failed Scenario -----");
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Scenario Screenshot");
        }
        // Closing the browser
        Driver.closeDriver();
    }
}
