package actions.editzone.workbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Withdraw extends EditZoneWorkbar {
	private final static String action = "WITHDRAW";
	private final static Logger logger = Logger.getLogger(Withdraw.class);

	public Withdraw(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}
}
