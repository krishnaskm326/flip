package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.genericUtility.ExcelUtility;

public class HomePage {
	
	@FindBy(xpath = "//a[@href='login.php']")
	private WebElement clkLoginBtn;
	
	@FindBy(xpath = "//a [text()='My Cart']")
	private WebElement myCartBtn;
	
	@FindBy(xpath = "//a[contains(.,'//a[contains(.,'Wishlist')]')]")
	private WebElement wishlistBtn;
	
	@FindBy(xpath ="//a[contains(.,'My Account')] " )
	private WebElement myAccountbtn;
	
	@FindBy(xpath = "//span[contains(.,'Track Order')]")
	private WebElement trackOrder;
	
	@FindBy(name = "product")
	private WebElement searchTF;
	
	@FindBy(xpath = "//button[@name = 'search']")
	private WebElement searchbtn;
	
	@FindBy(xpath = "(//a[contains(text(),'womens')])[1]")
	private WebElement clkCategory;
	@FindBy(xpath = "//a[@href='logout.php']")
	private WebElement clkLogOutBtn;
	
	
	                    public HomePage(WebDriver driver) 
	                    {
		                PageFactory.initElements(driver, this);
	                    }
	 public WebElement getClkLogOutBtn() {
		 return clkLogOutBtn;
	 }
	
	public WebElement getclkLoginBtn() {
		return clkLoginBtn;
	}
	public WebElement getMyCartBtn() {
		return myCartBtn;
	}
	public WebElement getWishlistBtn() {
		return wishlistBtn;
	}
	public WebElement getMyAccountbtn() {
		return myAccountbtn;
	}
	public WebElement getTrackOrder() {
		return trackOrder;
	}
	public WebElement getSearchTF() {
		return searchTF;
	}
	public WebElement getSearchbtn() {
		return searchbtn;
	}
	public WebElement getClkLoginBtn() {
		return clkLoginBtn;
	}

	public WebElement getClkCategory() {
		return clkCategory;
	}
	//-----------------------------
	
	public void clkLoginBtn() {
		clkLoginBtn.click();
	}
	public void clkLogOutBtn() {
		clkLogOutBtn.click();
	}
		public void MyCartBtn() {
		myCartBtn.click();
	}
	public void WishlistBtn() {
		wishlistBtn.click();
	}
	public void MyAccountbtn() {
		myAccountbtn.click();
	}
	public void TrackOrder() {
		trackOrder.click();
	}
	public void SearchProductTF(ExcelUtility eLib,String sheetname,int rownum,int columnnum) throws Throwable
	{
		String data = eLib.readDataFromExcel(sheetname,rownum,columnnum);
		searchTF.sendKeys(data);
		searchbtn.click();
	}

	public void clkCategory() {
		clkCategory.click();
	}
}
