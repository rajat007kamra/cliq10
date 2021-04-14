package actions.editzone.workbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Save extends EditZoneWorkbar {
	private static String action = "SAVE";
	private final static Logger logger = Logger.getLogger(Save.class);

	public Save(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}
}
