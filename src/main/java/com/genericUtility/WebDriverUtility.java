package com.genericUtility;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverUtility {
	
	public WebDriver openTheBrowser(WebDriver driver) {
		WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
		return driver;
	}
	/**
	 * this method is used to maximize the browser window during automation
	 * @author krishnamoorthi
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	/**
	 * this method is used to wait for the page load
	 * @param driver
	 */

	public void waitFOrPageLoad(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	}
	/**
	 * this method is used to wait until element to be visible
	 * @param driver
	 * @param element
	 */
	public void elementToBeVisible(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * this method is used to select data from dropdown list using value
	 * @param element
	 * @param value
	 */
	public void select(WebElement element, String value) {
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	/**
	 * this method is used to select data from dropdown list using index
	 * @param element
	 * @param index
	 */
	public void select(WebElement element,int index) {
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	/**
	 * this method is used to select data from dropdown list using visible text
	 * @param visibleText
	 * @param element
	 */
	public void select(String visibleText, WebElement element) {
		Select sel=new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	/**
	 * this method will perform mousehover action
	 * @param driver
	 * @param element
	 */
	public void mouseHover(WebDriver driver,WebElement element) {
		Actions act=new Actions(driver); 
		act.moveToElement(element).perform();
	}
	/**
	 * this method will perform drag and drop action
	 * @param driver
	 * @param src
	 * @param dst
	 */
	public void dragAndDrop(WebDriver driver,WebElement src,WebElement dst) {
		Actions act = new Actions(driver);
		act.dragAndDrop(src, dst);
	}
	/**
	 * this method will perform double click on webpage
	 * @param driver
	 */
	public void doubleClick(WebDriver driver,WebElement element) {
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	/**
	 * this method will perform double click on webpage
	 * @param driver
	 */
	public void rightClick(WebDriver driver,WebElement element) {
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	/**
	 * this method will press enter Key
	 * @param driver
	 */
	public void enterKeyPress(WebDriver driver) {
		Actions act=new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}
	/**
	 * this method will press enter Key
	 * @param driver
	 * @throws Throwable
	 */
	public void enterKey(WebDriver driver) throws Throwable {
		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_ENTER);
	}
	/**
	 * this method is used to release  enter Key
	 * @param driver
	 * @throws Throwable
	 */
	public void enterRelease(WebDriver driver) throws Throwable {
		Robot rb=new Robot();
		rb.keyRelease(KeyEvent.VK_ENTER);
	}
	/**
	 * this method will switch the frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * this method will switch the frame based on nameorId
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver,String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	/**
	 * this method will switch the frame based on address
	 * @param driver
	 * @param address
	 */
	public void switchToFrame(WebDriver driver,WebElement address) {
		driver.switchTo().frame(address);
	}
	/**
	 * this method is used to accept alert popup
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	/**
	 * this method is used to cancel the alert popup
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	/**
	 * this method is used to transfer the control to another window
	 * @param driver
	 * @param partialTitle
	 */
	public void switchToWindow(WebDriver driver,String partialTitle) {
		//step1: use getWindowHandles to capture all window id's
		Set<String> windows = driver.getWindowHandles();
		//step2: iterate through the windows
		Iterator<String> it = windows.iterator();
		//step3: check whether there is next window
		while(it.hasNext()) {
			//step4:capture current window id
			String winId = it.next();
			//step5: switch to current window and capture title
			String currentWinTitle = driver.switchTo().window(winId).getTitle();
			//step6: check whether current window is expected 
			if(currentWinTitle.contains(partialTitle)) {
				break;
			}
		}
	}
	public String fileClass(String path) {
		File f=new File(path);
		String xpath=f.getAbsolutePath();
		return xpath;
	}
	/**
	 * this method is used to take screenshot on webpage
	 * @param driver
	 * @param screenShotName
	 * @return
	 * @throws Throwable
	 */
	public static String getScreenShot(WebDriver driver,String screenShotName) throws Throwable {
		TakesScreenshot	ts =(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String path=".\\screenshot\\"+screenShotName+".png";
		File dst = new File(path);
		FileUtils.copyFile(src, dst);
		return path;
	}
	/**
	 * this method will perform random scroll
	 * @param driver
	 */
	public void scrollBarAction(WebDriver driver) {
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,800)","");
	}
	/**
	 * this method will scroll until expected element is found
	 * @param driver
	 * @param element
	 */
	public void scrollAction(WebDriver driver,WebElement element) {
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		jse.executeScript("window.scrollBy(0,"+y+")", element);
	}
	}
