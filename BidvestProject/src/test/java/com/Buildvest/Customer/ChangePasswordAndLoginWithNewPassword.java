package com.Buildvest.Customer;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.bidvest.genericutility.ExcelUtility;
import com.bidvest.genericutility.FileUtility;
import com.bidvest.genericutility.JavaUtility;
import com.bidvest.genericutility.WebDriverUtlity;
import com.bidvestUtility.pomrepositylib.ChangePassword;
import com.bidvestUtility.pomrepositylib.CustomerLogin;
import com.bidvestUtility.pomrepositylib.CustomerProfile;
import com.bidvestUtility.pomrepositylib.OnlineBankingSystemHome;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChangePasswordAndLoginWithNewPassword {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;
		ExcelUtility ExcelLib=new ExcelUtility();
		FileUtility ProFile=new FileUtility();
		JavaUtility JavaLib=new JavaUtility();
		WebDriverUtlity weblib=new WebDriverUtlity();
		
		//step 1 : Read the Common data from Properties file
	
		
		String URL=ProFile.getPropertyKeyValue("url");
		String StaffId=ProFile.getPropertyKeyValue("StaffId");
		String staffpassword=ProFile.getPropertyKeyValue("staffpassword");
		String CustomerId1=ProFile.getPropertyKeyValue("customerId1");
		String custom1password=ProFile.getPropertyKeyValue("custom1password");
		
		String CustomerId2=ProFile.getPropertyKeyValue("customerId2");
		String custom2password=ProFile.getPropertyKeyValue("custom2password");
		String Browser=ProFile.getPropertyKeyValue("Browser");
		 
		
		// step2:Read Data From Excel Sheet

		String NewPassword=ExcelLib.getExcelData("Sheet1", 8, 2);
		String ExpectedResult=ExcelLib.getExcelData("Sheet1", 8, 3);

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
				
				// step 4: Mouseover on Internet Banking page
				  OnlineBankingSystemHome hm=new OnlineBankingSystemHome(driver);
					WebElement ele = hm.getInternetBankingMouseOvrElmt();  //element from pom repositylib
					weblib.mouseOverOnElement(driver,ele);
					
					hm.getLoginBtn().click();  // click on login
				
				//step 5: login to Internet Banking Using customer id and password from property file
						
					CustomerLogin login=new CustomerLogin(driver);   //login 	InternetBanking
					login.CustomerLoginMeth(CustomerId2, custom2password);// Business lib
				
				//step 6: click on "change Password " button 
				CustomerProfile profile=new CustomerProfile(driver);
				profile.getChangePasswordBtn().click();
				ChangePassword change=new ChangePassword(driver);
				change.ChangePasswordMeth(custom2password, custom2password,NewPassword);
				
				//Step 7: Customer Password changed sucessfully pop message display
				weblib.swithToAlertWindowAndAccpect(driver, ExpectedResult);
									
				// step 8: Logout the Customer Account
				profile.getCustomerLogoutBtn().click();		
				
				// step 9 : login with new Password  
						
				login.CustomerLoginMeth(CustomerId2, NewPassword);
				
				
				//step 10 logout  customer			
		
				profile.getCustomerLogoutBtn();
				
				
				//step 11: close the application 
				
				driver.quit();
				
				
				
				
				
				
				
				

	}

}
