package actions.vewizoneactions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Reset extends ViewZoneAction {
	private static String action = "RESET";
	final static Logger logger = Logger.getLogger(Reset.class);

	public Reset(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}
}
