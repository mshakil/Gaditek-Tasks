package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ObjectRepo {

	protected static WebDriver Driver;

	public TestCases.LoginPage LoginPage() {
		return new TestCases.LoginPage();
	}
	
	public TestCases.TeamPage TeamPage(){
		return new TestCases.TeamPage();
	}
	
	public WebDriverWait SeleniumWait() {
		return  new WebDriverWait(Driver, 30);
	}
	
	//	LOGIN PAGE	//
	
	protected String WebUrl = "https://platform.cloudways.com/login";
	protected String UserName = "mshakil74@gmail.com";
	protected String Password = "Test123@@";
	protected String Team_Id = "m.s.h.akil74@gmail.com";
	protected String Team_Name = "Mubbashir";
	
	protected By obj_userName = By.cssSelector("input[id='userEmail']");
	protected By obj_password = By.cssSelector("input[name='password']");
	protected By obj_login =By.cssSelector("button[aria-label='LOGIN NOW']");
	protected By obj_addServer = By.cssSelector("button[ui-sref='server_create']");

	//	TEAM	//
	
	protected By obj_TeamMenu = By.cssSelector("li.team button[aria-label='Team']");
	protected By obj_AddTeamMember = By.cssSelector("button[aria-label='Add New Team Member']");
	protected By obj_addTeamMemberGrid = By.cssSelector("button[ui-sref='team_create']");
	
	protected By obj_userMail = By.cssSelector("input[name='email']");
	protected By obj_userCred = By.cssSelector("input[name='name']");
	
	protected By obj_jobTitleDropDown = By.cssSelector("md-select[name='jobTitle']");
	protected By obj_jobTitle (String title)
	{
		return By.cssSelector("md-option[value='"+title+"']");
	}
	
	//	TEAM MEMBER PERMISSION	//
	
	/*
	 * [checkbox]Billing Access	[done]
	 * [checkbox]Support Access
	 * [checkbox]Console Access	[done]
	 * 			[RadioButton] Full Access
	 * 			[RadioButton] Limited Access	[done]
	 * 						[Checkbox] Add Servers
	 * 						[Checkbox] Add-ons
	 * 						[Checkbox] Manage Projects
	 */						
	
	protected By Obj_CheckBoxTag = By.tagName("md-checkbox");
	protected By obj_BillingAccess = By.cssSelector("md-checkbox[aria-label='Billing Access']");
	protected By obj_ConsoleAccess = By.cssSelector("md-checkbox[aria-label='Console Access']");
	
	protected By obj_LimitedAccess = By.cssSelector("md-radio-button[aria-label='limited access']");
	
	protected By obj_AddServer = By.cssSelector("md-checkbox[aria-label='Add Server']");
	protected By obj_AddServerText = By.cssSelector("b.ng-binding");
	
	
	protected By obj_ServerPermissionPanel = By.cssSelector("div.pjt-tem-mang");
	protected By obj_ServerPermissionCheckBox = By.cssSelector("div.md-icon");
	protected By obj_ServerPermissionLable = By.cssSelector("div.md-labels");
	
	protected By obj_ServerSubOptionsPanel = By.cssSelector("div.pjt-tem-chk-sub");
	
	protected By obj_AddUserButton = By.cssSelector("button[aria-label='Add member']");
}
