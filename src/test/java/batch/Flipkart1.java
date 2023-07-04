package batch;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart1 {

	@Test
	public void test3() throws Throwable {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//div/div/button[text()='✕']")).click();

		driver.findElement(By.name("q")).sendKeys("iphones");
		Thread.sleep(200);
		List<WebElement> autoSugg = driver.findElements(By.xpath("//ul[@class='col-12-12 _1MRYA1 _38UFBk']/descendant::div[@class='lrtEPN _17d0yO']"));
		int size=autoSugg.size();
		String a[]=new String[size];
		for(int i=0;i<size;i++) {
			a[i] = autoSugg.get(i).getText();
			System.out.println(a[i]);
		}
		for(int i=0;i<a.length;i++) {
			Thread.sleep(200);
			if(a[i].contains("iphone 14 pro")) {
				autoSugg.get(i).click();
			}
		}
		Thread.sleep(1000);
		String data = driver.findElement(By.xpath("//span[@class='_10Ermr']")).getText();
		int i=1;
		int fp=0;

		for(;;i++) {
			try {
				Thread.sleep(2000);
				List<WebElement> fPage = driver.findElements(By.xpath("//div[@class='_2kHMtA']/a"));
				fp=fp+fPage.size();
				System.out.println(i+" page "+fPage.size()); 
				driver.findElement(By.xpath("//span[text()='Next']")).click();
			}
			catch (Exception e) {
				break;
			}
		}

		System.out.println("total products "+fp);
		String st[]=data.split(" ");
		Assert.assertEquals(fp, Integer.parseInt(st[5]));
		driver.close();


	}

}
