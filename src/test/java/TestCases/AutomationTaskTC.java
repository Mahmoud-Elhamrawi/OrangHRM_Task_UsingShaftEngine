package TestCases;

import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Pages.P03_UserManagment;
import Pages.P04_saveSysytemUser;
import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationTaskTC {
    SHAFT.GUI.WebDriver driver ;
    SHAFT.TestData.JSON tData;
    SHAFT.TestData.JSON tData2;


    @BeforeMethod
    public void setUp()
    {

        driver = new SHAFT.GUI.WebDriver();
        tData = new SHAFT.TestData.JSON(SHAFT.Properties.paths.testData()+"loginData.json");
        tData2 = new SHAFT.TestData.JSON(SHAFT.Properties.paths.testData()+"dataEmp.json");
        driver.browser().navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    }


    @Test
    public void assessmentTC() throws InterruptedException {

          //TODO:: login
        new P01_LoginPage(driver)
              .login(tData.getTestData("name"),tData.getTestData("password"));

         driver.assertThat().browser().url().isEqualTo("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index").perform();
         driver.assertThat().element(new P02_HomePage(driver).assertOnText()).text().isEqualTo("Dashboard").perform();

         //TODO::go to admin page  from home page
          new P02_HomePage(driver)
                  .clickOnAdminTAB()
                          .getCurrentNumberRecords();  // 10
        driver.assertThat().browser().url().isEqualTo("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers").perform();

        //TODO:: click on add button
        new P03_UserManagment(driver).navigateToAddingUserPage();
        driver.assertThat().browser().url().isEqualTo("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser").perform();

        //TODO:: fill new emp
          new P04_saveSysytemUser(driver)
                  .enterNewEmp(tData2.getTestData("userName"),tData2.getTestData("password"),tData2.getTestData("cPassword"))
                          .assertOnMsgAddExists();
          new P03_UserManagment(driver)
                  .getNumberRecordsAfterAdd();  //11


        driver.assertThat().browser().url().isEqualTo("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers").perform();

        //TODO::assert on number of records after added
          new P03_UserManagment(driver).assertOnDifferenceAfterAdded();

          //TODO:: search for new user
        new P03_UserManagment(driver)
                .searchForUserAdded(tData2.getTestData("userName"))
                .assertOnUserThatAddedFound()
                .deleteAddedUser()
                .assertOnMsgDeleteExists()
                .assertOnMsgDeleteContent();

        driver.browser().refreshCurrentPage();

new  P03_UserManagment(driver).assertOnDifferenceAfterDeleting();





    }


    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

}
