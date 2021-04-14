package actions.editzone.workbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SurendraShekhawat
 *
 */
public class Approve extends EditZoneWorkbar {
	private final static String action = "APPROVE";
	private final static String popUpMessage = "APPROVED!";
	private final static Logger logger = Logger.getLogger(Approve.class);

	public Approve(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}

	@Override
	public boolean validate() {
		return validatePopUpMessage(popUpMessage, action);
	}

}
