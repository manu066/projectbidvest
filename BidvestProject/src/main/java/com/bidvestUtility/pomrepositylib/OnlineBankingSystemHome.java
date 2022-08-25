package com.bidvestUtility.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OnlineBankingSystemHome {
	
	
	public OnlineBankingSystemHome(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
   @FindBy(xpath="//a[contains(.,'Internet ')] ")
   private WebElement InternetBankingMouseOvrElmt ;
   
   @FindBy(xpath="//li[.='Login ']")
   private WebElement LoginBtn;	
   
  @FindBy(xpath="//li[text()='Register']")
  private WebElement RegisterBtn;
  
  @FindBy(xpath="//li[text()='Fund Transfer']")
  private WebElement FundTransferBtn;
  
  @FindBy(xpath="//a[text()='Home']")
  private WebElement HomeBtn;

  
  public WebElement getHomeBtn() {
	return HomeBtn;
}

public void setHomeBtn(WebElement homeBtn) {
	HomeBtn = homeBtn;
}

public WebElement getInternetBankingMouseOvrElmt() {
	return InternetBankingMouseOvrElmt;
}

public WebElement getLoginBtn() {
	return LoginBtn;
}

public WebElement getRegisterBtn() {
	return RegisterBtn;
}

public WebElement getFundTransferBtn() {
	return FundTransferBtn;
}

public WebElement getApplyDebitCardBtn() {
	return ApplyDebitCardBtn;
}

public WebElement getOpenAccountBtn() {
	return OpenAccountBtn;
}

public WebElement getStaffLnk() {
	return StaffLnk;
}

@FindBy(xpath="//li[text()='Apply Debit Card']")
  private WebElement ApplyDebitCardBtn;
  
  @FindBy(xpath="//li[text()='Open Account']")
  private WebElement OpenAccountBtn;
  
  @FindBy(xpath="//a[text()='Staff Login']")
  private WebElement StaffLnk;
  
  
   
	
}
