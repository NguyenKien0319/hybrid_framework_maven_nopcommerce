package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.user.nopCommerce.BasePageUI;

public class BasePage {
	public void openUrlPage(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void getToBackPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void getToForwardPage(WebDriver driver) {
		driver.navigate().forward();
		;
	}

	public void getRefreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitElementPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitElementPresence(driver);
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitElementPresence(driver);
		driver.switchTo().alert().dismiss();
	}

	public void sendsKeyToAlert(WebDriver driver, String value) {
		waitElementPresence(driver);
		driver.switchTo().alert().sendKeys(value);
	}

	public String getTextToAlert(WebDriver driver) {
		waitElementPresence(driver);
		return driver.switchTo().alert().getText();
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}

		}
	}

	public void switchToWindowByTitle(WebDriver driver, String windowTitle) {
		Set<String> listWindows = driver.getWindowHandles();
		for (String window : listWindows) {
			driver.switchTo().window(window);
			if (driver.getTitle().equals(windowTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentWindow) {
		Set<String> listWindows = driver.getWindowHandles();
		for (String window : listWindows) {
			driver.switchTo().window(window);
			if (!driver.getTitle().equals(parentWindow)) {
				driver.close();
			}
			driver.switchTo().window(parentWindow);
		}
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public String getDynamicLocator(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	public WebElement getElement(WebDriver driver, String locator, String... params) {
		return driver.findElement(getByXpath(getDynamicLocator(locator, params)));
	}

	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName, String... params) {
		return getElement(driver, getDynamicLocator(locator, params)).getAttribute(attributeName);
	}

	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	public List<WebElement> getElements(WebDriver driver, String locator, String... params) {
		return driver.findElements(getByXpath(getDynamicLocator(locator, params)));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... params) {
		getElement(driver, getDynamicLocator(locator, params)).click();
	}

	public void sendsKeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	public void sendsKeyToElement(WebDriver driver, String locator, String value, String... params) {
		getElement(driver, getDynamicLocator(locator, params)).clear();
		getElement(driver, getDynamicLocator(locator, params)).sendKeys(value);
	}

	public int getElementSize(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}

	public int getElementSize(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		return getElements(driver, locator).size();
	}

	public void selectDropDownByText(WebDriver driver, String locator, String item) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(item);
	}

	public void selectDropDownByText(WebDriver driver, String locator, String item, String... params) {
		select = new Select(getElement(driver, getDynamicLocator(locator, params)));
		select.selectByVisibleText(item);
	}

	public String getDropDownItem(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public String getDropDownItem(WebDriver driver, String locator, String... params) {
		select = new Select(getElement(driver, getDynamicLocator(locator, params)));
		return select.getFirstSelectedOption().getText();
	}

	public Boolean isDropDownMultiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
			String expectedItem) {
		getElement(driver, parentLocator).click();
		sleepInMiliSecond(1000);

		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInMiliSecond(1000);

				item.click();
				sleepInMiliSecond(1000);
				break;
			}
		}
	}

	public String getTextElement(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}

	public String getTextElement(WebDriver driver, String locator, String... params) {
		return getElement(driver, getDynamicLocator(locator, params)).getText();
	}

	public String getCssValue(WebDriver driver, String locator, String cssAttributeName) {
		return getElement(driver, locator).getCssValue(cssAttributeName);
	}

	public String convertRbgaToHex(String rbgaValue) {
		return Color.fromString(rbgaValue).asHex();
	}

	public Boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public Boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public Boolean isElementSelected(WebDriver driver, String locator, String... params) {
		return getElement(driver, getDynamicLocator(locator, params)).isSelected();
	}

	public void checkTheCheckBoxOrRadio(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public void checkTheCheckBoxOrRadio(WebDriver driver, String locator, String... params) {
		if (!isElementSelected(driver, getDynamicLocator(locator, params))) {
			getElement(driver, getDynamicLocator(locator, params)).click();
		}
	}

	public void unCheckTheCheckBox(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public Boolean isElementDisplayed(WebDriver driver, String locator) {
		return getElement(driver, locator).isDisplayed();
	}

	public Boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		return getElement(driver, getDynamicLocator(locator, values)).isDisplayed();
	}

	public boolean isElementUnDisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getElements(driver, locator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public WebDriver switchToFrameByElement(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}

	public WebDriver switchToDefaultContent(WebDriver driver, String locator) {
		return driver.switchTo().defaultContent();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locator, String... params) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, getDynamicLocator(locator, params))).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}

	public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
	}

	public void presskeyToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	public void presskeyToElement(WebDriver driver, String locator, Keys key, String... params) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator, params), key).perform();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public Object executeJavascriptToBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript("return document.documentElement.innerText;").toString();
	}

	public Boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public Object scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInMiliSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean areJQueryAndJsLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return window.jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}

	protected void waitForElementPresence(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
	}

	protected void waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
	}

	protected void waitForAllElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}

	protected void waitForAllElementsVisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicLocator(locator, params))));
	}

	protected void waitForElementClickable(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, values))));
	}

	protected void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
	}

	public void sleepInMiliSecond(long timeoutSecond) {
		try {
			Thread.sleep(timeoutSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Pattern Object
	public void clickToHeaderTextLinkByName(WebDriver driver, String headerName) {
		waitForElementClickable(driver, BasePageUI.HEADER_TEXT_LINK_BY_NAME, headerName);
		clickToElement(driver, BasePageUI.HEADER_TEXT_LINK_BY_NAME, headerName);
	}

	public void clickToButtonByName(WebDriver driver, String buttonName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_NAME, buttonName);
		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_NAME, buttonName);
	}

	public boolean displayedErrorMessageByText(WebDriver driver, String validationText) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_VALIDATION_MESSAGE_BY_TEXT, validationText);
		return isElementDisplayed(driver, BasePageUI.DYNAMIC_VALIDATION_MESSAGE_BY_TEXT, validationText);
	}

	public void selectRadioButtonByLabel(WebDriver driver, String labelName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, labelName);
		checkTheCheckBoxOrRadio(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, labelName);
	}

	public void enterTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendsKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}

	public void enterTextareaByID(WebDriver driver, String textareaID, String value) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_ID, textareaID);
		sendsKeyToElement(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_ID, value, textareaID);
	}

	public String getEmailValue(WebDriver driver, String attribute, String nameID) {
		return getElementAttributeValue(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, "value", "Email");
	}

	public boolean isPageTitleDisplayed(WebDriver driver, String pageTitle) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXT_PAGETITLE, pageTitle);
		return isElementDisplayed(driver, BasePageUI.DYNAMIC_TEXT_PAGETITLE, pageTitle);
	}

	public void selectDropDownBirthDate(WebDriver driver, String dateOfBirth, String number) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_DATEOFBIRTH, dateOfBirth);
		selectDropDownByText(driver, BasePageUI.DYNAMIC_DATEOFBIRTH, number, dateOfBirth);
	}

	public void selectDropDownByID(WebDriver driver, String dropDownID, String value) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_ID, dropDownID);
		selectDropDownByText(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_ID, value, dropDownID);
	}

	public boolean isGenderSelected(WebDriver driver, String gender) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, gender);
		return isElementSelected(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, gender);
	}

	public void clickToNavigationBarByText(WebDriver driver, String navigatrionBarByID) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_NAVIGATION_BAR_BY_ID, navigatrionBarByID);
		clickToElement(driver, BasePageUI.DYNAMIC_NAVIGATION_BAR_BY_ID, navigatrionBarByID);
	}

	public boolean isNotificationDisplayed(WebDriver driver, String contentText) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_NOTIFICATION_TEXT, contentText);
		return isElementDisplayed(driver, BasePageUI.DYNAMIC_NOTIFICATION_TEXT, contentText);
	}

	public void closeNotification(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_NOTIFICATION_CLOSE);
		clickToElement(driver, BasePageUI.DYNAMIC_NOTIFICATION_CLOSE);
	}

	public boolean isLoadingIconClosed(WebDriver driver) {
		waitForElementInvisible(driver, BasePageUI.DYNAMIC_PROGRESS_LOADING_ICON);
		return isElementUnDisplayed(driver, BasePageUI.DYNAMIC_PROGRESS_LOADING_ICON);
	}

	public void clickToQuickSearchButton(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.QUICK_SEARCH_BUTTON);
		clickToElement(driver, BasePageUI.QUICK_SEARCH_BUTTON);
	}

	public void clickToCheckBoxReviewRating(WebDriver driver, String rating) {
		waitForElementClickable(driver, BasePageUI.REVIEW_RATING_CHECKBOX, rating);
		checkTheCheckBoxOrRadio(driver, BasePageUI.REVIEW_RATING_CHECKBOX, rating);
	}

	public void clickToFooterByText(WebDriver driver, String text) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_FOOTER_BY_TEXT, text);
		clickToElement(driver, BasePageUI.DYNAMIC_FOOTER_BY_TEXT, text);
	}

	private WebDriverWait explicitWait;
	private Select select;
	private JavascriptExecutor jsExecutor;
	private Actions action;

}
