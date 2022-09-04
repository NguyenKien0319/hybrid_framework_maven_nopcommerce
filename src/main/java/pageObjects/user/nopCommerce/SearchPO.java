package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.SearchPageUI;

public class SearchPO extends BasePage{
	private WebDriver driver;
	
	public SearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToProductByName(String productName) {
		waitForElementClickable(driver, SearchPageUI.DYNAMIC_PROBUCT_BY_NAME, productName);
		clickToElement(driver, SearchPageUI.DYNAMIC_PROBUCT_BY_NAME, productName);
	}

	public void clickToAddToReview() {
		waitForElementClickable(driver, SearchPageUI.ADD_TO_REVIEW_BUTTON);
		clickToElement(driver, SearchPageUI.ADD_TO_REVIEW_BUTTON);
	}

	public boolean isErrorMessageDisplayed(String errorText) {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_ERROR_MESSAGE, errorText);
		return isElementDisplayed(driver, SearchPageUI.DYNAMIC_ERROR_MESSAGE, errorText);
	}
	
}
