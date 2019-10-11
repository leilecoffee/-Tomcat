package ex02;

import java.io.File;

public class Constants {
	
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

	public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	public static final String SUCCESS_HTTP_MESSAGE= "HTTP/1.1 200 ok\r\n" + "Content-Type:text/html\r\n" + "\r\n";
}
