package actions.populateverticalsharing;

import org.openqa.selenium.WebDriver;

import actions.Actions;
import selenium.driver.Driver;


/**
 * 
 * @author Arun.Kapoor
 *
 */

public class PopulateVerticalSharing extends Driver implements Actions {

	public PopulateVerticalSharing(WebDriver driver, String jsonString) {
		super(driver);
	}

	@Override
	public boolean execute() {
//		VERTICAL SHARING FIELDS ARE DISABLED YET
//		TO DO
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
