package com.nttdata.steps;
import com.nttdata.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreStep {

    private WebDriver driv;

    public StoreStep(WebDriver driv){
        this.driv = driv;
    }

    public void typeUser(String user){
        WebElement userInputElement = driv.findElement(LoginPage.userInput);
        userInputElement.sendKeys(user);
        driv.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebDriverWait wait = new WebDriverWait(driv, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginButton));

    }
    public void typePassword(String password){

        this.driv.findElement(LoginPage.passInput).sendKeys(password);
    }
    public void login(){
        this.driv.findElement(LoginPage.loginButton).click();
    }

}
