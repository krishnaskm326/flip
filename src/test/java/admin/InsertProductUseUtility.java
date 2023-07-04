package admin;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.genericUtility.ExcelUtility;
import com.genericUtility.WebDriverUtility;
import POM.CategoryPage;
import POM.ChangePasswordPage;
import POM.InsertProductPage;
import POM.SubCategoryPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InsertProductUseUtility {

		public void insertProduct() throws Throwable {
			WebDriverUtility wLib=new WebDriverUtility();
			ExcelUtility eLib=new ExcelUtility();
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
		ChangePasswordPage cpp=new ChangePasswordPage(driver);
		cpp.clkCategoryBtn();
		CategoryPage cp=new CategoryPage(driver);
		String category_Name = cp.createCategoryTF(eLib, driver);
		cpp.clkSubCategoryBtn();
		SubCategoryPage scp=new SubCategoryPage(driver);
		String subCategory_name = scp.createSubcategory(wLib, category_Name, driver, eLib);
		InsertProductPage ipp=new InsertProductPage();
		ipp.insertProductCategoryAndSubCategory(wLib,category_Name, subCategory_name);
		ipp.insertDetailsOfTF(driver, eLib, "Address");
		ipp.stockAvailability(wLib, "In Stock");
		String result = driver.findElement(By.xpath("//div[contains(.,'Product Inserted Successfully !!')]/strong")).getText();
		System.out.println(result);
		if(result.contains("Well done")) {
			System.out.println(result+"product added successfully"+" TC Pass ");
		}
	}

}

