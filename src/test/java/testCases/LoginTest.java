package testCases;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseTests.BaseTest;

public class LoginTest extends BaseTest{
	public String password="secret_sauce";
	
	@Test(priority = 1)
	public void logintest_with_valid_creds() {
		logger=LogManager.getLogger(this.getClass());
		logger.info("*****Logging with Valid credential Testcase is running******");
		lp.setUsername("standard_user");
		logger.info("UserName Entered...");
		lp.setPassword(password);
		logger.info("Password Entered...");
		lp.clkLoginBtn();
		logger.info("Login Button Clicked...");
		logger.info("*****Logging with Valid credential Testcase PASSED******");
	}
	
	@Test(priority = 2)
	public void logintest_with_locked_creds() {
		lp.setUsername("locked_out_user");
		lp.setPassword(password);
		lp.clkLoginBtn();
		String errMsg=lp.msgLockErrorReturn();
		Assert.assertEquals(errMsg, "Epic sadface: Sorry, this user has been locked out.");
		
	}

}
