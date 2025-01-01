package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P04_saveSysytemUser {

    SHAFT.GUI.WebDriver driver ;

    //constructor
    public P04_saveSysytemUser(SHAFT.GUI.WebDriver driver)
    {
        this.driver = driver ;
    }
    //Locators
    private final By userRoleSelect = By.xpath("(//div[@class=\"oxd-select-text--after\"])[1] ");
    private final By userRoleAdmin = By.xpath("(//div[@role=\"option\"])[2]");


     private final By statusSelect = By.xpath("(//div[@class=\"oxd-select-text--after\"])[2] ");
     private final By statusEnabled = By.xpath("(//div[@class=\"oxd-select-option\"])[2]");

      private final By empName = By.cssSelector("p[class=\"oxd-userdropdown-name\"]");
      private final By empNameInp = By.cssSelector("div[class=\"oxd-autocomplete-text-input oxd-autocomplete-text-input--active\"] input");
      private final By empOption = By.cssSelector("div[class=\"oxd-autocomplete-option\"] ");


      private final By userNameInp= By.xpath("(//input[@class=\"oxd-input oxd-input--active\"])[2]");


    private final By passwordInp = By.xpath("(//input[@type=\"password\"])[1]");

    private final By confirmPasswordInp = By.xpath("(//input[@type=\"password\"])[2]");


      private final By saveBtn = By.cssSelector("button[type=\"submit\"]");



      //Actions
    public P03_UserManagment enterNewEmp(String userName , String pass , String cPass) {
        driver.element().click(userRoleSelect).and().click(userRoleAdmin)
                .and().type(empNameInp,driver.element().getText(empName));
        //using wait in selenium native
        new WebDriverWait(driver.getDriver(),Duration.ofSeconds(60)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(empOption));
        new WebDriverWait((driver.getDriver()),Duration.ofSeconds(30)).until(d->driver.element().waitUntilPresenceOfAllElementsLocatedBy(empOption));
        driver.element().click(empOption)
                .and().click(statusSelect).and().click(statusEnabled)
                .and().element().type(userNameInp,userName)
                .and().type(passwordInp,pass)
                .and().type(confirmPasswordInp,cPass)
                .and().click(saveBtn);

            driver.browser().captureScreenshot();


        return new P03_UserManagment(driver);

    }










}
