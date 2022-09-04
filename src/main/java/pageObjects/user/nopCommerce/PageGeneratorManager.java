package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	private static HomePagePO homePage;
	private static RegisterPO registerPage;
	private static LoginPO loginPage;
	private static MyAccountPO myaccountPage;
	private static SearchPO searchPage;
	private static AddProductReviewPO addProductReviewPage;

	public PageGeneratorManager() {

	}

	public static HomePagePO getHomePage(WebDriver driver) {
		homePage = new HomePagePO(driver);
		return homePage;
	}

	public static RegisterPO getRegisterPage(WebDriver driver) {
		registerPage = new RegisterPO(driver);
		return registerPage;
	}

	public static LoginPO getLoginPage(WebDriver driver) {
		loginPage = new LoginPO(driver);
		return loginPage;
	}

	public static MyAccountPO getMyAccountPage(WebDriver driver) {
		myaccountPage = new MyAccountPO(driver);
		return myaccountPage;
	}

	public static SearchPO getSearchPage(WebDriver driver) {
		searchPage = new SearchPO(driver);
		return searchPage;
	}

	public static AddProductReviewPO getAddProductReviewPage(WebDriver driver) {
		addProductReviewPage = new AddProductReviewPO(driver);
		return addProductReviewPage;
	}

}
