package main.java;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends initialize  {

	initialize a;
	Logger log=Logger.getLogger(getClass());
	

public  LoginTest() throws IOException {

a=new initialize();

}

	

	@Test(dataProvider="datasheet",priority=0)
	public void data(String Username, String Password) throws IOException
	{
		DOMConfigurator.configure("log4j.xml");
		wd.get(locators.getProperty("URL"));
		wd.manage().deleteAllCookies();
		getwebelement("Username").sendKeys(Username);
		getwebelement("Password").sendKeys(Password);
		WebDriverWait w= new WebDriverWait(wd,30);
		WebElement login=w.until(ExpectedConditions.visibilityOfElementLocated(By.id("Login")));
		login.click();
	    System.out.println(wd.getTitle());
	    
	    try
	    {
	    log.info("URL"+locators.getProperty("URL")+"navigated Successfully");
	    log.info("Website title is "+wd.getTitle());
	    log.info("Username is"+Username);
	    log.info("Password is Masked");
	    
	    }catch(Throwable t)
	    {
	    	log.warn("Error in Page"+t.getMessage());
	    }
		
	}
	
	@Test(priority=1)
	public void ProductionOrder() throws IOException, InterruptedException
	{
		
		Thread.sleep(10000);
		getwebelement("ProductionOrder").click();
		//screenshot(wd.getTitle());
		Thread.sleep(6000);
		Boolean data= getwebelement("ProductionOrder").isEnabled();
		System.out.println(data);
	    System.out.println(wd.getTitle());
	    System.out.println(getwebelement("ProductionOrder").getText());
	 /*   List<WebElement> tabs= wd.findElements(By.tagName("li"));
	    Thread.sleep(10000);
	    List<WebElement> tabs= wd.findElements(By.cssSelector("#\30 0B5F000004NW2r_listButtons > ul > li:nth-child(1)"));
	    for(int i=0;i<tabs.size();i++)
	    {
	    	
	    	System.out.println(tabs.get(i).getText());
	    }
	   Thread.sleep(10000);*/
	  
	    WebElement name=wd.findElement(By.name("view_directory"));
	    Thread.sleep(10000);
	    name.click();
	    Thread.sleep(10000);
	  // System.out.println(name.getText()); 
	  	   alert();
	// wd.findElement(By.id("a0yO0000002nNki")).click();//Checkbox	
	element("click","a0yO0000002nQlp",null);
	Thread.sleep(20000);
	WebDriverWait wait= new WebDriverWait(wd,20);
	WebElement view=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("view_directory")));
    view.click();
	Thread.sleep(10000);
	wd.findElement(By.className("iconOrderFile_active")).click();
	   
	    try
	    {
	    log.info("URL"+locators.getProperty("URL")+"navigated Successfully");
	    log.info("Website title is "+wd.getTitle());
	    
	    
	    }catch(Throwable t)
	    {
	    	log.warn("Error in Page"+t.getMessage());
	    }
		
	}
	
	private void element(String type, String locator, Object data) {
		// TODO Auto-generated method stub
		
	}



	@DataProvider(name="datasheet")
	public static Object[][] data1() throws IOException
	{
		/*Object[][] data= new Object[1][2];
		
		data[0][0]="ken@kirinuki.jp.full";
		data[0][1]="z9UG3EAf";
		return data;*/
		
		Object[][] data=Excelreader.singledata();
		return data;
		
		
	}
	/*@AfterTest
	public void close()
	{
		if(null!=wd){
			wd.quit();
		}
		
		}*/
}
