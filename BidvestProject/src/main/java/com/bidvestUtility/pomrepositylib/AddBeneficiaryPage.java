package com.bidvestUtility.pomrepositylib;

import javax.management.loading.PrivateClassLoader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bidvest.genericutility.WebDriverUtlity;


/**
 * 
 * @author Manu
 *
 */
public class AddBeneficiaryPage extends WebDriverUtlity{
	
	WebDriver driver;
	public AddBeneficiaryPage(WebDriver driver)
	{
		this.driver=driver;
		 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="beneficiary_name")
	private WebElement BenficiaryNameTF;
	
	
	@FindBy(name="beneficiary_acno")
	private WebElement BeneficiaryAccountNoTf;
	
	@FindBy(name="Ifsc_code")
	private WebElement IfscCodeTF;
	
	@FindBy(name="beneficiary_acc_type")
	private WebElement AccountTypeDropdown;
	
	@FindBy(name="add_beneficiary_btn")
	private WebElement AddBtn;

	public WebElement getBenficiaryNameTF() {
		return BenficiaryNameTF;
	}

	public WebElement getBeneficiaryAccountNoTf() {
		return BeneficiaryAccountNoTf;
	}

	public WebElement getIfscCodeTF() {
		return IfscCodeTF;
	}

	public WebElement getAccountTypeDropdown() {
		return AccountTypeDropdown;
	}

	public WebElement getAddBtn() {
		return AddBtn;
	}
	
	//Business Lib
	public void AddBeneficiaryMeth(String Beneficiaryname,String BeneficiaryAcNo,String ifsccode,String AccountTypetext)
	{
		BenficiaryNameTF.sendKeys(Beneficiaryname);
		BeneficiaryAccountNoTf.sendKeys(BeneficiaryAcNo);
		IfscCodeTF.sendKeys(ifsccode);	
		select(AccountTypeDropdown, AccountTypetext);
		AddBtn.click();
		
	}
	

}
