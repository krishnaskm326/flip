package POM;

import java.util.HashMap;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.genericUtility.ExcelUtility;
import com.genericUtility.WebDriverUtility;

public class SubCategoryPage {
	@FindBy(name="category")
	private WebElement chosseCategory;
	@FindBy(name="subcategory")
	private WebElement subCategoryTF;
	@FindBy(name="submit")
	private WebElement clkCreateBtn;
	public WebElement getChosseCategory() {
		return chosseCategory;
	}
	public WebElement getSubCategoryTF() {
		return subCategoryTF;
	}
	public WebElement getClkCreateBtn() {
		return clkCreateBtn;
	}

	public SubCategoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String createSubcategory(WebDriverUtility wLib,String categName,WebDriver driver,ExcelUtility eLib) throws Throwable {
		
		wLib.select(categName,chosseCategory);
		String subCategName=null;
		HashMap<String, String> map = eLib.readmultipleDataFromExcel("Sheet1");
		for(Entry<String,String> set:map.entrySet()) {
			if(set.getKey().equalsIgnoreCase("subCategoryName")) {
				subCategName=set.getValue();
				driver.findElement(By.name(set.getKey())).sendKeys(subCategName);
			}
			else {
				driver.findElement(By.name(set.getKey())).sendKeys();
			}
		}
		clkCreateBtn.click();
		return subCategName;
		
	}

}
