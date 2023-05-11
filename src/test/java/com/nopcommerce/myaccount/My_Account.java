package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nomcommerce.common.Common_01_Login;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.user.nopCommerce.AddProductReviewPO;
import pageObjects.user.nopCommerce.HomePagePO;
import pageObjects.user.nopCommerce.LoginPO;
import pageObjects.user.nopCommerce.MyAccountPO;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.SearchPO;

public class My_Account extends BaseTest{
	private WebDriver driver;
	HomePagePO homePage;
	LoginPO loginPage;
	MyAccountPO myaccountPage;
	SearchPO searchPage;
	AddProductReviewPO addProductReviewPage;
	private String Gender, Day, Month, Year, Company, Email, FirstName , LastName, Country, City, Address1, ZipCode, PhoneNumber, NewPassword, ProductName;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {
		
		Gender = "Female";
		Day = "22"; Month = "July"; Year = "1999"; 
		Email = "automationfc.vn@gmail.com"; 
		Company ="AutomationFC"; 
		FirstName = "Automation"; 
		LastName = "Testing";
		Country = "Viet Nam";
		City = "Đà Nẵng";
		Address1 = "138-TranVanOn";
		ZipCode = "84052";
		PhoneNumber = "0987612345";
		NewPassword = "123123";
		ProductName = "Build your own computer";
		
		log.info("Pre-condition: Open browser '" + browserName + "' with URL: '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		
		log.info("Pre-condition - 01: Open Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		log.info("Pre-condition - 02: Navigate to Login Page");
		homePage.clickToHeaderTextLinkByName(driver, "Log in");
		
		/*
		 * log.info("Pre-condition - 04: Set Login page cookie");
		 * loginPage.setAllCookies(driver, Common_01_Login.loginCookiePage);
		 * sleepInSecond(1); loginPage.getRefreshPage(driver);
		 */
		homePage.isButtonByNameDisplayed("Log in");
		homePage.clickToButtonByName(driver, "Log in");
		log.info("Pre-condition - 03: Open LoginPage");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterTextboxByID(driver, "Email", Common_01_Login.emailAddress);
		loginPage.enterTextboxByID(driver, "Password", GlobalConstants.PASSWORD);
		loginPage.clickToButtonByName(driver, "Log in");
		
		log.info("Pre-condition - 05: Open Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		log.info("Pre-condition - 06: Verify Logout button displayed");
		verifyTrue(homePage.isButtonByNameDisplayed("Log out"));
		
		log.info("Pre-condition - 07: Navigate to MyAccount Page");
		homePage.clickToHeaderTextLinkByName(driver, "My account");
		  
		log.info("Pre-condition - 08: Open Myaccount Page"); 
		myaccountPage = PageGeneratorManager.getMyAccountPage(driver);
		 
		log.info("Pre-condition - 09: Verify title 'Myaccount - Customer info' displayed"); 
		verifyTrue(myaccountPage.isPageTitleDisplayed(driver,"My account - Customer info"));
		
	}
	
	//@Test
	public void MyAccount_01_Update_Customer_Info() {
		
		log.info("MyAccount - Step 01: Edit Gender");
		myaccountPage.selectRadioButtonByLabel(driver,Gender);
		
		log.info("MyAccount - Step 02: Edit First Name");
		myaccountPage.enterTextboxByID(driver,"FirstName", FirstName);
		
		log.info("MyAccount - Step 03: Edit Last Name");
		myaccountPage.enterTextboxByID(driver,"LastName", LastName);
		
		log.info("MyAccount - Step 04: Edit Date of Birth");
		myaccountPage.selectDropDownBirthDate(driver,"Day", Day);
		myaccountPage.selectDropDownBirthDate(driver,"Month", Month);
		myaccountPage.selectDropDownBirthDate(driver,"Year", Year);
		
		log.info("MyAccount - Step 05: Edit Email");
		myaccountPage.enterTextboxByID(driver,"Email", Email);
		
		log.info("MyAccount - Step 06: Edit Company Name");
		myaccountPage.enterTextboxByID(driver, "Company", Company);
		
		log.info("MyAccount - Step 07: Click Save button");
		myaccountPage.clickToButtonByName(driver, "Save");
		
		log.info("MyAccount - Step 08: Verify update Gender " + Gender + " success");
		verifyTrue(myaccountPage.isGenderSelected(driver, Gender));
		
		log.info("MyAccount - Step 09: Verify update FirstName to " + FirstName + " success");
		verifyEquals(myaccountPage.isUpdatedTextBoxById("FirstName","value"),FirstName);
		
		log.info("MyAccount - Step 10: Verify update LastName to " + LastName + " success");
		verifyEquals(myaccountPage.isUpdatedTextBoxById("LastName","value"),LastName);
		
		log.info("MyAccount - Step 11:  Verify update Birthday to " + Day + "-" + Month + "-" + Year + " success");
		verifyEquals(myaccountPage.isUpdatedDateOfBirth("Day"),Day);
		verifyEquals(myaccountPage.isUpdatedDateOfBirth("Month"),Month);
		verifyEquals(myaccountPage.isUpdatedDateOfBirth("Year"),Year);
		
		log.info("MyAccount - Step 12:  Verify update " + Company + " success");
		verifyEquals(myaccountPage.isUpdatedTextBoxById("Company","value"),Company);
	
	}
	
	//@Test
	public void MyAccount_02_Add_Address() {
		myaccountPage.clickToNavigationBarByText(driver,"Address");
		myaccountPage.isPageTitleDisplayed(driver, "My account - Addresses");
		myaccountPage.clickToAddButton();
		myaccountPage.isPageTitleDisplayed(driver, "My account - Add new address");
		myaccountPage.enterTextboxByID(driver, "Address_FirstName", FirstName);
		myaccountPage.enterTextboxByID(driver, "Address_LastName", LastName);
		myaccountPage.enterTextboxByID(driver, "Address_Email", Email);
		myaccountPage.enterTextboxByID(driver, "Address_Company", Company);
		myaccountPage.selectDropDownByID(driver, "Address_CountryId", Country);
		myaccountPage.isLoadingIconClosed(driver);
		myaccountPage.enterTextboxByID(driver, "Address_City", City);
		myaccountPage.enterTextboxByID(driver, "Address_Address1", Address1);
		myaccountPage.enterTextboxByID(driver, "Address_ZipPostalCode", ZipCode);
		myaccountPage.enterTextboxByID(driver, "Address_PhoneNumber", PhoneNumber);
		myaccountPage.clickToButtonByName(driver, "Save");
		sleepInSecond(1);
	
		myaccountPage.isPageTitleDisplayed(driver, "My account - Addresses");
		verifyTrue(myaccountPage.isUpdatedAddressByName("name",FirstName + " " + LastName));
		verifyTrue(myaccountPage.isUpdatedAddressByName("email",Email));
		verifyTrue(myaccountPage.isUpdatedAddressByName("phone",PhoneNumber));
		verifyTrue(myaccountPage.isUpdatedAddressByName("address1", Address1));
		verifyTrue(myaccountPage.isUpdatedAddressByName("city-state-zip", City + ", " + ZipCode));
		verifyTrue(myaccountPage.isUpdatedAddressByName("country", Country));
		
		myaccountPage.clickToInfoButton("Delete");
		myaccountPage.acceptAlert(driver);
		verifyTrue(myaccountPage.isDisplayNoAddresstext());
		
	}
	
	//@Test
	public void MyAccount_03_Change_Password() {
		myaccountPage.clickToNavigationBarByText(driver, "Change password");
		myaccountPage.isPageTitleDisplayed(driver, "My account - Change password");
		myaccountPage.enterTextboxByID(driver, "OldPassword",GlobalConstants.PASSWORD);
		myaccountPage.enterTextboxByID(driver, "NewPassword",NewPassword);
		myaccountPage.enterTextboxByID(driver, "ConfirmNewPassword",NewPassword);
		myaccountPage.clickToButtonByName(driver, "Change password");
		myaccountPage.isNotificationDisplayed(driver,"Password was changed");
		myaccountPage.closeNotification(driver);
		sleepInSecond(1);
		myaccountPage.clickToHeaderTextLinkByName(driver, "Log out");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.isButtonByNameDisplayed("Log in");
		homePage.clickToHeaderTextLinkByName(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterTextboxByID(driver, "Email", Common_01_Login.emailAddress);
		loginPage.enterTextboxByID(driver, "Password", GlobalConstants.PASSWORD);
		loginPage.clickToButtonByName(driver, "Log in");
		verifyEquals(loginPage.displayedErrorValidateSumaryMessage(), "The credentials provided are incorrect");
		
		loginPage.enterTextboxByID(driver, "Password", NewPassword);
		loginPage.clickToButtonByName(driver, "Log in");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.isButtonByNameDisplayed("Log out");
	}
	
	@Test
	public void MyAccount_04_My_Products_Review() {
		homePage.enterTextboxByID(driver, "small-searchterms", ProductName);
		homePage.clickToQuickSearchButton(driver);
		searchPage = PageGeneratorManager.getSearchPage(driver);
		searchPage.clickToProductByName(ProductName);
		searchPage.clickToAddToReview();
		addProductReviewPage = PageGeneratorManager.getAddProductReviewPage(driver);
		addProductReviewPage.enterTextboxByID(driver, "AddProductReview_Title", "Title");
		addProductReviewPage.enterTextareaByID(driver, "AddProductReview_ReviewText", "Content");
		addProductReviewPage.clickToCheckBoxReviewRating(driver,"Good");
		addProductReviewPage.clickToButtonByName(driver, "Submit review");
		verifyEquals(addProductReviewPage.isAddProductReviewSuccessDisplayed(),"Product review is successfully added.");
		addProductReviewPage.clickToHeaderTextLinkByName(driver, "My account");
		myaccountPage = PageGeneratorManager.getMyAccountPage(driver);
		myaccountPage.clickToNavigationBarByText(driver, "My product reviews");
		myaccountPage.isPageTitleDisplayed(driver, "My account - My product reviews");
		verifyTrue(myaccountPage.isMyProductReviewDisplayed(ProductName,"Content","Title","80%"));
		
	}
	
	@AfterClass (alwaysRun = true)
	public void AfterClass() {
		closeBrowserAndDriver();
	}
}
