package actions.save;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.WorkbarActions;
import actions.save.model.SaveModel;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Save extends WorkbarActions {
	private Instant startTime;
	private Instant endTime;
    private WebDriver driver;
    private SaveModel saveModel;
	final static Logger logger = Logger.getLogger(Save.class);

    public Save(WebDriver driver, String jsonString) {
        super(driver);
        this.driver = driver;
        this.saveModel = new Gson().fromJson(jsonString, SaveModel.class);
    }
    
    @Override
    public boolean execute() {
    	try {
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			startTime = Instant.now();
			if (this.saveModel.getAccess() != null && !this.saveModel.getAccess().isEmpty()) {
				checkIconAccess(this.driver, "SAVE", this.saveModel.getAccess().trim().toUpperCase(), saveIcon);
			} else {
				verifySave(this.driver);
				clickSave();
			}			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
    }

	@Override
	public boolean validate() {
		try {
			checkIfIconIsDisabled(this.driver, "SAVE");
			logger.info("SAVE icon validated successfully");
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

    @Override
    public void setup() {

    }

    @Override
    public void tearDown() {

    }

    @Step("Click SAVE icon")
    private void clickSave() {
    	PageHelper.click(this.driver, saveIcon);
    	logger.info("Saved icon clicked");
    }
}