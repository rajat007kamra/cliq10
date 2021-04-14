package actions.pastedata;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import actions.PageHelper;
import common.variables.CommonVariables;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class PasteDataHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(PasteDataHelper.class);

	private String pasteTextBox = "div.vz-paste-capture input";
	private String CheckTopMessage = "div.cmplt-noti-dlg-lbl.cmplt-noti-dlg-lbl-top";
	private String formViewTitle = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-HDR-MAIN";

	public PasteDataHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Enter data in PASTE DATA inputbox")
	public void setDataInPasteData(String text) throws InterruptedException {
//		Thread.sleep(10000);
		WebElement inputElem = this.driver.findElement(By.cssSelector(pasteTextBox));
		PageHelper.sendKeys(this.driver, inputElem, text);
		copyAndPasteData();
	}

	@Step("Copy and paste data in PASTE DATA inputbox")
	public void copyAndPasteData() {
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
		builder.keyDown(Keys.CONTROL).sendKeys("x").keyUp(Keys.CONTROL).build().perform();
		builder.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
	}

	@Step("Get message")
	public String getMessage() {
		WebElement checkMessage = this.driver.findElement(By.cssSelector(CheckTopMessage));
		CommonVariables.notificationMsg = PageHelper.getText(this.driver, checkMessage);
		logger.info("Notification message found ::- " + CommonVariables.notificationMsg);
		return CommonVariables.notificationMsg;
	}

	@Step("Verify form view")
	public void verifyFormView() throws InterruptedException {
		PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
		WebElement formView = this.driver.findElement(By.cssSelector(formViewTitle));
		String title = formView.getAttribute("title");
		if (title.contains("(NEW)")) {
			logger.info("Form View opened successfully");
		} else {
			String errorMsg = "Form View not opened yet. Taking time";
			logger.info(errorMsg);
			Assert.fail(errorMsg);
		}
	}
}
