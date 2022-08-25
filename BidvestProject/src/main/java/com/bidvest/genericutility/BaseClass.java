package com.bidvest.genericutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.bidvestUtility.pomrepositylib.CustomerLogin;
import com.bidvestUtility.pomrepositylib.CustomerProfile;
import com.bidvestUtility.pomrepositylib.OnlineBankingSystemHome;
import com.bidvestUtility.pomrepositylib.StaffHome;
import com.bidvestUtility.pomrepositylib.StaffLogin;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver=null;
	public ExcelUtility ExcelLib=new ExcelUtility();
	public FileUtility ProFile=new FileUtility();
	public JavaUtility JavaLib=new JavaUtility();
	public WebDriverUtlity weblib=new WebDriverUtlity();

	public int p=1;
	// Read Common Data from Properties file




	@BeforeSuite(groups = {"SmokeTest","regression"})
	public void configBS()
	{ 
		System.out.println("Connect to DB ");

	}

	@Parameters("Browser")
	@BeforeClass(groups = {"SmokeTest","regression"})
	public void configBC(String Browser) throws Throwable
	{
		String URL=ProFile.getPropertyKeyValue("url");
		//String Browser=ProFile.getPropertyKeyValue("Browser");
		if(Browser.equalsIgnoreCase("chrome"))
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
	
	@BeforeMethod(groups = {"SmokeTest","regression"})
	public void configBM() throws Throwable
	{

		String StaffId=ProFile.getPropertyKeyValue("StaffId");
		String staffpassword=ProFile.getPropertyKeyValue("staffpassword");
		String CustomerId1=ProFile.getPropertyKeyValue("customerId1");
		String custom1password=ProFile.getPropertyKeyValue("custom1password");

		String CustomerId2=ProFile.getPropertyKeyValue("customerId2");
		String custom2password=ProFile.getPropertyKeyValue("custom2password");
		if(p<=3)
		{
			OnlineBankingSystemHome hm=new OnlineBankingSystemHome(driver);
			WebElement ele = hm.getInternetBankingMouseOvrElmt();  //element from pomrepositylib
			weblib.mouseOverOnElement(driver,ele);
			hm.getLoginBtn().click();  // click on login

			//Internet Banking Using customer id and password from property file

			CustomerLogin login=new CustomerLogin(driver);   //login 	InternetBanking
			login.CustomerLoginMeth(CustomerId1, custom1password);// Business lib
			p++;
		}
		else 
		{
			OnlineBankingSystemHome hm=new OnlineBankingSystemHome(driver);
			WebElement ele = hm.getInternetBankingMouseOvrElmt();  //element from pomrepositylib
			weblib.mouseOverOnElement(driver,ele);
			hm.getLoginBtn().click();  // click on login

			//Internet Banking Using customer id and password from property file

			CustomerLogin login=new CustomerLogin(driver);   //login 	InternetBanking
			login.CustomerLoginMeth(CustomerId2, custom2password);// Business lib
			p++;

		}


	}
	@AfterMethod(groups = {"SmokeTest","regression"})
	public void configAM()
	{

		CustomerProfile profile=new CustomerProfile(driver);
		profile.getCustomerLogoutBtn().click();
		OnlineBankingSystemHome hm=new OnlineBankingSystemHome(driver);
		hm.getHomeBtn().click();



	}


	@AfterClass (groups = {"SmokeTest","regression"})
	public void configAC()
	{ 
		//step: close the application

		driver.close();
	}

	@AfterSuite (groups = {"SmokeTest","regression"})
	public void configAS() 
	{
		System.out.println(" disconnect the db");
	}


}
