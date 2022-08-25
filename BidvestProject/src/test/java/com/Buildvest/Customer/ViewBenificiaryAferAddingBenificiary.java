package com.Buildvest.Customer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.sl.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.bidvestUtility.pomrepositylib.CustomerLogin;
import com.bidvestUtility.pomrepositylib.CustomerProfile;
import com.bidvestUtility.pomrepositylib.FundTransferPage;
import com.bidvestUtility.pomrepositylib.OnlineBankingSystemHome;
import com.bidvestUtility.pomrepositylib.ViewBeneficiary;

import io.github.bonigarcia.wdm.WebDriverManager;



public class ViewBenificiaryAferAddingBenificiary {

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
		String Beneficaryname=ExcelLib.getExcelData("Sheet1", 1, 2);
		  String BeneficaryAcNo=ExcelLib.getExcelData("Sheet1", 1, 3);
		  String Ifsccode=ExcelLib.getExcelData("Sheet1", 1, 4);
		  String Status=ExcelLib.getExcelData("Sheet1", 1, 5);
		 
		// Step3: Launch the Browser ---

		if(Browser.equalsIgnoreCase("Chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();

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
		WebElement ele = hm.getInternetBankingMouseOvrElmt();			
		weblib.mouseOverOnElement(driver,ele);

			hm.getLoginBtn().click(); // click on login button
			
		//step 5: login to Internet Banking Using customer id and password from property file	
		CustomerLogin login=new CustomerLogin(driver);
		login.CustomerLoginMeth(CustomerId1, custom1password);
		
		//step 6 Click on fundTransfer page display
		
		CustomerProfile profile=new CustomerProfile(driver);
		profile.getFundTransferBtn().click();

		// step 7: click on View Beneficiary 
		FundTransferPage fund=new FundTransferPage(driver);
		fund.getViewBeneficairyBtn().click();	
		ViewBeneficiary view=new ViewBeneficiary(driver);
		List<WebElement> ViewBeneficiaryNameRef=view.getViewBeneficiaryNameTable();
		ArrayList<String> ViewBeneficiaryName =new ArrayList<String>();
		for (WebElement ele1 :  ViewBeneficiaryNameRef) {

			String ViewTableBeneficiaryname=ele1.getText();
			ViewBeneficiaryName.add(ViewTableBeneficiaryname);


		}

		//  verify Beneficiary Name		
		boolean flag=false;
		for (String ActualBeneficiaryname: ViewBeneficiaryName) {

			if(ActualBeneficiaryname.equals(Beneficaryname))
			{
				System.out.println("TestCase Pass");
				System.out.println(ActualBeneficiaryname);
				flag=true;
				break;
			}

		}
		if(flag=false)
		{
			System.out.println("TestCase Fail");
		}

		//step 8 : Logout Application	
		profile.getCustomerLogoutBtn().click();

		//step 9: close the Browser
		driver.close();




	}

}
