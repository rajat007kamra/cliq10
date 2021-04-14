package actions.pastedata;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.ToolbarActions;
import actions.pastedata.model.PasteDataModel;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class PasteData extends ToolbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private PasteDataModel pasteDataModel;
	private PasteDataHelper pasteDateHelper;
	final static Logger logger = Logger.getLogger(PasteData.class);

	public PasteData(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.pasteDateHelper = new PasteDataHelper(this.driver);
		this.pasteDataModel = new Gson().fromJson(jsonString, PasteDataModel.class);
	}
	
	@Override
	public boolean execute() {
		try {
			clickMoreOptions(this.driver);
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			startTime = Instant.now();
			if (this.pasteDataModel.getAccess() != null && !this.pasteDataModel.getAccess().isEmpty()) {
				checkIconAccess(this.driver, "PASTE DATA", this.pasteDataModel.getAccess().trim().toUpperCase());
			} else {
				verifyPasteData(this.driver);
				clickPasteData();
				pasteDateHelper.setDataInPasteData(this.pasteDataModel.getData());
			}			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "Paste data not found");
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
			Assert.assertEquals(pasteDateHelper.getMessage(), "RECORDS PASTED");
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			pasteDateHelper.verifyFormView();
			PageHelper.waitInSeconds(this.driver, PageHelper.DEFAULT_TIMEOUT_SEC);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Click on PASTE DATA")
	private void clickPasteData() {
		PageHelper.click(this.driver, pasteData);
		logger.info("Paste data icon clicked");
	}
}
