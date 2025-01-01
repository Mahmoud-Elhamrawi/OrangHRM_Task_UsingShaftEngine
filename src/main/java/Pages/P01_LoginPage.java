package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class P01_LoginPage {


    SHAFT.GUI.WebDriver driver ;

    //constructor
    public P01_LoginPage(SHAFT.GUI.WebDriver driver)
    {
        this.driver=driver ;
    }

    private final By userInp = By.cssSelector("input[name=\"username\"]");
    private final By passInp = By.cssSelector("input[name=\"password\"]");
    private final By loginBtn = By.cssSelector("button[type=\"submit\"]");


    public P02_HomePage login(String name , String password)
    {
        driver.element().type(userInp,name)
                .and().type(passInp,password)
                .and().click(loginBtn);
        return new P02_HomePage(driver);
    }





}
