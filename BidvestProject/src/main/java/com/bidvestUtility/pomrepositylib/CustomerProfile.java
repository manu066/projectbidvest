package com.bidvestUtility.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerProfile {
	
	
	public CustomerProfile(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getCustomerHomeBtn() {
		return CustomerHomeBtn;
	}


	public WebElement getCustomerLogoutBtn() {
		return CustomerLogoutBtn;
	}


	public WebElement getMyAccountMajorBtn() {
		return MyAccountMajorBtn;
	}


	public WebElement getMyProfileMajorBtn() {
		return MyProfileMajorBtn;
	}


	public WebElement getChangePasswordBtn() {
		return ChangePasswordBtn;
	}


	public WebElement getFundTransferBtn() {
		return FundTransferBtn;
	}


	public WebElement getStatementBtn() {
		return StatementBtn;
	}


	@FindBy(name="home")
	private WebElement CustomerHomeBtn;
	
	@FindBy(name="logout_btn")
	private WebElement CustomerLogoutBtn;
	
	@FindBy(xpath="//li[text()='My Account']")
	private WebElement MyAccountMajorBtn;
	
	@FindBy(xpath="//li[text()='My Profile']")
	private WebElement MyProfileMajorBtn;
	
	@FindBy(xpath="//li[text()='Change Password']")
	private WebElement ChangePasswordBtn;
	
	@FindBy(xpath="//li[text()='Fund Transfer']")
	private WebElement FundTransferBtn;
	
	
	@FindBy(xpath="//li[text()='Statement']")
	private WebElement StatementBtn;
	
	
	
	

}
