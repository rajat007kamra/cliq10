package actions.locksortorder;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.google.gson.Gson;

import actions.Actions;
import actions.locksortorder.model.LockSortOrderModel;
import junit.framework.Assert;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class LockSortOrder extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private LockSortOrderModel lockSortModel;
	private LockSortOrderHelper lockSortHelper;
	final static Logger logger = Logger.getLogger(LockSortOrder.class);

	public LockSortOrder(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.lockSortHelper = new LockSortOrderHelper(this.driver);
		this.lockSortModel = new Gson().fromJson(jsonString, LockSortOrderModel.class);
	}
	
	@Override
	public boolean execute() {
		try {
			Thread.sleep(5000);
			startTime = Instant.now();
			lockSortHelper.lockColumnsSort(this.lockSortModel.getColumn());
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
            Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			Assert.assertEquals(Integer.parseInt(this.lockSortModel.getTotal()), lockSortHelper.getlockedColumns());
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
            Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public void setup() {
		
	}

	@Override
	public void tearDown() {
		
	}
}