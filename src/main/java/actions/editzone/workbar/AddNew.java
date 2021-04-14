package actions.editzone.workbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class AddNew extends EditZoneWorkbar {
	private static String action = "ADD NEW";
	final static Logger logger = Logger.getLogger(AddNew.class);

	public AddNew(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}
}
