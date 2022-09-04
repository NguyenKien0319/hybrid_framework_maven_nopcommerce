package com.nopcommerce.search;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nomcommerce.common.Common_01_Login;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePagePO;
import pageObjects.user.nopCommerce.LoginPO;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.SearchPO;

public class Search_AdvancedSearch extends BaseTest{
	private WebDriver driver;
	HomePagePO homePage;
	LoginPO loginPage;
	SearchPO searchPage;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {
		
		log.info("Pre-condition: Open browser '" + browserName + "' with URL: '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		
		log.info("Pre-condition - 01: Open Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		log.info("Pre-condition - 02: Navigate to Login Page");
		homePage.clickToHeaderTextLinkByName(driver, "Log in");
		
		log.info("Pre-condition - 03: Open LoginPage");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		log.info("Pre-condition - 04: Set Login page cookie");
		loginPage.setAllCookies(driver, Common_01_Login.loginCookiePage);
		sleepInSecond(1);
		loginPage.getRefreshPage(driver);
		
		log.info("Pre-condition - 05: Open Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		log.info("Pre-condition - 06: Verify Logout button displayed");
		verifyTrue(homePage.isButtonByNameDisplayed("Log out"));
		
		homePage.clickToFooterByText(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		searchPage.isPageTitleDisplayed(driver, "Search");	
	}
	
	@Test
	public void Search_Advanced_Search_01_Search_With_Empty() {
		searchPage.clickToButtonByName(driver, "Search");
		verifyTrue(searchPage.isErrorMessageDisplayed("Search term minimum length is 3 characters"));
	}
	
	@Test
	public void Search_Advanced_Search_02_Data_Not_Exist() {
		searchPage.enterTextboxByID(driver, "q", "Macbook Pro 2050");
		searchPage.clickToButtonByName(driver, "Search");
		verifyTrue(searchPage.isErrorMessageDisplayed("No products were found that matched your criteria."));
	}
	
	/*
	 * @Test public void Search_Advanced_Search_03_Search_With_ProductName() {
	 * searchPage.enterTextboxByID(driver,"q", "Lenovo");
	 * searchPage.clickToButtonByName(driver, "Search"); }
	 */
}
