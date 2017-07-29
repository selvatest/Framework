package main.java;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductionOrder extends LoginTest {



	public ProductionOrder() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	initialize a;
	Logger log=Logger.getLogger(getClass());
	
	@Test(dataProvider="datasheet")
	public void data(String Username, String Password) throws IOException
	{
		
		DOMConfigurator.configure("log4j.xml");
		wd.get(locators.getProperty("URL"));
		wd.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("Time")), TimeUnit.SECONDS);
		wd.manage().timeouts().pageLoadTimeout(Integer.parseInt(config.getProperty("Time")), TimeUnit.SECONDS);
		getwebelement("ProductionOrder").click();
		screenshot(wd.getTitle());
		Boolean data= getwebelement("ProductionOrder").isSelected();
		System.out.println(data);
	    System.out.println(wd.getTitle());
	    getwebelement("ProductionOrder").getText();
	    //assertEquals(getwebelement("Name").getText(), "");
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
	
}
