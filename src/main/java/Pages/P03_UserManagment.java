package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P03_UserManagment {

    SHAFT.GUI.WebDriver driver ;
    public int records  ;

    //constructor
    public P03_UserManagment(SHAFT.GUI.WebDriver driver) {
        this.driver = driver ;
    }


    //Locators
    private final By numberOfRecords = By.xpath("//div[contains(@class,\"orangehrm-horizontal-padding\")] /span");
    private final By addBtn = By.cssSelector("div[class=\"orangehrm-header-container\"] button");
    private final By msgBody = By.id("oxd-toaster_1");
    private final By assertOnMsg =By.xpath("//p[contains(@class,'oxd-text--toast-message')]");
private final By searchInp = By.xpath("(//label[.='Username']//following::input)[1]");
private final By searchBtn = By.cssSelector("button[type=\"submit\"]");
private final By deleteBtn = By.cssSelector("div[class=\"oxd-table-cell-actions\"] button:nth-of-type(1)");
private final By confirmDevAppear = By.xpath("//div[contains(@class,'orangehrm-dialog-popup')]");
private final By confirmDeletingBtn = By.xpath("//button[contains(@class,'oxd-button--label-danger')] ");
    private final By msgBodyDelete = By.xpath("//div[contains(@class,'oxd-toast--success')]");
    private final By msgContentDelete =By.xpath("//p[contains(@class,'oxd-text--toast-message')]");


    //Actions
    public int getNumberOfRecords()
    {
        // (35) Records Found
        records = Integer.parseInt(driver.element().getText(numberOfRecords).replaceAll("[^0-9]",""));
        return records;
    }
   public static int currentNumber  ,  numberAfterAdd ;

    public P03_UserManagment getCurrentNumberRecords()
    {
        currentNumber = getNumberOfRecords();
        System.out.println("current : "+currentNumber);  //5
        return this;
    }

    public P03_UserManagment getNumberRecordsAfterAdd()
    {
        numberAfterAdd = getNumberOfRecords();
        System.out.println("after : "+numberAfterAdd); //6

        return this;
    }

    public P03_UserManagment assertOnDifferenceAfterAdded()
    {
        SHAFT.Validations.assertThat().object(numberAfterAdd).isEqualTo(currentNumber+1).perform();
        return this;
    }


     public P04_saveSysytemUser  navigateToAddingUserPage()
     {
      driver.element().click(addBtn);

         return new P04_saveSysytemUser(driver);
     }

    public void assertOnMsgAddExists()
    {
        driver.assertThat().element(msgBody).exists().perform();

    }

    
    public P03_UserManagment searchForUserAdded(String userName)
    {
        driver.element().type(searchInp,userName);
        driver.element().click(searchBtn);
        return this;
    }


    public P03_UserManagment assertOnUserThatAddedFound()
    {
        SHAFT.Validations.assertThat().number(new P03_UserManagment(driver).getNumberOfRecords()).isEqualTo(1).perform();
        return this;
    }

    public P03_UserManagment deleteAddedUser()
    {
        driver.element().click(deleteBtn);
        //wait
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(10)).until(d->driver.element().waitUntilPresenceOfAllElementsLocatedBy(confirmDevAppear));
        driver.element().click(confirmDeletingBtn);
        return this;
    }



    public P03_UserManagment assertOnMsgDeleteExists()
    {
        driver.assertThat().element(msgBodyDelete).exists().perform();
        return this;
    }

    public P03_UserManagment assertOnMsgDeleteContent()
    {
        driver.assertThat().element(msgContentDelete).exists().perform();
        return this;
    }





    public P03_UserManagment assertOnDifferenceAfterDeleting()
    {
        SHAFT.Validations.assertThat().object(currentNumber).isEqualTo(currentNumber).perform();
        return this;
    }


}
