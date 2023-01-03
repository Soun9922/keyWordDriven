package keyWord_Engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseClass_FB.baseClass;

public class keyWordEngg {
	public WebDriver driver;
	public Properties prop;
	baseClass base;
	WebElement element;
	public String locatorName =null;
	public String locatorValue =null;

	public static Workbook book;
	public static Sheet sheet;

	public final String Scenario_Sheet_Path = "D:\\KeyWord_Driven\\keyWord_Driven_Fb"
			+ "\\src\\main\\java\\keyWord_Scenario\\Scenario_FB.xlsx";

	public void startExecutions(String sheetName) {
		String locatorName = null;
		String locatorValue = null;

		FileInputStream file = null;
		try {
			file = new FileInputStream(Scenario_Sheet_Path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {
			String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim();// id-->[0] = username-->[1]
			if (!locatorColValue.equalsIgnoreCase("NA")) {
				locatorName = locatorColValue.split("=")[0].trim();//id
				locatorValue = locatorColValue.split("=")[1].trim();//username
			}
			String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
			String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
			switch (action) {
			case "open browser":
				base = new baseClass();
				prop = base.inti_properties();
				if (value.isEmpty() || value.equals("NA")) {
					driver = base.init_driver(prop.getProperty("browser"));
				} else {
					driver = base.init_driver(value);
				}
				break;
			case "enter url":
				if (value.isEmpty() || value.equals("NA")) {
					driver.get(prop.getProperty("url"));
				} else {
					driver.get(value);
				}
				break;
			case "quit":
				driver.quit();
				break;
			default:
				break;
			}

			switch (locatorName) {
			case "id":
				element = driver.findElement(By.id(locatorValue));
				if (action.equalsIgnoreCase("sendKeys")) {
					element.clear();
					element.sendKeys(value);
				} else if (action.equalsIgnoreCase("click")) {
					element.click();
				}
				locatorName = null;
				break;
			case "linkText":
				element = driver.findElement(By.linkText(locatorValue));
				element.click();
				locatorName = null;	
				break;
			default:
				break;
			}
		}catch(Exception e) {	
			}
		}
	}
}
