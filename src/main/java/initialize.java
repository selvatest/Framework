package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import net.sourceforge.htmlunit.corejs.javascript.GeneratedClassLoader;



public class initialize {
	
	public static Properties locators=null;
	public static Properties config=null;
	public static WebDriver  wd= null;
	
	public initialize() throws IOException
	{
		browser();
	}
	
	public void browser() throws IOException
	{
		if(wd==null)
		{
			initial();
		}
		
		}
	
	
	
	private void initial()throws IOException{

		
		FileInputStream f= new FileInputStream(new File("C:\\Users\\PRAKASH\\workspace\\China\\src\\main\\resources\\locator.properties"));
		locators= new Properties();
		locators.load(f);
	//System.out.println(locators.getProperty("god"));
		
		FileInputStream f1= new FileInputStream(new File("C:\\Users\\PRAKASH\\workspace\\China\\src\\main\\resources\\config.properties"));
		config= new Properties();
		config.load(f1);
		
		
		
		if(config.getProperty("browser").equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",config.getProperty("firefoxdriver"));
			wd=new FirefoxDriver();
		}else
		if(config.getProperty("browser").equals("chrome"))
		{
		
			System.setProperty("webdriver.chrome.driver",config.getProperty("chromedriver"));
			wd=new ChromeDriver();
		}else
		{
			System.setProperty("webdriver.ie.driver",config.getProperty("iedriver"));
			wd=new InternetExplorerDriver();
		
		}
	}
	public static WebElement getwebelement(String locator) throws IOException
	{
	//	return wd.findElement(By.id(locators.getProperty(locator)));
		try
		{
		return wd.findElement(By.id(locators.getProperty(locator)));
		}
		catch(Throwable a)
		{
			File srcfile=((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcfile, new File("E:\\M.Selva\\FileRepo-master\\FileRepo-master\\Screenshot"+locator+".jpg"));
			System.out.println(a.getMessage());
			
		}
		return null;
	}
	
	public void close()
	{
		wd.close();
		wd=null;
		
	}
	public void screenshot(String name) throws IOException
	 {
		 File srcfile=((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(srcfile, new File("C:\\Users\\PRAKASH\\workspace\\China"+name+".jpg")); 
	 }
	
	public void logger()
	{
		Logger log= Logger.getLogger(getClass());
	    
	}
	
	
	public static WebElement element(String type,String locator, String data)
		{
		
		switch(type)
		{
		case "click":
		wd.findElement(By.id(locator)).click();
		
		case "sendkeys":
		wd.findElement(By.id(locator)).sendKeys(data);
		
		case "gettext":
		wd.findElement(By.id(locator)).getText();			
			
		default:
			break;
		}
		return null;
		}
	
 public void alert()
 {
	 Alert a= wd.switchTo().alert();
		System.out.println(a.getText());
		a.accept();
 }
 
 public void select(WebElement select, String value)
 {
	 Select s= new Select(select);
	 s.selectByVisibleText(value);
 }
	
 public void action()
 {
	 Actions act= new Actions(wd);
 }
 
 public void perform(Properties p,String operation,String objectName,String objectType,String value) throws Exception{
		System.out.println("");
		switch (operation.toUpperCase()) {
		case "CLICK":
			//Perform click
			wd.findElement(this.getObject(p,objectName,objectType)).click();
			break;
		case "SETTEXT":
			//Set text on control
			wd.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
			break;
			
		case "GOTOURL":
			//Get url of application
			wd.get(p.getProperty(value));
			break;
		case "GETTEXT":
			//Get text of an element
			wd.findElement(this.getObject(p,objectName,objectType)).getText();
			break;

		default:
			break;
		}
	}
	
	/**
	 * Find element BY using object type and value
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	public By getObject(Properties p,String objectName,String objectType) throws Exception{
		//Find by xpath
		if(objectType.equalsIgnoreCase("XPATH")){
			
			return By.xpath(p.getProperty(objectName));
		}
		//find by class
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
			
			return By.className(p.getProperty(objectName));
			
		}
		//find by name
		else if(objectType.equalsIgnoreCase("NAME")){
			
			return By.name(p.getProperty(objectName));
			
		}
		//Find by css
		else if(objectType.equalsIgnoreCase("CSS")){
			
			return By.cssSelector(p.getProperty(objectName));
			
		}
		//find by link
		else if(objectType.equalsIgnoreCase("LINK")){
			
			return By.linkText(p.getProperty(objectName));
			
		}
		//find by partial link
		else if(objectType.equalsIgnoreCase("PARTIALLINK")){
			
			return By.partialLinkText(p.getProperty(objectName));
			
		}else
		{
			throw new Exception("Wrong object type");
		}
	}
 
}
