package baseClass_FB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class baseClass {
	public WebDriver driver;
	public Properties prop;

	public WebDriver init_driver(String browserName) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\souna\\driverfortest\\chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}

	public Properties inti_properties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					"D:\\KeyWord_Driven\\keyWord_Driven_Fb"
					+ "\\src\\main\\java\\keyWord_Config\\config_Properties");
				prop.load(ip);
		}  catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
				e.printStackTrace();
		}
		return prop;
	}
}
