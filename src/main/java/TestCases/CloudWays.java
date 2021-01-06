package TestCases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class CloudWays extends ObjectRepo {

	@BeforeTest
	public void TestInit() {
		System.setProperty("webdriver.chrome.driver", "C:\\driver\\chrome\\chromedriver.exe");
		Driver = new ChromeDriver();
		Driver.manage().window().maximize();
	}

	@AfterTest
	public void TearDown() {
		// Driver.quit();
	}

	@Test
	public void CreateNewTeamMember() {

		String jobTitle = "DevOps";
		try {
			// Navigate To URL
			LoginPage().NavigateToUrl(WebUrl);

			LoginPage().LoginToApplication(UserName, Password);
			Assert.assertTrue(LoginPage().IsLoginSuccessfull(), "Login not successfull please check.");

			// Open Team Page
			TeamPage().OpenTeamPage();
			TeamPage().ClickAddNewTeamMember();

			Thread.sleep(10000);

			TeamPage().EnterTeamMemberInformation(Team_Id, Team_Name, jobTitle);
			TeamPage().SelectPermissions();

			TeamPage().ClickAddNewMemberButton();
			Thread.sleep(10000);

		} catch (Exception ex) {

			Assert.fail("Test Case not passed" + ex.getStackTrace());

		}
	}

}
