package pageUIs.user.nopCommerce;

public class MyAccountPageUI {
	public static final String DYNAMIC_INFO_BUTTON = "//div[@class ='address-list']//div/button[text()='Delete']";
	public static final String DYNAMIC_INFO_BY_TEXT = "//div[@class ='address-list']//ul/li[@class ='%s' and contains(string(),'%s')]";
	public static final String ADD_BUTTON = "//div[@class ='add-button']/button[text()='Add new']";
	public static final String NO_ADDRESS_BUTTON = "//div[text()='No addresses']";
	public static final String MYACCOUNT_REVIEW_INFO = "//a[text()='%s']//ancestor::div[@class='review-info']/preceding-sibling::div[text()='%s']//ancestor::div[@class='product-review-item']//div[@class='review-title' and contains(string(),'%s')]/following-sibling::div//div[@style='width:%s']";
}
