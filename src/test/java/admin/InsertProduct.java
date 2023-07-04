package admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InsertProduct {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//String path = "http://rmgtestingserver/domain/Online_Shopping_Application/admin";
		driver.get("http://rmgtestingserver/domain/Online_Shopping_Application/admin");

		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("Test@123");
		driver.findElement(By.xpath("//button[.='Login']")).click();
		driver.findElement(By.xpath("//a[.='Insert Product ']")).click();
		WebElement element = driver.findElement(By.name("category"));
		Select s=new Select(element);
		s.selectByVisibleText("womens");
		WebElement element1 = driver.findElement(By.name("subcategory"));
		Select s1=new Select(element1);
		s1.selectByVisibleText("t-shirts");
		driver.findElement(By.name("productName")).sendKeys("seventy eight");
		driver.findElement(By.name("productCompany")).sendKeys("k&k company");
		driver.findElement(By.name("productpricebd")).sendKeys("200");
		driver.findElement(By.name("productprice")).sendKeys("150");
		driver.findElement(By.name("productDescription")).sendKeys("it gives good look");
		driver.findElement(By.name("productShippingcharge")).sendKeys("20");
		WebElement element2 = driver.findElement(By.name("productAvailability"));
		Select s3=new Select(element2);
		s3.selectByVisibleText("In Stock");
		
		File f=new File("./src/test/resources/pic1.png");
		String path=f.getAbsolutePath();
		driver.findElement(By.id("productimage1")).sendKeys(path);
		
		driver.findElement(By.name("productimage2")).sendKeys(path);
		
		driver.findElement(By.name("productimage3")).sendKeys(path);
		driver.findElement(By.name("submit")).click();
		 String result = driver.findElement(By.xpath("//div[contains(.,'Product Inserted Successfully !!')]/strong")).getText();
		 System.out.println(result);
		 if(result.contains("Well done")) {
			 System.out.println(result+"product added successfully"+" TC Pass ");
		 }
	}

}
