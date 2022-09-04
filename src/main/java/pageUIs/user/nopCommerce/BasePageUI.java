package pageUIs.user.nopCommerce;

public class BasePageUI {
	public static final String HEADER_TEXT_LINK_BY_NAME = "//div[@class = 'header-links']//a[text()='%s']";
	public static final String DYNAMIC_BUTTON_BY_NAME = "//div[@class = 'buttons']/button[text() = '%s']";
	public static final String DYNAMIC_VALIDATION_MESSAGE_BY_TEXT = "//span[@class='field-validation-error']/span[text()='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL = "//label[text()='%s']/preceding-sibling::input[@type='radio']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_TEXTAREA_BY_ID = "//div[@class='inputs']//textarea[@id='%s']";
	public static final String DYNAMIC_TEXT_PAGETITLE = "//div[@class='page-title']/h1[text()='%s']";
	public static final String DYNAMIC_DATEOFBIRTH = "//div[@class='inputs date-of-birth']//select[@name = 'DateOfBirth%s']";
	public static final String DYNAMIC_DROPDOWN_BY_ID = "//select[@id = '%s']";
	public static final String DYNAMIC_NAVIGATION_BAR_BY_ID = "//ul[@class = 'list']//a[contains(string(),'%s')]";
	public static final String DYNAMIC_NOTIFICATION_TEXT = "//div[@id='bar-notification']//p[text()='%s']";
	public static final String DYNAMIC_NOTIFICATION_CLOSE = "//div[@id='bar-notification']//span[@title='Close']";
	public static final String DYNAMIC_PROGRESS_LOADING_ICON = "//span[@id = 'states-loading-progress']";
	public static final String QUICK_SEARCH_BUTTON = "//form[@id='small-search-box-form']//button[text() = 'Search']";
	public static final String REVIEW_RATING_CHECKBOX = "//div[@class='review-rating']//input[@aria-label='%s']";
	public static final String DYNAMIC_FOOTER_BY_TEXT = "//div[@class='footer']//a[text()='%s']";

}
