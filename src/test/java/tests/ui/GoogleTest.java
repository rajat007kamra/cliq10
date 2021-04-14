package tests.ui;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class GoogleTest extends Base {

	@Test(priority = 1)
	public static void searchOnGoogle() throws InterruptedException, AWTException {
		actions.PageHelper.maximizeWindow();
		String searchText = "Automation Testing";
		WebElement searchBox = Driver.getDriver().findElement(By.name("q"));
		searchBox.sendKeys(searchText);
		actions.PageHelper.sendKeys(Driver.getDriver(), searchBox, searchText);
		System.out.println("SEARCH TEXT ON GOOGLE IS ::- " + searchText);

		searchBox.sendKeys(Keys.ENTER);
		System.out.println("GOOGLE SEARCH BUTTON CLICKED SUCCESSFULLY");
	}
}
