package actions.editzone.workbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import actions.EditZoneActions;
import actions.PageHelper;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class CloseFormView extends EditZoneActions {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(CloseFormView.class);
	
	public CloseFormView(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public CloseFormView(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}
	
	@Override
	public boolean execute() {
		try {
			// Small static wait required to wait for page load initialization
			logger.warn("Static wait introduced");
			PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
			logger.info("Wait for page to load");
			PageHelper.waitForPageLoad(this.driver);
			logger.info("wait for close icon to be present and verify");
			verifyEditZoneClose(this.driver);
			Thread.sleep(5000);
			clickCloseIcon();
			return true;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
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

	@Step("Click close icon")
	public void clickCloseIcon() {
		if (PageHelper.isElementDisplayed(editZoneCloseIcon)) {
            PageHelper.click(this.driver, editZoneCloseIcon);
            logger.info("click on close icon");
        }
        else{
            System.out.println("Clone Popout not Found");
        }
	}
}