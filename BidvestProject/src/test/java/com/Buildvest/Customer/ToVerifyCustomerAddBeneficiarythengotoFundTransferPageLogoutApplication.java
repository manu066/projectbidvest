package com.Buildvest.Customer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

public class ToVerifyCustomerAddBeneficiarythengotoFundTransferPageLogoutApplication {

	public static void main(String[] args) throws Throwable {

		WebDriver driver = null;
		ExcelUtility ExcelLib = new ExcelUtility();
		FileUtility ProFile = new FileUtility();
		JavaUtility JavaLib = new JavaUtility();
		WebDriverUtlity weblib = new WebDriverUtlity();

		// step 1: Read Data from Properties file
		String URL = ProFile.getPropertyKeyValue("url");
		String StaffId = ProFile.getPropertyKeyValue("StaffId");
		String staffpassword = ProFile.getPropertyKeyValue("staffpassword");
		String CustomerId1 = ProFile.getPropertyKeyValue("customerId1");
		String custom1password = ProFile.getPropertyKeyValue("custom1password");

		String CustomerId2 = ProFile.getPropertyKeyValue("customerId2");
		String custom2password = ProFile.getPropertyKeyValue("custom2password");
		String Browser = ProFile.getPropertyKeyValue("Browser");

		// step2:Read Data From Excel Sheet
		String Beneficaryname = ExcelLib.getExcelData("Sheet1", 14, 2);
		String BeneficaryAcNo = ExcelLib.getExcelData("Sheet1", 14, 3);
		String Ifsccode = ExcelLib.getExcelData("Sheet1", 14, 4);
		String AccountType = ExcelLib.getExcelData("Sheet1", 14, 5);
		String ExpectedResultPopmesage = ExcelLib.getExcelData("Sheet1", 14, 6);

		// Step3: Launch the Browser ---

		if (Browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (Browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();

		} else {
			System.out.println("Invalid Browser");
		}

		driver.get(URL);
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		weblib.waitForElementInDOM(driver);

		// step 4: Mouseover on Internet Banking page
		OnlineBankingSystemHome hm = new OnlineBankingSystemHome(driver);
		WebElement ele = hm.getInternetBankingMouseOvrElmt(); // element from pom repositylib
		weblib.mouseOverOnElement(driver, ele);

		hm.getLoginBtn().click();

		// driver.findElement(By.xpath("//li[.='Login ']")).click();

		// step 5: login to Internet Banking Using customer id and password from
		// property file
		CustomerLogin login = new CustomerLogin(driver);
		login.CustomerLoginMeth(CustomerId1, custom1password);

		// step 6: click on Fund Transfer
		CustomerProfile profile = new CustomerProfile(driver);
		profile.getFundTransferBtn().click();

		// step 7: click on Add beneficiary, Enter all the Details

		FundTransferPage fund = new FundTransferPage(driver);
		fund.getAddBeneficiaryBtn().click();
		
		AddBeneficiaryPage Addbeneficiary = new AddBeneficiaryPage(driver);
		Addbeneficiary.AddBeneficiaryMeth(Beneficaryname, BeneficaryAcNo, Ifsccode, AccountType);
		
		// step 8: Add beneficiary Pop message
		weblib.swithToAlertWindowAndAccpect(driver, ExpectedResultPopmesage);

		// step 9: click on Fund Transfer
	
		profile.getFundTransferBtn().click();

		// step 10: Fetch the beneficiary from "Select Beneficiary" Drop down
		WebElement SelectBeneficiaryDropdown=fund.getSelectBeneficiaryDropdown();
		List<WebElement> Beneficiarynames = weblib.select(SelectBeneficiaryDropdown);
		
		

		ArrayList<String> beneficiaryNameAc = new ArrayList<String>();

		for (WebElement beneficiaryNameAcDrop : Beneficiarynames) {

			String BNA = beneficiaryNameAcDrop.getText();
			beneficiaryNameAc.add(BNA);

		}

		// Step 11 - Compare add beneficiary and fundTransfer "Select Beneficiary" Dropdown
	
		String ActualResult = Beneficaryname + "-" + BeneficaryAcNo;

		boolean flag = false;

		for (String ExpectedResult : beneficiaryNameAc) {

			if (ExpectedResult.equals(ActualResult)) {
				System.out.println("TEST Case Pass");
				System.out.println(ExpectedResult);
				flag = true;
				break;

			} else {
				flag = false;
			}

		}
		if (flag == false) {
			System.out.println("Test Case Fail");
		}

		// step 10 logout customer	
		profile.getCustomerLogoutBtn().click();		

		// step 11: close the application

		driver.quit();

	}

}
