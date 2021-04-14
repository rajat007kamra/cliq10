package actions.addapprover;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.addapprover.model.AddApproverModel;
import actions.forgotpassword.model.ForgotPasswordModel;
import selenium.driver.Driver;

public class AddApprover extends Driver implements Actions {

	private WebDriver driver;
	private AddApproverModel addModel;

	final static Logger logger = Logger.getLogger(AddApprover.class);

	@FindBy(css = "div.FLY-TOP-TRG.TRG-BASE.trg-base.material-icons.FLY-TOP-TRG-SRC-ADD")
	private WebElement addbutton;

	public AddApprover(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.addModel = new Gson().fromJson(jsonString, AddApproverModel.class);
	}

	@Override
	public boolean execute() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
			addnew();
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

	public boolean addnew() throws InterruptedException {
		if (PageHelper.isElementDisplayed(addbutton)) {
			logger.info("Click on Add button ");
			return true;
		} else {
			logger.info("Add Button Not Found");
			Assert.assertTrue(false, "Button Not Found");
			return false;
		}
	}
}
