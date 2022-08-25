package com.bidvestUtility.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PendingCustomer {
	
	public PendingCustomer(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="application_no")
	private WebElement ApplicationNoTf;
	
	@FindBy(name="search_application")
	private WebElement SearchBtn;
	
	@FindBy(name="approve_cust")
	private WebElement ApproveBtn;

	public WebElement getApplicationNoTf() {
		return ApplicationNoTf;
	}

	public WebElement getSearchBtn() {
		return SearchBtn;
	}

	public WebElement getApproveBtn() {
		return ApproveBtn;
	}
	
	public void PendingCustomerApplication(String AppNo)
	{
	
		
		ApplicationNoTf.sendKeys(AppNo);
		SearchBtn.click();
		
	}
	
	
	

}
