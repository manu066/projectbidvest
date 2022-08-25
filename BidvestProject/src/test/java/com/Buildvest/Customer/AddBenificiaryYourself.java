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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.bidvest.genericutility.ExcelUtility;
import com.bidvest.genericutility.FileUtility;
import com.bidvest.genericutility.JavaUtility;
import com.bidvest.genericutility.WebDriverUtlity;
import com.bidvestUtility.pomrepositylib.AddBeneficiaryPage;
import com.bidvestUtility.pomrepositylib.CustomerLogin;
import com.bidvestUtility.pomrepositylib.CustomerProfile;
import com.bidvestUtility.pomrepositylib.FundTransferPage;
import com.bidvestUtility.pomrepositylib.OnlineBankingSystemHome;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddBenificiaryYourself {

	public static void main(String[] args) throws Throwable {



		WebDriver driver=null;
		ExcelUtility ExcelLib=new ExcelUtility();
		FileUtility ProFile=new FileUtility();
		JavaUtility JavaLib=new JavaUtility();
		WebDriverUtlity weblib=new WebDriverUtlity();

		//step 1: Read Data from Properties file

		String URL=ProFile.getPropertyKeyValue("url");
		String StaffId=ProFile.getPropertyKeyValue("StaffId");
		String staffpassword=ProFile.getPropertyKeyValue("staffpassword");
		String CustomerId1=ProFile.getPropertyKeyValue("customerId1");
		String custom1password=ProFile.getPropertyKeyValue("custom1password");

		String CustomerId2=ProFile.getPropertyKeyValue("customerId2");
		String custom2password=ProFile.getPropertyKeyValue("custom2password");
		String Browser=ProFile.getPropertyKeyValue("Browser");

		// step2:Read Data From Excel Sheet
		String Beneficaryname=ExcelLib.getExcelData("Sheet1", 11, 2);
		String BeneficaryAcNo=ExcelLib.getExcelData("Sheet1", 11, 3);
		String Ifsccode=ExcelLib.getExcelData("Sheet1", 11, 4);
		String AccountType=ExcelLib.getExcelData("Sheet1", 11, 5);
		String ExpectedResult=ExcelLib.getExcelData("Sheet1", 11, 6);

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
		WebElement ele = hm.getInternetBankingMouseOvrElmt();  //element from pomrepositylib
		weblib.mouseOverOnElement(driver,ele);
		hm.getLoginBtn().click();  // click on login

		//step 5: login to Internet Banking Using customer id and password from property file

		CustomerLogin login=new CustomerLogin(driver);   //login 	InternetBanking
		login.CustomerLoginMeth(CustomerId1, custom1password);// Business lib


		//step 6: click on Fund Transfer

		CustomerProfile profile=new CustomerProfile(driver);

		profile.getFundTransferBtn().click();     


		//step 7: click on Add beneficiary, Enter all the Details

		FundTransferPage fund=new FundTransferPage(driver);

		//POM Repository 
		fund.getAddBeneficiaryBtn().click();
		AddBeneficiaryPage Beneficiary=new AddBeneficiaryPage(driver);
		Beneficiary.getBenficiaryNameTF().sendKeys(Beneficaryname);
		Beneficiary.getBeneficiaryAccountNoTf().sendKeys(BeneficaryAcNo);
		Beneficiary.getIfscCodeTF().sendKeys(Ifsccode);

		//Pom Repolib
		WebElement AccountTypeDropdown =Beneficiary.getAccountTypeDropdown();
		weblib.select(AccountTypeDropdown, AccountType);

		Beneficiary.getAddBtn().click();

		//step 8: Add beneficiary Pop message 
		//weblib.swithToAlertWindowAndAccpect(driver, ExpectedResult);
		String actualResult = weblib.swithToAlertWindowAndAccpectvalidate(driver);
		Assert.assertEquals(actualResult, ExpectedResult);
	

		//step 9 logout  customer
		profile.getCustomerLogoutBtn();

		//step 11: close the application 

		driver.quit();



	}

}
