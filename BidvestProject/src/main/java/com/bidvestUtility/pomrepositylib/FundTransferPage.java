package com.bidvestUtility.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bidvest.genericutility.WebDriverUtlity;

public class FundTransferPage extends WebDriverUtlity {
	
	WebDriver driver;
	
	public FundTransferPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
  public WebElement getAddBeneficiaryBtn() {
		return AddBeneficiaryBtn;
	}

	public WebElement getSelectBeneficiaryDropdown() {
		return SelectBeneficiaryDropdown;
	}

	public WebElement getAmountTF() {
		return AmountTF;
	}

	public WebElement getRemarkTF() {
		return RemarkTF;
	}

	public WebElement getSendBtn() {
		return SendBtn;
	}

	public WebElement getDeleteBeneficiaryBtn() {
		return DeleteBeneficiaryBtn;
	}

	public WebElement getViewBeneficairyBtn() {
		return ViewBeneficairyBtn;
	}


@FindBy(name="add_beneficiary")
  private WebElement AddBeneficiaryBtn;
  
  @FindBy(name="beneficiary") 
private WebElement SelectBeneficiaryDropdown;
  
  @FindBy(name="trnsf_amount")
  private WebElement AmountTF;
  
  @FindBy(name="trnsf_remark")
  private WebElement RemarkTF;
  
  @FindBy(name="fnd_trns_btn")
  private WebElement SendBtn;

  @FindBy(name="delete_beneficiary")
  private WebElement DeleteBeneficiaryBtn;
  
  @FindBy(name="view_beneficiary")
  private WebElement ViewBeneficairyBtn;
  
  
  public void FundTransferMet(String index,String Amount,String Remark)
  {
	  select(SelectBeneficiaryDropdown,index);
	  AmountTF.sendKeys(Amount);
	  RemarkTF.sendKeys(Remark);
	  SendBtn.click();
	  
  }
  

}
