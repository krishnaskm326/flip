package com.genericUtility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ListenerImplementation1 implements ITestListener{
	ExtentReports reports;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		test = reports.createTest(methodname);
		Reporter.log(methodname+" test started successfully");
	}

	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		test.log(Status.PASS, methodname+" test execution is pass");
	}

	public void onTestFailure(ITestResult result) {

		try {
			String screenShotName = WebDriverUtility.getScreenShot(BaseClass.sdriver,result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(screenShotName);
		}
		catch(Throwable e) {
			
			e.printStackTrace();
			test.log(Status.FAIL,result.getThrowable());
		}
		
			String methodname = result.getMethod().getMethodName();
			EventFiringWebDriver webd=new  EventFiringWebDriver(BaseClass.sdriver);
			
			File src=webd.getScreenshotAs(OutputType.FILE);
			File dest=new File("/ExtendReports/"+methodname+"+.png");
			try {
				FileUtils.copyFile(src, dest);
			}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodname+" test execution is skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		ExtentSparkReporter htmlreport=new ExtentSparkReporter("./ExtentReport/report.html");
		htmlreport.config().setTheme(Theme.STANDARD);
		htmlreport.config().setDocumentTitle("Custom Reporter");
		htmlreport.config().setReportName("online shopping application");

		reports=new ExtentReports();
		reports.attachReporter(htmlreport);
		reports.setSystemInfo("URI", "");
		reports.setSystemInfo("Domain", "");
		reports.setSystemInfo("Browser", "");
		reports.setSystemInfo("Operating system	", "");
		reports.setSystemInfo("Author", "Mr.krishna");
	}
	public void onFinish(ITestContext context) {
		reports.flush();
	}

}
