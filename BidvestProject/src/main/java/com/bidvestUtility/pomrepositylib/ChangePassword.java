package com.bidvestUtility.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePassword {
	
	public ChangePassword(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getOldPasswordTF() {
		return OldPasswordTF;
	}

	public WebElement getConfmTF() {
		return ConfmTF;
	}

	public WebElement getNewpasswordTf() {
		return NewpasswordTf;
	}

	public WebElement getChangeBtn() {
		return ChangeBtn;
	}

	@FindBy(name="oldpass")
	private WebElement OldPasswordTF;
	
	@FindBy(name="cnfrm")
	private WebElement ConfmTF;
	
	@FindBy(name="newpass")
	private WebElement NewpasswordTf;
	
	@FindBy(name="change_pass")
	private WebElement ChangeBtn;
	
	
	public void ChangePasswordMeth(String OldPasswd,String Confoldpass,String NewPasswd)
	{
		OldPasswordTF.sendKeys(OldPasswd);
		ConfmTF.sendKeys(Confoldpass);
		NewpasswordTf.sendKeys(NewPasswd);
		ChangeBtn.click();
		
	}
	
	

}
