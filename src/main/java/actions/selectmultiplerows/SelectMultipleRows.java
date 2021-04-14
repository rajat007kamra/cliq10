package actions.selectmultiplerows;

import java.time.Instant;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import actions.Actions;
import selenium.driver.Driver;

public class SelectMultipleRows extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private SelectMultipleRowsHelper rowHelper;
	
	public SelectMultipleRows(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.rowHelper = new SelectMultipleRowsHelper(this.driver);
	}

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			rowHelper.selectMultipleRows();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		endTime = Instant.now();
		//logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
		return false;
	}

	@Override
	public void setup() {
		
	}

	@Override
	public void tearDown() {
		
	}
}