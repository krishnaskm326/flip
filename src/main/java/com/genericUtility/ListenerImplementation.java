package com.genericUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.genericUtility.JavaUtilitity;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener {
	ExtentReports reports;
	ExtentTest test;
	JavaUtilitity jlib=new JavaUtilitity();

	public void onTestStart(ITestResult result) {
		
		String MethodName = result.getMethod().getMethodName();
		test = reports.createTest(MethodName);
		Reporter.log(MethodName+"=======> test script execution started");
	}

	public void onTestSuccess(ITestResult result) {
		String MethodName = result.getMethod().getMethodName();
		test.log(Status.PASS,MethodName+"======>test script execution successful");
	}

	public void onTestFailure(ITestResult result) {
		
		try {
			String screenShotName = WebDriverUtility.getScreenShot(BaseClass.sdriver,result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(screenShotName);
		}
		catch(Throwable e) {
			e.printStackTrace();
			test.log(Status.FAIL, result.getThrowable());
			Reporter.log("Test script execution failed");
		}
		
		String MethodName = result.getMethod().getMethodName();
		EventFiringWebDriver edriver = new EventFiringWebDriver(BaseClass.sdriver);
		File src = edriver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/"+MethodName+".png");
		try {
			FileUtils.copyFile(src, dest);
		}
		catch(IOException e){
			e.printStackTrace();
					}
	}

	public void onTestSkipped(ITestResult result) {
		String MethodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, result.getThrowable());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	
	public void onStart(ITestContext context) {
		// create html report
				ExtentSparkReporter htmlReport=new ExtentSparkReporter("./ExtentReport/report"+jlib.random()+".html");
				htmlReport.config().setDocumentTitle("E-Commerce");
				htmlReport.config().setTheme(Theme.STANDARD);
				htmlReport.config().setReportName("ONLINE SHOPPING APPLICATION");
				
				reports = new ExtentReports();
				reports.attachReporter(htmlReport);
				reports.setSystemInfo("BaseBrowser","Chrome");
				reports.setSystemInfo("OS", "Windows");
				reports.setSystemInfo("BaseURL", " http://rmgtestingserver/domain/Online_Shopping_Application/");
				reports.setSystemInfo("ReporterName","Krishnamoorthi");
	}

	public void onFinish(ITestContext context) {
		//consolidate the report
		reports.flush();
	}

}
