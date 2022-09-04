package com.nopcommerce.login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.register.Register_Account;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.user.nopCommerce.HomePagePO;
import pageObjects.user.nopCommerce.LoginPO;
import pageObjects.user.nopCommerce.PageGeneratorManager;

public class Login_Account extends BaseTest{
	private WebDriver driver;
	private HomePagePO homePage;
	private LoginPO loginPage;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {
		
		
		log.info("Pre-condition: Open browser '" + browserName + "' with URL: '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		
		log.info("Pre-condition - 01: Open Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		log.info("Pre-condition - 02: Navigate to Login Page");
		homePage.clickToHeaderTextLinkByName(driver, "Log in");
	}
	
	@Test
	public void Login_01_Leave_All_Fields_Empty() {
		
		log.info("Login_01 - Step 00: Open LoginPage");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		log.info("Login_01 - Step 01: Click to Login Button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_01 - Step 02: Verify error message displayed");
		verifyTrue(loginPage.displayedErrorMessageByText(driver, "Please enter your email"));
	}
	
	@Test
	public void Login_02_Invalid_Email() {
		
		log.info("Login_02 - Step 01: Refresh Page");
		loginPage.getRefreshPage(driver);
		
		log.info("Login_02 - Step 02: Input valid Email");
		loginPage.enterTextboxByID(driver, "Email", GlobalConstants.INVALID_EMAIL);
		
		log.info("Login_02 - Step 03: Click Login button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_02 - Step 04: Verify error message displayed");
		verifyTrue(loginPage.displayedErrorMessageByText(driver, "Wrong email"));
	}
	
	@Test
	public void Login_03_Email_Not_Registered() {
		
		log.info("Login_03 - Step 01: Refresh Page");
		loginPage.getRefreshPage(driver);
		
		log.info("Login_03 - Step 02: Input Email not registered");
		loginPage.enterTextboxByID(driver, "Email", GlobalConstants.EMAIL_NOT_REGISTERED);
		
		log.info("Login_03 - Step 03: Click Login button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_03 - Step 04: Verify error message displayed");
		verifyEquals(loginPage.displayedErrorValidateSumaryMessage(),"No customer account found");
	}
	
	@Test 
	public void Login_04_Leave_Password_Blank() {
		
		log.info("Login_04 - Step 01: Input valid Email ");
		loginPage.enterTextboxByID(driver, "Email", Register_Account.emailAddress);
		
		log.info("Login_04 - Step 02: Click Login button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_04 - Step 03: Verify error message displayed");
		verifyEquals(loginPage.displayedErrorValidateSumaryMessage(),"The credentials provided are incorrect");
	}
	
	@Test 
	public void Login_05_Input_Incorrect_Password() {
		
		log.info("Login_05 - Step 01: Input valid Email ");
		loginPage.enterTextboxByID(driver, "Email", Register_Account.emailAddress);
		
		log.info("Login_05 - Step 02: Input invalid Password");
		loginPage.enterTextboxByID(driver, "Password", GlobalConstants.INVALID_PASSWORD);
		
		log.info("Login_05 - Step 03: Click Login button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_05 - Step 04: Verify error message displayed");
		verifyEquals(loginPage.displayedErrorValidateSumaryMessage(),"The credentials provided are incorrect");
	}
	
	@Test 
	public void Login_06_Login_Success() {
		
		log.info("Login_06 - Step 01: Input valid Email ");
		loginPage.enterTextboxByID(driver, "Email", Register_Account.emailAddress);
		
		log.info("Login_06 - Step 02: Input valid Password");
		loginPage.enterTextboxByID(driver, "Password", GlobalConstants.PASSWORD);
		
		log.info("Login_06 - Step 03: Click Login button");
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Login_06 - Step 04: Open HomePage");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		log.info("Login_06 - Step 04: Verify Logout button displayed");
		verifyTrue(homePage.isButtonByNameDisplayed("Log out"));
	}
	
	@AfterClass(alwaysRun = true)
	public void AfterClass() {
		closeBrowserAndDriver();
	}
	
}
