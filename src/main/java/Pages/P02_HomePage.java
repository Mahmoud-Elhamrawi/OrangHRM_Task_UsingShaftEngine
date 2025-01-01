package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class P02_HomePage {

    SHAFT.GUI.WebDriver driver ;

    //constructor
    public P02_HomePage(SHAFT.GUI.WebDriver driver) {

        this.driver = driver ;
    }

    private final By assertText = By.cssSelector("span[class=\"oxd-topbar-header-breadcrumb\"] h6");
     private final By adminTab = By.xpath("(//li[@class=\"oxd-main-menu-item-wrapper\"])[1]");


    public By assertOnText()
    {
        return assertText ;
    }


    public P03_UserManagment clickOnAdminTAB()
    {
        driver.element().click(adminTab);
        return new P03_UserManagment(driver);
    }












}
