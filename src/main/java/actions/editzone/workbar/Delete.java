package actions.editzone.workbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Delete extends EditZoneWorkbar {
	private static String action = "DELETE";
	private final static Logger logger = Logger.getLogger(Delete.class);

	public Delete(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}
}
