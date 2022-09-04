package com.nomcommerce.common;

import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.user.nopCommerce.HomePagePO;
import pageObjects.user.nopCommerce.LoginPO;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPO;

public class Common_01_Login extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName;
	private HomePagePO homePage;
	private RegisterPO registerPage;
	private LoginPO loginPage;
	public static String emailAddress = getRandomEmail();
	public static Set<Cookie> loginCookiePage;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void BeforeTest(String browserName, String appUrl) {
		firstName = "Automation";
		lastName = "Testing";

		log.info("Pre-condition: Open browser '" + browserName + "' with URL: '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		log.info("Pre-condition - 01: Open Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Pre-condition - 02: Navigate to RegisterPage");
		homePage.clickToHeaderTextLinkByName(driver, "Register");

		log.info("Pre-condition - 03: Open Register Page");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		log.info("User_01_Login - Step_02: Select Radio button: 'Male'");
		registerPage.selectRadioButtonByLabel(driver, "Male");

		log.info("User_01_Login - Step_03: Input FirstName: '" + firstName + "'");
		registerPage.enterTextboxByID(driver, "FirstName", firstName);

		log.info("User_01_Login - Step_04: Input LastName: '" + lastName + "'");
		registerPage.enterTextboxByID(driver, "LastName", lastName);

		log.info("User_01_Login - Step_05: Input valid email: '" + emailAddress + "'");
		registerPage.enterTextboxByID(driver, "Email", emailAddress);

		log.info("User_01_Login - Step_06: Input password: '" + GlobalConstants.PASSWORD + "'");
		registerPage.enterTextboxByID(driver, "Password", GlobalConstants.PASSWORD);

		log.info("User_01_Login - Step_07: Input confirm password: '" + GlobalConstants.PASSWORD + "'");
		registerPage.enterTextboxByID(driver, "ConfirmPassword", GlobalConstants.PASSWORD);

		log.info("User_01_Login - Step_08: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");

		log.info("User_01_Login - Step_09: Verify register success message displayed ");
		assertTrue(registerPage.isRegisterSuccessMessageDisplayed());

		log.info("User_01_Login - Step_10: Click to 'Logout' button");
		registerPage.clickToHeaderTextLinkByName(driver, "Log out");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("User_01_Login - Step_11: Navigate to Login Page");
		homePage.clickToHeaderTextLinkByName(driver, "Log in");

		log.info("User_01_Login - Step_12: Open LoginPage");
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("User_01_Login - Step_13: Input valid Email ");
		loginPage.enterTextboxByID(driver, "Email", emailAddress);

		log.info("User_01_Login - Step_14: Input valid Password");
		loginPage.enterTextboxByID(driver, "Password", GlobalConstants.PASSWORD);

		log.info("User_01_Login - Step_15: Click Login button");
		loginPage.clickToButtonByName(driver, "Log in");

		log.info("User_01_Login - Step_16: Open HomePage");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("User_01_Login - Step_17: Verify Logout button displayed");
		verifyTrue(homePage.isButtonByNameDisplayed("Log out"));

		log.info("User_01_Login - Step_18: Get all cookies");
		loginCookiePage = homePage.getAllCookies(driver);
		System.out.print(loginCookiePage);

		log.info("Post - condition: Close driver and browser + '" + browserName + "'");
		driver.close();
	}
}
