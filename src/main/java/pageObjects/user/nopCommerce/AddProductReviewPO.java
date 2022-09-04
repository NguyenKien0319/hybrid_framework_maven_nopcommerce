package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.AddProductReviewUI;

public class AddProductReviewPO extends BasePage{
	private WebDriver driver;
	
	public AddProductReviewPO(WebDriver driver) {
		this.driver = driver;
	}

	public String isAddProductReviewSuccessDisplayed() {
		waitForElementVisible(driver, AddProductReviewUI.MESSAGE_ADD_PRODUCT_SUCCESS);
		return getTextElement(driver, AddProductReviewUI.MESSAGE_ADD_PRODUCT_SUCCESS);
	}

	
}
