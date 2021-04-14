package actions.populatehorizontalsharing;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.populatehorizontalsharing.model.Column;
import actions.populatehorizontalsharing.model.PopulateHorizontalSharingModel;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class PopulateHorizontalSharing extends Driver implements Actions {
	private WebDriver driver;
	private PopulateHorizontalSharingHelper userRightsHelper;
	private PopulateHorizontalSharingModel userRightsModel;

	final static Logger logger = Logger.getLogger(PopulateHorizontalSharing.class);

	public PopulateHorizontalSharing(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.userRightsHelper = new PopulateHorizontalSharingHelper(this.driver);
		this.userRightsModel = new Gson().fromJson(jsonString, PopulateHorizontalSharingModel.class);
	}

	public PopulateHorizontalSharing(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.userRightsHelper = new PopulateHorizontalSharingHelper(this.driver);
		this.userRightsModel = new PopulateHorizontalSharingModel();
		this.userRightsModel.setColumn(columns);
	}

	@Override
	public boolean execute() {
		try {
			List<Column> columns = this.userRightsModel.getColumn();
			for (Column column : columns) {
				String user = column.getUser();
				String access = column.getRights();
				logger.info("Set user '" +user +"' and right '" +access +"'");
				userRightsHelper.findTableAndEnter(user, access);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
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
}
