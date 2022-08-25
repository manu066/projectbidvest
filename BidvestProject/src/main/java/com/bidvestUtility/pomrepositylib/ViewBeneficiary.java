package com.bidvestUtility.pomrepositylib;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ViewBeneficiary {
	
	WebDriver driver=null;
	public ViewBeneficiary(WebDriver driver)
	{ 
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="go_back")
	private WebElement Gobackbtn;
	
	@FindBy(xpath="//table/tbody/*/td[2]") 
	private List<WebElement> ViewBeneficiaryNameTable;
	
	@FindBy(xpath="//table/tbody/*/td[3]") 
	private List<WebElement> BeneficiaryAccountNoTable;
	
	@FindBy(xpath="//table/tbody/*/td[4]") 
	private List<WebElement> IfscCodeTable;
	
	
	public WebDriver getDriver() {
		return driver;
	}


	public List<WebElement> getViewBeneficiaryNameTable() {
		return ViewBeneficiaryNameTable;
	}


	public List<WebElement> getBeneficiaryAccountNoTable() {
		return BeneficiaryAccountNoTable;
	}

	public List<WebElement> getIfscCodeTable() {
		return IfscCodeTable;
	}


	public WebElement getGobackbtn() {
		return Gobackbtn;
	}

}
