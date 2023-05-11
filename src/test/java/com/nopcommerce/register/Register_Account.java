package com.nopcommerce.register;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.user.nopCommerce.HomePagePO;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPO;

public class Register_Account extends BaseTest {
	private WebDriver driver;
	private HomePagePO homePage;
	private RegisterPO registerPage;
	private String  firstName, lastName, invalidPassword, duplicateEmail;
	public static String emailAddress = getRandomEmail();

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {
		firstName = "Automation";
		lastName = "Testing";
		invalidPassword = "123";

		log.info("Pre-condition: Open browser '" + browserName + "' with URL: '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		log.info("Pre-condition - 01: Open Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Pre-condition - 02: Navigate to RegisterPage");
		homePage.clickToHeaderTextLinkByName(driver, "Register");
	}

	@Test
	public void Register_01_Leave_All_Fields_Blank() {
		log.info("Register_00 - Step_01: Open Register Page");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		log.info("Register_00 - Step_02: Click to Register button");
		registerPage.clickToButtonByName(driver, "Register");

		log.info("Register_00 - Step_03: Verify validation error message display");
		verifyTrue(registerPage.displayedErrorMessageByText(driver, "First name is required."));
		verifyTrue(registerPage.displayedErrorMessageByText(driver, "Last name is required."));
		verifyTrue(registerPage.displayedErrorMessageByText(driver, "Email is required."));
		verifyTrue(registerPage.displayedErrorMessageByText(driver, "Password is required."));
	}

	@Test
	public void Register_02_Invalid_Email() {
		log.info("Register_01 - Step_01: Refresh page");
		registerPage.getRefreshPage(driver);

		log.info("Register_01 - Step_02: Select Radio button: 'Male'");
		registerPage.selectRadioButtonByLabel(driver, "Male");

		log.info("Register_01 - Step_03: Input FirstName: '" + firstName + "'");
		registerPage.enterTextboxByID(driver, "FirstName", firstName);

		log.info("Register_01 - Step_04: Input LastName: '" + lastName + "'");
		registerPage.enterTextboxByID(driver, "LastName", lastName);

		log.info("Register_01 - Step_05: Input invalid email: '" + GlobalConstants.INVALID_EMAIL + "'");
		registerPage.enterTextboxByID(driver, "Email", GlobalConstants.INVALID_EMAIL);

		log.info("Register_01 - Step_06: Input password: '" + GlobalConstants.PASSWORD + "'");
		registerPage.enterTextboxByID(driver, "Password", GlobalConstants.PASSWORD);

		log.info("Register_01 - Step_07: Input confirm password: '" + GlobalConstants.PASSWORD + "'");
		registerPage.enterTextboxByID(driver, "ConfirmPassword", GlobalConstants.PASSWORD);

		log.info("Register_01 - Step_08: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");

		log.info("Register_01 - Step_09: Verify errormessage 'Wrong Email' displayed");
		verifyTrue(registerPage.displayedErrorMessageByText(driver, "Wrong email"));
	}

	@Test
	public void Register_03_Password_less_than_6_characters() {
		log.info("Register_02 - Step_01: Refresh Page");
		registerPage.getRefreshPage(driver);

		log.info("Register_02 - Step_02: Select Radio button: 'Male'");
		registerPage.selectRadioButtonByLabel(driver, "Male");

		log.info("Register_02 - Step_03: Input FirstName: '" + firstName + "'");
		registerPage.enterTextboxByID(driver, "FirstName", firstName);

		log.info("Register_02 - Step_04: Input LastName: '" + lastName + "'");
		registerPage.enterTextboxByID(driver, "LastName", lastName);

		log.info("Register_02 - Step_05: Input invalid email: '" + emailAddress + "'");
		registerPage.enterTextboxByID(driver, "Email", emailAddress);

		log.info("Register_02 - Step_06: Input password: '" + invalidPassword + "'");
		registerPage.enterTextboxByID(driver, "Password", invalidPassword);

		log.info("Register_02 - Step_07: Input confirm password: '" + invalidPassword + "'");
		registerPage.enterTextboxByID(driver, "ConfirmPassword", invalidPassword);

		log.info("Register_02 - Step_08: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");

		log.info("Register_02 - Step_09: Verify Error Message 'must have at least 6 characters' displayed");
		verifyEquals(registerPage.errorMessageInvalidPassword(), "must have at least 6 characters");
	}

	@Test
	public void Register_04_ConfirmPassword_Not_Match_With_Password() {
		log.info("Register_03 - Step_01: Refresh Page");
		registerPage.getRefreshPage(driver);

		log.info("Register_03 - Step_02: Select Radio button: 'Male'");
		registerPage.selectRadioButtonByLabel(driver, "Male");

		log.info("Register_03 - Step_03: Input FirstName: '" + firstName + "'");
		registerPage.enterTextboxByID(driver, "FirstName", firstName);

		log.info("Register_03 - Step_04: Input LastName: '" + lastName + "'");
		registerPage.enterTextboxByID(driver, "LastName", lastName);

		log.info("Register_03 - Step_05: Input invalid email: '" + emailAddress + "'");
		registerPage.enterTextboxByID(driver, "Email", emailAddress);

		log.info("Register_03 - Step_06: Input password: '" + GlobalConstants.PASSWORD + "'");
		registerPage.enterTextboxByID(driver, "Password", GlobalConstants.PASSWORD);

		log.info("Register_03 - Step_07: Input confirm password: '" + firstName + "'");
		registerPage.enterTextboxByID(driver, "ConfirmPassword", firstName);

		log.info("Register_02 - Step_08: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");

		log.info(
				"Register_03 - Step_09: Verify that errormessage 'The password and confirmation password do not match.' displayed");
		verifyTrue(registerPage.displayedErrorMessageByText(driver,
				"The password and confirmation password do not match."));
	}

	@Test
	public void Register_05_Register_Success() {
		log.info("Register_04 - Step_01: Refresh Page");
		registerPage.getRefreshPage(driver);

		log.info("Register_04 - Step_02: Select Radio button: 'Male'");
		registerPage.selectRadioButtonByLabel(driver, "Male");

		log.info("Register_04 - Step_03: Input FirstName: '" + firstName + "'");
		registerPage.enterTextboxByID(driver, "FirstName", firstName);

		log.info("Register_04 - Step_04: Input LastName: '" + lastName + "'");
		registerPage.enterTextboxByID(driver, "LastName", lastName);

		log.info("Register_04 - Step_05: Input valid email: '" + emailAddress + "'");
		registerPage.enterTextboxByID(driver, "Email", emailAddress);
		duplicateEmail = registerPage.getEmailValue(driver, "value", "Email");

		log.info("Register_04 - Step_06: Input password: '" + GlobalConstants.PASSWORD + "'");
		registerPage.enterTextboxByID(driver, "Password", GlobalConstants.PASSWORD);

		log.info("Register_04 - Step_07: Input confirm password: '" + GlobalConstants.PASSWORD + "'");
		registerPage.enterTextboxByID(driver, "ConfirmPassword", GlobalConstants.PASSWORD);

		log.info("Register_04 - Step_08: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");

		log.info("Register_04 - Step_09: Verify register success message displayed ");
		verifyTrue(registerPage.isRegisterSuccessMessageDisplayed());
	}

	@Test
	public void Register_06_Email_Already_Registed() {
		log.info("Register_05 - Step_01: Click to 'Logout' button");
		registerPage.clickToHeaderTextLinkByName(driver, "Register");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Register_05 - Step_02: Click to 'Register' button");
		homePage.clickToHeaderTextLinkByName(driver, "Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		log.info("Register_05 - Step_03: Select Radio button: 'Male'");
		registerPage.selectRadioButtonByLabel(driver, "Male");

		log.info("Register_05 - Step_04: Input FirstName: '" + firstName + "'");
		registerPage.enterTextboxByID(driver, "FirstName", firstName);

		log.info("Register_05 - Step_05: Input LastName: '" + lastName + "'");
		registerPage.enterTextboxByID(driver, "LastName", lastName);

		log.info("Register_05 - Step_06: Input invalid email: '" + duplicateEmail + "'");
		registerPage.enterTextboxByID(driver, "Email", duplicateEmail);

		log.info("Register_05 - Step_07: Input password: '" + GlobalConstants.PASSWORD + "'");
		registerPage.enterTextboxByID(driver, "Password", GlobalConstants.PASSWORD);

		log.info("Register_05 - Step_08: Input confirm password: '" + GlobalConstants.PASSWORD + "'");
		registerPage.enterTextboxByID(driver, "ConfirmPassword", GlobalConstants.PASSWORD);

		log.info("Register_05 - Step_09: Click to 'Register' button");
		registerPage.clickToButtonByName(driver, "Register");

		log.info("Register_05 - Step_10: Verify Error Message 'The specified email already exists' displayed");
		verifyEquals(registerPage.errorMessageEmailExist(), "The specified email already exists");
	}

	@AfterClass(alwaysRun = true)
	public void AfterClass() {
		closeBrowserAndDriver();
	}
}
