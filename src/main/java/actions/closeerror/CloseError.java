package actions.closeerror;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import actions.Actions;
import actions.PageHelper;
import selenium.driver.Driver;

public class CloseError extends Driver implements Actions {

    private WebDriver driver;
    @FindBy(css = "div.FLY.rslts-wgt div.HOR-FLEX-EL.FLY-CLOSE-TRG[title='CLOSE']")
    private WebElement closeError;

    public CloseError(WebDriver driver, String jsonString) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public boolean execute() {
        try {
            Thread.sleep(10000);
            closewidget();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false, e.getMessage());
            return false;
        }  
    }
    
    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void setup() {
    }

    @Override
    public void tearDown() {
    } 

    public void closewidget() {
        if (PageHelper.isElementDisplayed(closeError)) {
            PageHelper.click(this.driver, closeError);
        }
        else{
            System.out.println("Clone Popout not Found");
        }
    }
}