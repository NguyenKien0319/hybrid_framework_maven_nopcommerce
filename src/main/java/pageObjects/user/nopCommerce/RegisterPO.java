package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.RegisterPageUI;

public class RegisterPO extends BasePage{
	private WebDriver driver;
	
	public RegisterPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public String errorMessageInvalidPassword() {
		waitForElementVisible(driver, RegisterPageUI.INVALID_PASSWORD_MESSAGE);
		return getTextElement(driver, RegisterPageUI.INVALID_PASSWORD_MESSAGE);
	}
	
	public boolean isRegisterSuccessMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}
	
	public String errorMessageEmailExist() {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_EMAIL_EXIST);
		return getTextElement(driver, RegisterPageUI.ERROR_MESSAGE_EMAIL_EXIST);
	}
}
