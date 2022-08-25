package com.Buildvest.Staff;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bidvest.genericutility.BaseClassStaff;
import com.bidvestUtility.pomrepositylib.PendingCustomer;
import com.bidvestUtility.pomrepositylib.StaffHome;

public class StaffTestng extends BaseClassStaff{
	
	
	@Test
	public void verifyAccountInApprovePendingAccountTest() throws Throwable
	{
		
		// step1:Read Data From Excel Sheet
		
				String ApplicationNo=ExcelLib.getExcelData("Sheet1", 4, 2);
		        String ExpectedResult=ExcelLib.getExcelData("Sheet1", 4, 3);
		      //step 5:  Approve Pending Account in staff 		
				StaffHome sfhm=new StaffHome(driver);
				sfhm.getAprovePendingAccountBtn().click();
					
				// step 2: Enter the Invalid application number and  search 
				
				PendingCustomer pdCustomer=new PendingCustomer(driver);
				pdCustomer.PendingCustomerApplication(ApplicationNo);
					
				//Step 3: Application not found Pop message display		
			
				//weblib.swithToAlertWindowAndAccpect(driver, ExpectedResult);	
				String ActualResult = weblib.swithToAlertWindowAndAccpectvalidate(driver);
				Assert.assertEquals(ActualResult, ExpectedResult, "ResultMatching");
		
	}
	

}
