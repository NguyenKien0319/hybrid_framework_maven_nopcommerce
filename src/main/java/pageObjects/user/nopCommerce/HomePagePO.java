package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.BasePageUI;

public class HomePagePO extends BasePage{
	private WebDriver driver;
	
	public HomePagePO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isButtonByNameDisplayed(String headerName) {
		waitForElementVisible(driver, BasePageUI.HEADER_TEXT_LINK_BY_NAME, headerName);
		return isElementDisplayed(driver, BasePageUI.HEADER_TEXT_LINK_BY_NAME, headerName);
	}
	
}
