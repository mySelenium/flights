package pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FlightsPage {

	WebDriver driver;
	ExtentTest test;

	public FlightsPage(WebDriver driver, ExtentTest test) {

		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	// Flights Tab
	@FindBy(id = "flightmanager-tab-1")
	WebElement flightsTab;

	// Round-trip Tab
	@FindBy(id = "flightmanagerFlightsFormReturnLabel")
	WebElement roundTrip;

	// One-way Tab
	@FindBy(id = "flightmanagerFlightsFormOnewayLabel")
	WebElement oneWay;

	// Origin Box
	@FindBy(id = "flightmanagerFlightsFormOrigin")
	WebElement originBox;

	// Destination Box
	@FindBy(id = "flightmanagerFlightsFormDestination")
	WebElement destinationBox;

	@FindBy(id = "flightmanagerFlightsFormOutboundDateDisplay")
	WebElement depDate;

	// Returning
	@FindBy(id = "flightmanagerFlightsFormInboundDateDisplay")
	WebElement retDate;

	// Number of Adult Passenger
	@FindBy(id = "flightmanagerFlightsFormAdults")
	public WebElement numAdult;

	// Number of Children Passenger
	@FindBy(id = "flightmanagerFlightsFormChildren")
	public WebElement numChildren;

	// Number of Infant Passenger
	@FindBy(id = "flightmanagerFlightsFormInfants")
	public WebElement numInfants;
	// Cabin Class
	@FindBy(id = "flightmanagerFlightsFormCabin")
	public WebElement cabinClass;

	// Search Flights
	@FindBy(xpath = "//button[text()='Search flights']")
	public WebElement searchFlightButton;

	public void doOneWayFlight(String _origin, String _destination, String _depDate, String _numAdult,
			String _numChildren, String _numInfant, String _cabinClass) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		flightsTab.click();
		test.log(LogStatus.INFO, "Clicked on flights tab...");

		oneWay.click();
		test.log(LogStatus.INFO, "One way trip selected");

		originBox.sendKeys(_origin);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='flightmanagerFlightsFormOriginPopup']")));// popup
																														// list
		driver.findElement(By.xpath(".//*[@id='flightmanagerFlightsFormOriginPopup']")).click();
		test.log(LogStatus.INFO, "Entered origin city...");

		destinationBox.sendKeys(_destination);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(".//*[@id='flightmanagerFlightsFormDestinationPopupList']")));// popup
																												// list
		driver.findElement(By.xpath(".//*[@id='flightmanagerFlightsFormDestinationPopupList']")).click();
		test.log(LogStatus.INFO, "Entered destination city...");

		depDate.click();
		WebElement depCalendar = driver
				.findElement(By.xpath(".//*[@id='kosa-cal-modal-1']/div/div/div[2]/div[1]/div[1]/div[2]/table/tbody"));
		List<WebElement> depDays = depCalendar.findElements(By.tagName("button"));
		for (WebElement day : depDays) {
			if (day.getText().equals(_depDate)) {
				day.click();
				break;
			}
		}
		test.log(LogStatus.INFO, "Entered departure date");

		Select adults = new Select(numAdult);
		adults.selectByValue(_numAdult);
		test.log(LogStatus.INFO, "Specified number of adult passengers...");

		driver.findElement(By.linkText("Travel with children")).click();

		Select children = new Select(numChildren);
		children.selectByValue(_numChildren);
		test.log(LogStatus.INFO, "Specified number of child passengers...");

		Select infants = new Select(numInfants);
		infants.selectByValue(_numInfant);
		test.log(LogStatus.INFO, "Specified number of infant passengers...");

		Select cabin = new Select(cabinClass);
		cabin.selectByValue(_cabinClass);
		test.log(LogStatus.INFO, "Specified cabing class...");
	}

	public void doRoundTripFlight(String _origin, String _destination, String _depDate, String _retDate,
			String _numAdult, String _numChildren, String _numInfant, String _cabinClass) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		flightsTab.click();
		test.log(LogStatus.INFO, "Clicked on flights tab...");

		roundTrip.click();
		test.log(LogStatus.INFO, "One way trip selected");

		originBox.sendKeys(_origin);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='flightmanagerFlightsFormOriginPopup']")));// popup
																														// list
		driver.findElement(By.xpath("//*[@id='flightmanagerFlightsFormOriginPopup']")).click();
		test.log(LogStatus.INFO, "Entered origin city...");

		destinationBox.sendKeys(_destination);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='flightmanagerFlightsFormDestinationPopupList']")));// popup
																											// list
		driver.findElement(By.xpath("//*[@id='flightmanagerFlightsFormDestinationPopupList']")).click();
		test.log(LogStatus.INFO, "Entered destination city...");

		depDate.click();
		WebElement depCalendar = driver.findElement(By.xpath(
				".//*[@id='flightmanagerFlightsFormOutboundDateDisplay-scr-empty']//preceding-sibling::div/div[1]"));
		List<WebElement> depDays = depCalendar.findElements(By.tagName("button"));
		for (WebElement day : depDays) {
			if (day.getText().equals(_depDate)) {
				day.click();
				break;
			}
		}
		test.log(LogStatus.INFO, "Entered departure date");

		// closing calendar popup
		driver.findElement(By.xpath(".//*[@id='kosa-cal-modal-2']/div/div/div[1]/button")).click();
		Thread.sleep(500);
		retDate.click();

		WebElement retCalendar = driver
				.findElement(By.xpath(".//*[@id='kosa-cal-modal-2']/div/div/div[2]/div[1]/div[1]/div[2]/table/tbody"));
		List<WebElement> retDays = retCalendar.findElements(By.tagName("button"));
		for (WebElement day : retDays) {
			if (day.getText().equals(_retDate)) {
				day.click();
				break;
			}
		}

		Select adults = new Select(numAdult);
		adults.selectByValue(_numAdult);
		test.log(LogStatus.INFO, "Specified number of adult passengers...");

		driver.findElement(By.linkText("Travel with children")).click();
		Select children = new Select(numChildren);
		children.selectByValue(_numChildren);
		test.log(LogStatus.INFO, "Specified number of child passengers...");

		Select infants = new Select(numInfants);
		infants.selectByValue(_numInfant);
		test.log(LogStatus.INFO, "Specified number of infant passengers...");

		Select cabin = new Select(cabinClass);
		cabin.selectByValue(_cabinClass);
		test.log(LogStatus.INFO, "Specified cabing class...");
	}

	public void startSearch() {
		searchFlightButton.click();
		test.log(LogStatus.INFO, "Clicked on search button...");

	}

}
