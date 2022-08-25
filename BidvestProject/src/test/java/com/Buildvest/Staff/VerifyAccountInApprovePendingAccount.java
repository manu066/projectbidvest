package com.Buildvest.Staff;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.bidvest.genericutility.ExcelUtility;
import com.bidvest.genericutility.FileUtility;
import com.bidvest.genericutility.JavaUtility;
import com.bidvest.genericutility.WebDriverUtlity;
import com.bidvestUtility.pomrepositylib.OnlineBankingSystemHome;
import com.bidvestUtility.pomrepositylib.PendingCustomer;
import com.bidvestUtility.pomrepositylib.StaffHome;
import com.bidvestUtility.pomrepositylib.StaffLogin;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyAccountInApprovePendingAccount {

	public static void main(String[] args) throws Throwable {

		WebDriver driver=null;
		ExcelUtility ExcelLib=new ExcelUtility();
		FileUtility ProFile=new FileUtility();
		JavaUtility JavaLib=new JavaUtility();
		WebDriverUtlity weblib=new WebDriverUtlity();
		
		//step1 : Read the Data from common data
		String URL=ProFile.getPropertyKeyValue("url");
		String StaffId=ProFile.getPropertyKeyValue("StaffId");
		String staffpassword=ProFile.getPropertyKeyValue("staffpassword");
		String CustomerId1=ProFile.getPropertyKeyValue("customerId1");
		String custom1password=ProFile.getPropertyKeyValue("custom1password");
		
		String CustomerId2=ProFile.getPropertyKeyValue("customerId2");
		String custom2password=ProFile.getPropertyKeyValue("custom2password");
		String Browser=ProFile.getPropertyKeyValue("Browser");

		// step2:Read Data From Excel Sheet
		String ApplicationNo=ExcelLib.getExcelData("Sheet1", 4, 2);
        String ExpectedResult=ExcelLib.getExcelData("Sheet1", 4, 3);

		// Step3: Launch the Browser ---

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
		
		// step 4: Login as Staff 		
		OnlineBankingSystemHome hm=new OnlineBankingSystemHome(driver);
		hm.getStaffLnk().click();	
		StaffLogin sflogin=new StaffLogin(driver);
		sflogin.StaffLoginmeth(StaffId, staffpassword);
		
		//step 5:  Approve Pending Account in staff 		
		StaffHome sfhm=new StaffHome(driver);
		sfhm.getAprovePendingAccountBtn().click();
			
		// step 6: Enter the Invalid application number and  search 
		
		PendingCustomer pdCustomer=new PendingCustomer(driver);
		pdCustomer.PendingCustomerApplication(ApplicationNo);
			
		//Step 7: Application not found Pop message display		
	
		weblib.swithToAlertWindowAndAccpect(driver, ExpectedResult);			
		
		// step 8 : logout staff login applicaiton
			
		sfhm.getStaffLogoutBtn().click();
		
		//step 9: close the browser
		driver.quit();
				
		
	}
}


