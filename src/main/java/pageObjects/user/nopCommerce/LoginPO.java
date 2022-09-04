package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.LoginPageUI;

public class LoginPO extends BasePage{
	private WebDriver driver;
	
	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public String displayedErrorValidateSumaryMessage() {
		waitForElementVisible(driver, LoginPageUI.ERROR_VALIDATION_SUMARY_MESSAGE);
		return getTextElement(driver, LoginPageUI.ERROR_VALIDATION_SUMARY_MESSAGE);
	}
}
