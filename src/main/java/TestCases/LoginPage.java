package TestCases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends ObjectRepo {
	
	public void NavigateToUrl(String url) {
		Driver.get(url);
	}

	private void EnterUserId(String userId) {

		WebElement email = Driver.findElement(obj_userName);
		email.sendKeys(userId);
	}

	private void EnterPassword(String password) {
		WebElement passcode = Driver.findElement(obj_password);
		passcode.sendKeys(password);
	}
	
	private void ClickLogin(){
		WebElement login = Driver.findElement(obj_login);
		login.click();
		
		SeleniumWait().until(ExpectedConditions.elementToBeClickable(obj_addServer));
	}
	
	public boolean IsLoginSuccessfull()
	{
		//	Check Add Server button is showing or not.
		
		return  Driver.findElement(obj_addServer).isDisplayed();
	}
	
	public void LoginToApplication(String userId, String password) {
		EnterUserId(userId);
		EnterPassword(password);
		ClickLogin();

	}

}
