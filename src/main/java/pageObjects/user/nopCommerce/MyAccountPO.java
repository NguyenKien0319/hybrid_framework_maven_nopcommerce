package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.BasePageUI;
import pageUIs.user.nopCommerce.MyAccountPageUI;

public class MyAccountPO extends BasePage {
	private WebDriver driver;
	
	public MyAccountPO(WebDriver driver) {
		this.driver = driver;
	}

	public String isUpdatedTextBoxById(String ID, String attributeName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, ID);
		return getElementAttributeValue(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, attributeName, ID);
	}

	public String isUpdatedDateOfBirth(String dateOfBirth) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_DATEOFBIRTH, dateOfBirth);
		return getDropDownItem(driver, BasePageUI.DYNAMIC_DATEOFBIRTH, dateOfBirth);
	}

	public Boolean isUpdatedAddressByName(String param1, String param2 ) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_INFO_BY_TEXT, param1, param2);
		return isElementDisplayed(driver, MyAccountPageUI.DYNAMIC_INFO_BY_TEXT,  param1, param2);
	}

	public void clickToInfoButton(String value) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_INFO_BUTTON, value);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_INFO_BUTTON, value);
	}

	public void clickToAddButton() {
		waitForElementClickable(driver, MyAccountPageUI.ADD_BUTTON);
		clickToElement(driver, MyAccountPageUI.ADD_BUTTON);
	}

	public boolean isDisplayNoAddresstext() {
		waitForElementVisible(driver, MyAccountPageUI.NO_ADDRESS_BUTTON);
		return isElementDisplayed(driver, MyAccountPageUI.NO_ADDRESS_BUTTON);
	}

	public boolean isMyProductReviewDisplayed(String productName, String content, String title, String rating) {
		waitForElementVisible(driver,MyAccountPageUI.MYACCOUNT_REVIEW_INFO,productName, content, title, rating);
		return isElementDisplayed(driver,MyAccountPageUI.MYACCOUNT_REVIEW_INFO,productName, content, title, rating);
	}
}
