package commons;

import java.io.File;

public class GlobalConstants {
	public static final int SHORT_TIMEOUT = 5;
	public static final int LONG_TIMEOUT = 15;
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_PATH = PROJECT_PATH + File.separator + "downloadFiles" + File.separator;
	
	public static final String INVALID_EMAIL = "testing";
	public static final String INVALID_PASSWORD = "123";
	public static final String PASSWORD = "123456";
	public static final String EMAIL_NOT_REGISTERED = "automationtesting9999@gmail.net";

}
