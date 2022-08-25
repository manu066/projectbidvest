package com.bidvestUtility.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerLogin {
	
	public CustomerLogin(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	 @FindBy(name="customer_id")
		private WebElement CustomerIdEdt;
	   
	   @FindBy(name="password")
		private WebElement CustomerPosswordEdt;
	   
	   @FindBy(name="login-btn")
	   private WebElement loginbtn;
	   
	   
	   public void CustomerLoginMeth(String username,String password)
	   {
		   CustomerIdEdt.sendKeys(username);
		   CustomerPosswordEdt.sendKeys(password);
		   loginbtn.click();
		   
	   }


	public WebElement getCustomerIdEdt() {
		return CustomerIdEdt;
	}


	public WebElement getCustomerPosswordEdt() {
		return CustomerPosswordEdt;
	}


	public WebElement getLoginbtn() {
		return loginbtn;
	}
	   

}
