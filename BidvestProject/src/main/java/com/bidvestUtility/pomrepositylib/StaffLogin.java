package com.bidvestUtility.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StaffLogin {
	
	public StaffLogin(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy(name="staff_id")
	private WebElement StaffIdTf;
	
	@FindBy(name="password")
	private WebElement StaffPasswordTf;
	

	@FindBy(name="staff_login-btn")
	private WebElement StfLoginbtn;


	public WebElement getStaffIdTf() {
		return StaffIdTf;
	}


	public WebElement getStaffPasswordTf() {
		return StaffPasswordTf;
	}


	public WebElement getStfLoginbtn() {
		return StfLoginbtn;
	}
	
	public void StaffLoginmeth(String staffid,String password)
	{
		StaffIdTf.sendKeys(staffid);
		StaffPasswordTf.sendKeys(password);
		StfLoginbtn.click(); 
		
	}
	
	
	

}
