package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePages.BasePage;

public class LoginPage extends BasePage{
	
	public WebDriver driver;
	public WebDriverWait wait;

	public LoginPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(id="user-name")
	public WebElement txtUsername;
	
	@FindBy(id="password")
	public WebElement txtPassword;
	
	@FindBy(css = "#login-button")
	public WebElement btnLogin;
	
	@FindBy(xpath="//h3[text()='Epic sadface: Sorry, this user has been locked out.']")
	public WebElement msgLockError;
	
	
	public void setUsername(String username) {
		txtUsername.sendKeys(username);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clkLoginBtn() {
		btnLogin.click();
	}
	
	public String msgLockErrorReturn() {
		wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(msgLockError));
		return msgLockError.getText();
	}
}
