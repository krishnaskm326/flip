package admin;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import io.github.bonigarcia.wdm.WebDriverManager;

public class InsertProductFetchData {


	public static void main(String[] args) throws Throwable{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commondata.property");
		Properties pobj = new Properties();
		pobj.load(fis);
		//--------------------------------Common Data------------------------------------------------------------//
		String URL = pobj.getProperty("Admin_URL");
		String USERNAME = pobj.getProperty("Admin_un");
		String PASSWORD = pobj.getProperty("Admin_pwd");
		driver.get(URL);

		driver.findElement(By.name("username")).sendKeys(USERNAME);
		driver.findElement(By.name("password")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[.='Login']")).click();
		driver.findElement(By.xpath("//a[.='Insert Product ']")).click();
		WebElement element = driver.findElement(By.name("category"));
		Select s=new Select(element);
		s.selectByVisibleText("womens");
	    WebElement element1 = driver.findElement(By.name("subcategory"));
		Select s1=new Select(element1);
		s1.selectByVisibleText("t-shirts");
		//----------------------------------Test Data-------------------------------------------------------------//
		FileInputStream fis1 = new FileInputStream(".\\src\\test\\resources\\testdata.xlsx");
		Workbook wb1 = WorkbookFactory.create(fis1);
		Sheet sh = wb1.getSheet("Sheet2");
		
		HashMap<String, String> map=new HashMap<String, String>();
			for(int i=0;i<=sh.getLastRowNum();i++)
			{
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);
		}
		//------------------------------------------------------------------------------------------------// 
		for(Entry<String, String> set:map.entrySet())
		{
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
		
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
