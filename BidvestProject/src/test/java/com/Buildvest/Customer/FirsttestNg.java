package com.Buildvest.Customer;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;
import com.bidvest.genericutility.BaseClass;
import com.bidvest.genericutility.ReTryImpclass;
import com.bidvestUtility.pomrepositylib.AddBeneficiaryPage;
import com.bidvestUtility.pomrepositylib.ChangePassword;
import com.bidvestUtility.pomrepositylib.CustomerLogin;
import com.bidvestUtility.pomrepositylib.CustomerProfile;
import com.bidvestUtility.pomrepositylib.FundTransferPage;
import com.bidvestUtility.pomrepositylib.OnlineBankingSystemHome;
import com.bidvestUtility.pomrepositylib.PendingCustomer;
import com.bidvestUtility.pomrepositylib.StaffHome;
import com.bidvestUtility.pomrepositylib.ViewBeneficiary;

@Listeners(com.bidvest.genericutility.ListenerImpleclass.class)
public class FirsttestNg extends BaseClass {

	@Test(priority = 1,groups = "regression",retryAnalyzer =ReTryImpclass.class )
	public void addBenificiaryYourselfTest() throws Throwable {

		// step2:Read Data From Excel Sheet
		String Beneficaryname = ExcelLib.getExcelData("Sheet1", 11, 2);
		String BeneficaryAcNo = ExcelLib.getExcelData("Sheet1", 11, 3);
		String Ifsccode = ExcelLib.getExcelData("Sheet1", 11, 4);
		String AccountType = ExcelLib.getExcelData("Sheet1", 11, 5);
		String ExpectedResult = ExcelLib.getExcelData("Sheet1", 11, 6);

		// step 1: click on Fund Transfer

		CustomerProfile profile = new CustomerProfile(driver);

		profile.getFundTransferBtn().click();

		// step 7: click on Add beneficiary, Enter all the Details

		FundTransferPage fund = new FundTransferPage(driver);

		// POM Repository
		fund.getAddBeneficiaryBtn().click();
		AddBeneficiaryPage Beneficiary = new AddBeneficiaryPage(driver);
		Beneficiary.getBenficiaryNameTF().sendKeys(Beneficaryname);
		Beneficiary.getBeneficiaryAccountNoTf().sendKeys(BeneficaryAcNo);
		Beneficiary.getIfscCodeTF().sendKeys(Ifsccode);

		// Pom Repolib
		WebElement AccountTypeDropdown = Beneficiary.getAccountTypeDropdown();
		weblib.select(AccountTypeDropdown, AccountType);

		Beneficiary.getAddBtn().click();

		// step 8: Add beneficiary Pop message
	
		String actualResult = weblib.swithToAlertWindowAndAccpectvalidate(driver);
		Assert.assertEquals(actualResult, ExpectedResult);
		

	}


    
    @Test (priority = 2,groups = "regression")
	public void  ToVerifyCustomerAddBeneficiarythengotoFundTransferPageLogoutApplicationTest()
			throws Throwable {

		// step:Read Data From Excel Sheet 
		String Beneficaryname = ExcelLib.getExcelData("Sheet1", 14, 2); 
		String BeneficaryAcNo =ExcelLib.getExcelData("Sheet1", 14, 3); 
		String Ifsccode =ExcelLib.getExcelData("Sheet1", 14, 4); 
		String AccountType =ExcelLib.getExcelData("Sheet1", 14, 5); 
		String ExpectedResultPopmesage = ExcelLib.getExcelData("Sheet1", 14, 6); 
		String ActualResult = null;
		// step 1: click on Fund Transfer
		CustomerProfile profile = new CustomerProfile(driver);
		profile.getFundTransferBtn().click();

		// step 2: click on Add beneficiary, Enter all the Details

		FundTransferPage fund = new FundTransferPage(driver);
		fund.getAddBeneficiaryBtn().click();

		AddBeneficiaryPage Addbeneficiary = new AddBeneficiaryPage(driver);
		Addbeneficiary.AddBeneficiaryMeth(Beneficaryname, BeneficaryAcNo, Ifsccode,
				AccountType);

		// step 3: Add beneficiary Pop message
		weblib.swithToAlertWindowAndAccpect(driver, ExpectedResultPopmesage);

		// step 9: click on Fund Transfer

		profile.getFundTransferBtn().click();

		// step 4: Fetch the beneficiary from "Select Beneficiary" Drop down
		WebElement SelectBeneficiaryDropdown=fund.getSelectBeneficiaryDropdown();
		List<WebElement> Beneficiarynames = weblib.select(SelectBeneficiaryDropdown);

		ArrayList<String> beneficiaryNameAc = new ArrayList<String>();

		for (WebElement beneficiaryNameAcDrop : Beneficiarynames) {

			String BNA = beneficiaryNameAcDrop.getText(); beneficiaryNameAc.add(BNA);

		}

		// Step 5 - Compare add beneficiary and fundTransfer "Select Beneficiary"Dropdown

		String Expected = Beneficaryname + "-" + BeneficaryAcNo;

		boolean flag = false;
		

		for (String ActualResultdata : beneficiaryNameAc) 
		{
			
			

			if (Expected.equals(ActualResultdata)) 
			{
			   
				ActualResult=ActualResultdata;
			//	System.out.println("TEST Case Pass"); 
			//	System.out.println(ExpectedResult);
				flag = true; break;

			} 
			else 
			{ 
				flag = false; 
				}

		} 
		//if (flag == false) { System.out.println("Test Case Fail"); }
		//Assert.assertEquals(ActualResult, Expected);
		//Assert.assertTrue(flag);
		Assert.fail("we fail testcase 3");
		
		
	}




	@Test(priority = 3,groups = "regression")
	public void ViewBenificiaryAferAddingBenificiaryTest() throws Throwable
	{


		// step1:Read Data From Excel Sheet
		String Beneficaryname=ExcelLib.getExcelData("Sheet1", 1, 2);
		String BeneficaryAcNo=ExcelLib.getExcelData("Sheet1", 1, 3);
		String Ifsccode=ExcelLib.getExcelData("Sheet1", 1, 4);
		String Status=ExcelLib.getExcelData("Sheet1", 1, 5);

		//step 2 Click on fundTransfer page display

		CustomerProfile profile=new CustomerProfile(driver);
		profile.getFundTransferBtn().click();

		// step 3: click on View Beneficiary 
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

	}

	@Test(priority = 4,groups = "regression")
	public void ChangePasswordAndLoginWithNewPassword() throws Throwable
	{
		String CustomerId2=ProFile.getPropertyKeyValue("customerId2");
		String custom2password=ProFile.getPropertyKeyValue("custom2password");
		// step2:Read Data From Excel Sheet

		String NewPassword=ExcelLib.getExcelData("Sheet1", 8, 2);
		String ExpectedResult=ExcelLib.getExcelData("Sheet1", 8, 3);

		CustomerProfile profile=new CustomerProfile(driver);

		//step 3: click on "change Password " button 
		//CustomerProfile profile=new CustomerProfile(driver);
		profile.getChangePasswordBtn().click();
		ChangePassword change=new ChangePassword(driver);
		change.ChangePasswordMeth(custom2password, custom2password,NewPassword);

		//Step 7: Customer Password changed sucessfully pop message display
		
		//weblib.swithToAlertWindowAndAccpect(driver, ExpectedResult);
		String ActualResult = weblib.swithToAlertWindowAndAccpectvalidate(driver);
		Assert.assertEquals(ActualResult, ExpectedResult);

		// step 8: Logout the Customer Account
		
		profile.getCustomerLogoutBtn().click();		

		// step 9 : login with new Password  
		CustomerLogin login=new CustomerLogin(driver); 
		login.CustomerLoginMeth(CustomerId2, NewPassword);
	}




}
