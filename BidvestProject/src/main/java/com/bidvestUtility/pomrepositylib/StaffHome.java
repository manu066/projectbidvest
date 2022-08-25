package com.bidvestUtility.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StaffHome {

	
	public StaffHome(WebDriver driver) {
		
		PageFactory.initElements(driver,this);
	
	}
	
	@FindBy(name="apprvac")
	private WebElement AprovePendingAccountBtn;
	
	@FindBy(name="viewdet")
	private WebElement ViewActiveCustomerBtn;
	
	@FindBy(name="view_cust_by_ac")
	private WebElement ViewCustomerByAcNoBtn;
	
	@FindBy(name="del_cust")
	private WebElement DeleteCustomerAcBtn;
	
	@FindBy(name="credit_cust_ac")
	private WebElement CreditCustomerAmtBtn;

	
	public WebElement getAprovePendingAccountBtn() {
		return AprovePendingAccountBtn;
	}

	public WebElement getViewActiveCustomerBtn() {
		return ViewActiveCustomerBtn;
	}

	public WebElement getViewCustomerByAcNoBtn() {
		return ViewCustomerByAcNoBtn;
	}

	public WebElement getDeleteCustomerAcBtn() {
		return DeleteCustomerAcBtn;
	}

	public WebElement getCreditCustomerAmtBtn() {
		return CreditCustomerAmtBtn;
	}

	public WebElement getStaffHomeBtn() {
		return StaffHomeBtn;
	}

	public WebElement getStaffLogoutBtn() {
		return StaffLogoutBtn;
	}

	@FindBy(name="home")
	private WebElement StaffHomeBtn;
	
	@FindBy(name="logout_btn")
	private WebElement StaffLogoutBtn;
	
	
	
	
}
