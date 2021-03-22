package stepdefinitions;

import org.testng.Assert;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import runner.TestRunner;

public class CharacteristicsCheckStepDef {

	protected WebDriver driver = TestRunner.getDriver();

	@Given("^User hits the URL: (.*)$")
	public void ueser_hits_the_url(String url) {
		try {
			driver.get(url);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// When Section

	@When("^User enters the Username (.*) and Password (.*) to login$")
	public void user_enters_credentials(String emailId, String password) {
		driver.findElement(By.name("username")).sendKeys(emailId);
		driver.findElement(By.name("password")).sendKeys(password);
	}

	@When("^User clicks Login$")
	public void user_clicks_login() {
		driver.findElement(By.xpath(".//button[@type='submit']")).click();
	}

	@When("^Mouse hover outside the viewport$")
	public void mouse_outside_the_viewport() throws InterruptedException, AWTException {
		Robot robot = new Robot();
		Point coordinates = driver.findElement(By.xpath("//p[contains(text(),'Mouse out of the viewport pane and see a modal win')]")).getLocation();
		robot.mouseMove(coordinates.getX(),coordinates.getY()+120);
		robot.mouseMove(600, -1);
		Thread.sleep(1000);
	}

	@When("^Clicking on the column lastname$")
	public void clicking_on_the_column_lastname() {
		driver.findElement(By.xpath("//table[@id='table1']/thead/tr/th/span")).click();
	}

	@When("^Clicking on the column firstname$")
	public void clicking_on_the_column_firstname() {
		driver.findElement(By.xpath("//table[@id='table2']/thead/tr/th[2]/span")).click();
		driver.findElement(By.xpath("//table[@id='table2']/thead/tr/th[2]/span")).click();
	}

	@When("^User clicks the button click here$")
	public void user_clicks_the_button_click_here() {
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
	}

	@When("^User clicks enable button$")
	public void user_clicks_enable_button() {
		toggleEnableDisableButton();
	}

	@When("^User clicks disable button$")
	public void user_clicks_disable_button() {
		toggleEnableDisableButton();
	}

	// Then Section

	@Then("^User is successfully logged into the application and could see the success message (.*)$")
	public void user_gets_the_success_message(String expectedMessage) {
		VerifyLoginResponse(expectedMessage, "User is not successfully logged in");
	}

	@Then("^User is not logged into the application and could see the failure message (.*)$")
	public void user_gets_the_failure_message(String expectedMessage) {
		VerifyLoginResponse(expectedMessage, "User should not be successfully logged in");
	}

	@Then("^Verify exit intent alert is shown$")
	public void verify_exit_intent_alert_shown() {
		Assert.assertTrue(isAlertPresent(), "Exit intent alert is not shown");
	}

	@Then("^Verify lastname column is sorted ascending$")
	public void verify_lastname_column_sorted_ascending() {
		List<WebElement> lastNameColumn = driver.findElements(By.xpath(".//table[@id='table1']/tbody/tr/td[1]"));
		List<String> lastNameColumnAfterSorting = getListOfValuesInTheWebElementColumn(lastNameColumn);
		Assert.assertTrue(isSorted(lastNameColumnAfterSorting, true), "the lastname is not sorted in ascending order");
	}

	@Then("^Verify firstname column is sorted descending$")
	public void verify_firstname_column_sorted_descending() {
		List<WebElement> firstNameColumn = driver.findElements(By.xpath(".//table[@id='table2']/tbody/tr/td[2]"));
		List<String> firstNameColumnAfterSorting = getListOfValuesInTheWebElementColumn(firstNameColumn);
		Assert.assertTrue(isSorted(firstNameColumnAfterSorting, false),
				"the firstname is not sorted in descending order");
	}

	@Then("^Verify click here opens a new tab with URL: (.*)$")
	public void verify_click_here_opens_a_new_tab(String destinationURL) throws InterruptedException {
		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		Assert.assertTrue(browserTabs.size() > 1, "New tab or New Window is not opened");
		driver.switchTo().window(browserTabs.get(1));
		Thread.sleep(3000);
		Assert.assertEquals(driver.getCurrentUrl(), destinationURL, "URL of the new opened window does not match");
		driver.switchTo().window(browserTabs.get(0));
	}

	@Then("^Verify text box is enabled to provide input (.*)$")
	public void verify_text_box_is_enabled_to_provide_input(String input) {
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//form[@id='input-example']/input"))));
		driver.findElement(By.xpath("//form[@id='input-example']/input")).sendKeys(input);
	}

	@Then("^Verify text box is disable and still it has the input (.*)$")
	public void verify_text_box_is_disabled_and_still_has_the_same_input(String input) {
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.not(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//form[@id='input-example']/input")))));
		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='input-example']/input")).getAttribute("value"),
				input, "The provided input is incorrect");
	}

	private void VerifyLoginResponse(String expectedMessage, String failsMessage) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='flash']")));
		Boolean isElementPresent = driver
				.findElement(By.xpath("//div[@id='flash' and contains(string(), \"" + expectedMessage + "\")]"))
				.isDisplayed();
		Assert.assertTrue(isElementPresent, failsMessage);
	}

	private void toggleEnableDisableButton() {
		driver.findElement(By.xpath("//form[@id='input-example']/button")).click();
	}

	public boolean isAlertPresent() {
		try {
			//Thread.sleep(2000);
			//driver.switchTo().frame("This is a modal window");
			//WebElement m=
				      driver.findElement(By.cssSelector("div.modal"));
			//System.out.println(m.isDisplayed());
			Boolean isAlertPresent = driver.findElement(By.xpath("//div[@id='ouibounce-modal']")).isDisplayed();
			return isAlertPresent;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private List<String> getListOfValuesInTheWebElementColumn(List<WebElement> lastNameColumn) {
		int row_num = 1;
		List<String> columnValueList = new ArrayList<String>();
		for (WebElement tdElement : lastNameColumn) {
			columnValueList.add(tdElement.getText());
			row_num++;
		}
		return columnValueList;
	}

	public static boolean isSorted(List<String> listOfStrings, Boolean InAscending) {
		if (listOfStrings.isEmpty() || listOfStrings.size() == 1) {
			return true;
		}

		Iterator<String> iter = listOfStrings.iterator();
		String current, previous = iter.next();
		while (iter.hasNext()) {
			current = iter.next();
			if (InAscending) {
				if (previous.compareTo(current) > 0) {
					return false;
				}
			} else {
				if (previous.compareTo(current) < 0) {
					return false;
				}
			}
			previous = current;
		}
		return true;
	}
}
