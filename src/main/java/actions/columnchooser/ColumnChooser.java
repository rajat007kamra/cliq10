package actions.columnchooser;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import actions.PageHelper;
import actions.RealmActions;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import junit.framework.Assert;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class ColumnChooser extends RealmActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(ColumnChooser.class);

	public ColumnChooser(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "div.CHOOSER-WGT-TITLE div.gwt-Label")
	private WebElement columnsListHeader;

	@Override
	public boolean execute() {
		try {
			verifyColumnChooser(this.driver);
			logger.info("click on column chooser");
			Thread.sleep(5000);
			startTime = Instant.now();
			clickColumnChooser();
			CommonVariables.actionTime = System.currentTimeMillis();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			Assert.assertEquals(validateColumnChooser().trim(), "VARIABLES");
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Click COLUMN CHOOSER icon")
	private void clickColumnChooser() {
		PageHelper.click(this.driver, columnsChooserIcon);
	}

	@Step("verify COLUMN CHOOSER icon")
	private String validateColumnChooser() {
		logger.info("Columns chooser list header found ::- " + PageHelper.getText(this.driver, columnsListHeader));
		return PageHelper.getText(this.driver, columnsListHeader);
	}
}
