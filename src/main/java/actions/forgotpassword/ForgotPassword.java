package actions.forgotpassword;

 

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.forgotpassword.model.ForgotPasswordModel;
import selenium.driver.Driver;

 

public class ForgotPassword extends Driver implements Actions {

 

    private WebDriver driver;
    private ForgotPasswordModel forgotModel;

 

    final static Logger logger = Logger.getLogger(ForgotPassword.class);

 

    @FindBy(css = "div.gwt-Label.PASSWORD-LABEL")
    private WebElement forgot;
    @FindBy(css = "input.LOGIN-FIELD-USER")
    private WebElement usernameField;
    @FindBy(css = "input.ENTRYFLD-TXT.ENTRYFLD-TXT-MODAL.ENTRYFLD-TXT-MODAL-DEFAULT")
    private WebElement securityanswer;
    @FindBy(css = "div.MODAL-BUTTON.MODAL-BUTTON-SUBMIT")
    private WebElement submitbutton;
    @FindBy(css = "div.MODAL-FORGOT div.MODAL-MSG")
    private WebElement message;

 

    public ForgotPassword(WebDriver driver, String jsonString) {
        super(driver);
        this.driver = driver;
        this.forgotModel = new Gson().fromJson(jsonString, ForgotPasswordModel.class);
    }

 

    @Override
    public boolean execute() {
        try {
            PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
            forgotpassword(this.forgotModel.getusername(), this.forgotModel.getanswer());
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

 

    public void forgotpassword(String username, String answer) throws InterruptedException {
        if (this.forgotModel.getusername() != null) {
            if (PageHelper.isElementDisplayed(forgot)) {
                PageHelper.sendKeys(this.driver, usernameField, username);
                PageHelper.click(this.driver, forgot);
                logger.info("Click on Forgot Password ");
                PageHelper.sendKeys(this.driver, securityanswer, answer);
                logger.info("Security answer is " + answer);
                PageHelper.click(this.driver, submitbutton);
                Thread.sleep(10000);
                logger.info("Submit button clicked");
                String msg = PageHelper.getText(this.driver, message);
                if (msg.equals("LOGIN FAILED: PLEASE CONTACT YOUR MARSHAL")) {
                    logger.info("LOGIN FAILED: PLEASE CONTACT YOUR MARSHAL");
                } else {
                    logger.info(msg);
                }
            } else {
                logger.info("Forgot Password Buuton Not Found");
            }
        } else {
            logger.info("Either Username is Invalid or Empty");
        }
    }
}
