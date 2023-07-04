package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {
	@FindBy(xpath="(//td/a[@title='Track order'])[2]")
	private WebElement clkTrackBtn;

	public WebElement getClkTrackBtn() {
		return clkTrackBtn;
	}
	public OrderHistoryPage(WebDriver driver) {
       	PageFactory.initElements(driver, this);
           }
	public void clkTrackBtn() {
	clkTrackBtn.click();
	}
	
	
	
}
