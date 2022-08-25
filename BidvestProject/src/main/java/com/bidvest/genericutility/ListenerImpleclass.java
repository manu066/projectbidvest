package com.bidvest.genericutility;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Timer;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.TimeFunc;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerImpleclass extends BaseClass implements ITestListener {

	public void onTestFailure(ITestResult result )
	{
	  String testName = result.getMethod().getMethodName();
	  String sysdate = JavaLib.getSystemDateInIST();
    
	  System.out.println(" Exectute === Listener ");
	  EventFiringWebDriver edriver=new EventFiringWebDriver(driver);
	  File srcfile=edriver.getScreenshotAs(OutputType.FILE);
	  try
	  {
		  FileUtils.copyFile(srcfile, new File("./screenshot/"+testName +sysdate+".png"));
	  }
	  catch(IOException e)
	  {
		  e.printStackTrace();
	  }
	}
}
