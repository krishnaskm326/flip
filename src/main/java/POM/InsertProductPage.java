package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.genericUtility.ExcelUtility;
import com.genericUtility.WebDriverUtility;

public class InsertProductPage {

	@FindBy(name="category")
	private WebElement chosseCategory;
	@FindBy(name="subcategory")
	private WebElement chooseSubCategory;
	@FindBy(name="productName")
	private WebElement productNameTF;
	@FindBy(name="productCompany")
	private WebElement produCompany;
	@FindBy(name="productpricebd")
	private WebElement produPriceBD;
	@FindBy(name="productprice")
	private WebElement productPriceAD;
	@FindBy(name="productDescription")
	private WebElement productDescription;
	@FindBy(name="productShippingcharge")
	private WebElement productShippingCharge;
	@FindBy(name="productAvailability")
	private WebElement StockAvailability;
	@FindBy(name="productimage1")
	private WebElement image1;
	@FindBy(name="productimage2")
	private WebElement image2;
	@FindBy(name="productimage3")
	private WebElement image3;
	@FindBy(name ="submit")
	private WebElement clkInsertBtn;
	
	public WebElement getClkInsertBtn() {
		return clkInsertBtn;
	}
	
	public WebElement getChosseCategory() {
		return chosseCategory;
	}
	public WebElement getChooseSubCategory() {
		return chooseSubCategory;
	}
	public WebElement getProductNameTF() {
		return productNameTF;
	}
	public WebElement getProduCompanyTF() {
		return produCompany;
	}
	public WebElement getProduPriceBD() {
		return produPriceBD;
	}
	public WebElement getProductPriceAD() {
		return productPriceAD;
	}
	public WebElement getProductDescription() {
		return productDescription;
	}
	public WebElement getProductShippingCharge() {
		return productShippingCharge;
	}
	public WebElement getStockAvailability() {
		return StockAvailability;
	}
	public WebElement getImage1() {
		return image1;
	}
	public WebElement getImage2() {
		return image2;
	}
	public WebElement getImage3() {
		return image3;
	}



	public void insertProductCategoryAndSubCategory(WebDriverUtility wLib,String categName,String subCategName) {
		//chosseCategory
		
		wLib.select(categName, chosseCategory);
		//chooseSubCategory;
		wLib.select(subCategName, chooseSubCategory);
	}
	
	public void insertDetailsOfTF(WebDriver driver,ExcelUtility eLib,String sheetName ) throws Throwable 
	{
		eLib.fetchMultipleDataUsingHashmap(sheetName, eLib, driver);
		
	}
	public void stockAvailability(WebDriverUtility wLib,String visibleText) {
		wLib.select(visibleText, StockAvailability);
	}
	public void uploadImgInInserproduct(WebDriverUtility wLib,String path) {
		wLib.fileClass(path);
		image1.sendKeys(path);
		image2.sendKeys(path);
		image3.sendKeys(path);
		clkInsertBtn.click();

	}

}

