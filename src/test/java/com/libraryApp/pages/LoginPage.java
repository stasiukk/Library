package com.libraryApp.pages;

import com.libraryApp.utilities.BrowserUtil;
import com.libraryApp.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "inputEmail")
    public WebElement emailBox;

    @FindBy(id = "inputPassword")
    public WebElement passwordBox;

    @FindBy(tagName = "button")
    public WebElement loginButton;





    public void login(String userType){

        String username=System.getenv("EMAIL");
        String password=System.getenv("PASSWORD");



        emailBox.sendKeys(username);
        passwordBox.sendKeys(password);
        loginButton.click();
        BrowserUtil.waitFor(2);

    }

    public void login(String email, String password){

        emailBox.sendKeys(email);
        passwordBox.sendKeys(password);
        loginButton.click();
        BrowserUtil.waitFor(2);

    }







}
