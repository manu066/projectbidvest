package com.bidvest.genericutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.bidvestUtility.pomrepositylib.OnlineBankingSystemHome;
import com.bidvestUtility.pomrepositylib.StaffHome;
import com.bidvestUtility.pomrepositylib.StaffLogin;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassStaff {
	
	public WebDriver driver=null;
	public ExcelUtility ExcelLib=new ExcelUtility();
	public FileUtility ProFile=new FileUtility();
	public JavaUtility JavaLib=new JavaUtility();
	public WebDriverUtlity weblib=new WebDriverUtlity();
	
	


	
	
	  @BeforeSuite public void configBS()
	  { 
		  System.out.println("Connect to DB ");
	  }
	  
	  
	 
	@BeforeClass
	public void configBC() throws Throwable
	{
		String URL=ProFile.getPropertyKeyValue("url");
		String Browser=ProFile.getPropertyKeyValue("Browser");
		if(Browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();


		}
		else if(Browser.equalsIgnoreCase("firefox"))
		{

			WebDriverManager.firefoxdriver().setup();

			driver=new FirefoxDriver();

		}
		else
		{
			System.out.println("Invalid Browser");
		}

		driver.get(URL);
		driver.manage().window().maximize();
		weblib.waitForElementInDOM(driver);
	}
	
	@BeforeMethod
	public void configBM() throws Throwable
	{
		
		String StaffId=ProFile.getPropertyKeyValue("StaffId");
		String staffpassword=ProFile.getPropertyKeyValue("staffpassword");
		
		OnlineBankingSystemHome hm=new OnlineBankingSystemHome(driver);
		hm.getStaffLnk().click();	
		StaffLogin sflogin=new StaffLogin(driver);
		sflogin.StaffLoginmeth(StaffId, staffpassword);
		
	}
	
	@AfterMethod 
	public void configAM()
	{
		StaffHome sfhm=new StaffHome(driver);
		sfhm.getStaffLogoutBtn().click();
		OnlineBankingSystemHome hm=new OnlineBankingSystemHome(driver);
		hm.getHomeBtn().click();
	}
	
	@AfterClass
	public void configAC()
	{
		driver.quit();
	}
	
	
	@AfterSuite
	public void conifAS()
	{
		System.out.println(" db close");
	}

}
