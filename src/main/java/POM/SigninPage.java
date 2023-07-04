package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class SigninPage {
	//-------------------------------------------------------------------//
	@FindBy(name = "email")
	private WebElement userNameTF;
	@FindBy(name = "password")
	private WebElement passwordTF;
	@FindBy(name = "login" )
	private WebElement loginBtn;
	//-------------------------------------------------------------------//
	@FindBy(id="fullname")
	private WebElement fullNameTF;
	@FindBy(id="email")
	private WebElement emailAddressTF;
	@FindBy(id="contactno")
	private WebElement contactNoTF;
	@FindBy(id="password")
	private WebElement createPasswordTF;
	@FindBy(id="confirmpassword")
	private WebElement confirmPasswordTF;
	@FindBys({@FindBy(id="submit"),@FindBy(name="submit")})
	private WebElement clkSignUpBtn;
	//---------------------------------------------------------------------//
	
	public WebElement getFullNameTF() {
		return fullNameTF;
	}
	public WebElement getEmailAddressTF() {
		return emailAddressTF;
	}
	public WebElement getContactNoTF() {
		return contactNoTF;
	}
	public WebElement getCreatePasswordTF() {
		return createPasswordTF;
	}
	public WebElement getConfirmPasswordTF() {
		return confirmPasswordTF;
	}
	public WebElement getClkSignUpBtn() {
		return clkSignUpBtn;
	}
	
	public SigninPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//----------------------------------------------------//
	public WebElement getUserNameTF() {
		return userNameTF;
	}
	public WebElement getPasswordTF() {
		return passwordTF;
	}
	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
public void userSignUp(String firstName,String emailAddress,String contactNo,String password,String reEnterPassword) {
		fullNameTF.sendKeys(firstName);
		emailAddressTF.sendKeys(emailAddress);
		contactNoTF.sendKeys(contactNo);
		createPasswordTF.sendKeys(password);
		confirmPasswordTF.sendKeys(reEnterPassword);
		clkSignUpBtn.click();
	}
		public void userSignIn(String userName,String password) {
		userNameTF.sendKeys(userName);
		passwordTF.sendKeys(password);
		loginBtn.click();
	}
	
	

}
