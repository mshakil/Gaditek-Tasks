package TestCases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;

import java.util.*;

public class TeamPage extends ObjectRepo {

	public void OpenTeamPage() {
		WebElement teamPage = Driver.findElement(obj_TeamMenu);
		teamPage.click();

		SeleniumWait().until(ExpectedConditions.elementToBeClickable(obj_addTeamMemberGrid));
	}

	public void ClickAddNewTeamMember() {
		try {
			// IF No User Is Add On Server.

			WebElement newMember = Driver.findElement(obj_AddTeamMember);
			newMember.click();
		} catch (Exception ex) {
			// One or more user is Showing in Grid.

			WebElement newMember = Driver.findElement(obj_addTeamMemberGrid);
			newMember.click();
		}
	}

	private void Team_EnterEmail(String email) {

		WebElement mail = Driver.findElement(obj_userMail);
		mail.clear();
		mail.sendKeys(email);
	}

	private void Team_EnterName(String name) {
		WebElement username = Driver.findElement(obj_userCred);
		username.clear();
		username.sendKeys(name);
	}

	private void SelectJobTitle(String jobTitle) {
		WebElement jtDropdown = Driver.findElement(obj_jobTitleDropDown);
		jtDropdown.click();

		WebElement jt = Driver.findElement(obj_jobTitle(jobTitle));
		jt.click();
	}

	public void EnterTeamMemberInformation(String email, String name, String jobTitle) throws InterruptedException {
		Thread.sleep(10000);

		Team_EnterEmail(email);
		Team_EnterName(name);
		SelectJobTitle(jobTitle);
	}

	///////////////////// PERMISSIONS ///////////////////////////////

	private void CheckBillingAccess() {
		WebElement chkBxBilling = Driver.findElement(obj_BillingAccess);

		String isChecked = chkBxBilling.getAttribute("aria-checked");
		System.out.println("Billing Access Checked: \t" + isChecked);

		if (isChecked.equals("true"))
			return;
		else
			chkBxBilling.click();
	}

	private void CheckConsoleAccess() {
		WebElement chkBxConsole = Driver.findElement(obj_ConsoleAccess);

		String isChecked = chkBxConsole.getAttribute("aria-checked");
		System.out.println("Console Access Checked: \t" + isChecked);

		if (isChecked.equals("true"))
			return;
		else
			chkBxConsole.click();

		SeleniumWait().until(ExpectedConditions.elementToBeClickable(obj_LimitedAccess));
		Assert.assertTrue(Driver.findElement(obj_LimitedAccess).isDisplayed(), "Limited Access is not visible");
	}

	private void CheckLimitedAccess() {
		WebElement chkBxLimited = Driver.findElement(obj_LimitedAccess);

		String isChecked = chkBxLimited.getAttribute("aria-checked");
		System.out.println("Limited Access Checked: \t" + isChecked);

		if (isChecked.equals("true"))
			return;
		else
			chkBxLimited.click();

		VerifyLimitedAccessOptionsShowing();
	}

	private void VerifyLimitedAccessOptionsShowing() {
		List<WebElement> addServerOptions = Driver.findElements(obj_AddServer);
		ArrayList<String> expectedOptions = new ArrayList<String>();
		expectedOptions.add("Add Server");
		expectedOptions.add("Add-ons");
		expectedOptions.add("Manage Projects");

		ArrayList<String> actualOptions = new ArrayList<String>();

		for (WebElement webElement : addServerOptions) {
			String elementText = webElement.findElement(obj_AddServerText).getText();
			System.out.println(elementText);
			actualOptions.add(elementText);
		}
		assertEquals(actualOptions, expectedOptions);
	}

	private void MarkCheckedLimitedAccessOptions() {
		List<WebElement> addServerOptions = Driver.findElements(obj_AddServer);

		for (WebElement webElement : addServerOptions) {
			webElement.click();
		}
	}

	private void SelectServerRights() throws Exception {
		try {
			WebElement serverName = Driver.findElement(obj_ServerPermissionPanel).findElement(Obj_CheckBoxTag);
			((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView(true);", serverName);
			serverName.click();

			List<WebElement> fullAccess = Driver.findElement(obj_ServerPermissionPanel).findElements(Obj_CheckBoxTag);

			System.out.println("ELEMENT FOUND:\t" + fullAccess.size());

			if (fullAccess.size() > 1)
				;
			String actualText = fullAccess.get(1).getText().trim();

			assertEquals(actualText, "Full Access", "Full Access checkbox is not showing after click on Server Name");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new Exception("No element selected");
		}
	}

	private void UncheckFullAccess() throws Exception {
		try {
			List<WebElement> fullAccess = Driver.findElement(obj_ServerPermissionPanel).findElements(Obj_CheckBoxTag);

			System.out.println("ELEMENT FOUND:\t" + fullAccess.size());

			if (fullAccess.size() > 1)
				;
			{
				String actualText = fullAccess.get(1).getText().trim();

				if (actualText.equals("Full Access")) {
					fullAccess.get(1).click();
				}
			}

			int subOptionsSize = Driver.findElement(obj_ServerSubOptionsPanel).findElements(Obj_CheckBoxTag).size();

			System.out.println("Sub option size:\t" + fullAccess.size());
			assertEquals(subOptionsSize, 13, "Sub Options are not as expected");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new Exception("No element selected");
		}
	}

	private void CheckAllSubOptions() {

		List<WebElement> options = Driver.findElement(obj_ServerSubOptionsPanel).findElements(Obj_CheckBoxTag);

		for (WebElement webElement : options) {
			webElement.click();
		}
	}

	public void SelectPermissions() throws Exception {
		try {
			CheckBillingAccess();
			CheckConsoleAccess();

			CheckLimitedAccess();
			MarkCheckedLimitedAccessOptions();

			SelectServerRights();
			UncheckFullAccess();
			CheckAllSubOptions();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new Exception("Something went wrong please see console for message.");
		}
	}
	
	public void ClickAddNewMemberButton() {
		WebElement addButton = Driver.findElement(obj_AddUserButton);
		addButton.click();
	}
}
