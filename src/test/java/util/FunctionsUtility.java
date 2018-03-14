package util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import IMarket_Registration.RegistrationSuccessfully;



// TODO: Auto-generated Javadoc
/**
 * <h1>FunctionsUtility class</h1> <br>
 * Utility Class for Functions <br>
 * <br>.
 *
 * @author TestingXperts
 * @version 1.0 <br>
 *          <br>
 */
public class FunctionsUtility extends DriverUtility{

	/** The extent. */
	public static ExtentReports extent;

	/** The logger. */
	public static ExtentTest logger;

	/** The element. */
	private static By element;

	/** The wait. */
	static WebDriverWait wait;

	/** The robo. */
	static Robot robo;

	/** The scan. */
	static Scanner scan;

	/** The util. */
	static FileUtils util;

	/** The result. */
	static ITestResult result;

	/** The class name. */
	// Put your class name here in place of Login
	public static String className = RegistrationSuccessfully.class.getSimpleName();

	/** The image path. */
	static String imagePath;

	/** The path for logger. */
	static String pathForLogger;

	/** The browser name. */
	static String browserName;

	/** The html. */
	static String html;

	/** The excel report file full path. */
	static String excelReportFileFullPath;

	/** The excel report file name. */
	static String excelReportFileName;

	/** The suite name. */
	static String suiteName = "Polaris iMarket";;

	/** The keyboard. */
	static Keyboard keyboard;

	/** The executor. */
	static JavascriptExecutor executor; 

	/**
	 * This method prints the suite name in the Extent Report and initiates
	 * Extent Reporting. <br>
	 * <br>
	 * <span style='color:red'><b>Change the suiteName variable in the first
	 * line for correct SUITE name to be printed.</b></span> <br><br>
	 * 
	 * @category Utility Class for Functions
	 */
	@BeforeSuite(alwaysRun = true)
	public void printSuiteName() {

		extent = new ExtentReports(System.getProperty("user.dir")
				+ readStringFromProperties("Framework.properties", "ExtentReportFileName"));

		extent.loadConfig(new File(
				System.getProperty("user.dir") + readStringFromProperties("Framework.properties", "ExtentConfigXML")));

		logger = extent.startTest("Intermediary Group Registration");

		logger.assignAuthor("Prateek & Anuj");

		logger.log(LogStatus.INFO, normalInfoColor("Suite -  " + suiteName));
	}

	/**
	 * This method sets up the browser and driver and prints Class name. <br>
	 * <br>
	 * <span style='color:red'><b>Change the suiteName variable in the VARIABLE
	 * DECLARATION section.</b></span> <br><br>
	 * 
	 * @exception Exception
	 *                The exception is thrown by the method getDriver(). <br>
	 *                <br>
	 * @category Utility Class for Functions
	 */
	@BeforeTest(alwaysRun = true)
	public static void setUp() throws Exception {
		browserName=readStringFromProperties("Framework.properties", "browserName");
		logger.assignCategory(suiteName);

		logger.setDescription("This Test Class perofrms Polaris iMarket website testing");

		logger.getDescription();

		//logger.log(LogStatus.INFO, normalInfoColor("Class -  " + suiteName));



	}

	/**
	 * Initiate browser.
	 *
	 * @throws Exception the exception
	 */
	@BeforeMethod(alwaysRun = true)
	public static void initiateBrowser()throws Exception{
		scan = new Scanner(System.in);

		Reporter.log("Getting Browser Name");

		if (browserName.equalsIgnoreCase("Chrome") || browserName.equalsIgnoreCase("Ch")) {

			logger.log(LogStatus.PASS, normalInfoColor("Opening Website in Google Chrome"));

		} else if (browserName.equalsIgnoreCase("Firefox") || browserName.equalsIgnoreCase("Ff")) {

			logger.log(LogStatus.INFO, normalInfoColor("Opening Website in Mozilla Firefox."));

		} else if (browserName.equalsIgnoreCase("Explorer") || browserName.equalsIgnoreCase("IE")) {

			logger.log(LogStatus.INFO, normalInfoColor("Opening Website in Internet Explorer."));

		}
		//added condition for logging android chrome browser - Nov 14 2017 Raghav Pal
		else if (browserName.equalsIgnoreCase("android-chrome") || browserName.equalsIgnoreCase("chrome-android")) {

			logger.log(LogStatus.INFO, normalInfoColor("Opening Website in Android Chrome browser"));

		}
		
		else {

			logger.log(LogStatus.INFO, failStringRedColor("Sorry. Browser not Supported."));
		}

		getDriver(browserName);

		scan.close();
	}


	/**
	 * This method takes a screenshot of the web page opened by the driver. <br>
	 * <br>
	 * <span style='color:red'><b>The name of the image will be same as the name
	 * of the class (the suiteName variable is the NAME of the image
	 * file).</b></span> <br><br>
	 * 
	 * @return Absolute path of the image file. <br>
	 *         <br>
	 * @exception IOException
	 *                This exception is thrown if the directory specified is not
	 *                accessible. <br>
	 *                <br>
	 * @category Utility Class for Functions
	 */
	public static String testFailTakeScreenshot() throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File des = new File(System.getProperty("user.dir") + "/FailedScreenshots/" + suiteName + ".jpeg");

		FileUtils.copyFile(src, des);

		return des.getAbsolutePath();
	}

	/**
	 * <span style='color:red'><b>This function changes the color of the string
	 * passed to RED.</b></span> <br><br>
	 * 
	 * @param stepName
	 *            - The string to be changed to
	 *            <span style='color:red'><b>RED.</b></span> <br><br>
	 * @return A String appended with HTML tags. <br>
	 *         <br>
	 * @category Utility Class for Functions
	 */
	public static String failStringRedColor(String stepName) {

		html = "<span style='color:red'><b>" + stepName + "</b></span>";
		return html;

	}

	/**
	 * <span style='color:#2E86C1'><b>This function changes the color of the
	 * string passed to BLUE.</b></span> <br><br>
	 * 
	 * @param stepName
	 *            - The string to be changed to
	 *            <span style='color:#2E86C1'><b>BLUE.</b></span> <br><br>
	 * @return A String appended with HTML tags. <br>
	 *         <br>
	 * @category Utility Class for Functions
	 */
	public static String normalInfoColor(String stepName) {

		html = "<span style='color:#2E86C1'><b>" + stepName + "</b></span>";
		return html;

	}

	/**
	 * <span style='color:#008000'><b>This function changes the color of the
	 * string passed to GREEN.</b></span> <br><br>
	 * 
	 * @param stepName
	 *            - The string to be changed to
	 *            <span style='color:#008000'><b>GREEN.</b></span> <br><br>
	 * @return A String appended with HTML tags. <br>
	 *         <br>
	 * @category Utility Class for Functions
	 */
	public static String passStringGreenColor(String stepName) {

		html = "<span style='color:#008000'><b>" + stepName + "</b></span>";
		return html;

	}

	/**
	 * <span style='color:#F39C12'><b>This function changes the color of the
	 * string passed to YELLOW.</b></span> <br><br>
	 * 
	 * @param stepName
	 *            - The string to be changed to
	 *            <span style='color:#F39C12'><b>YELLOW.</b></span> <br><br>
	 * @return A String appended with HTML tags. <br>
	 *         <br>
	 * @category Utility Class for Functions
	 */
	public static String skipStringYellowColor(String stepName) {

		html = "<span style='color:#F39C12'><b>" + stepName + "</b></span>";
		return html;

	}

	/**
	 * This function checks the Title of the page opened. <br>
	 * <br>
	 * 
	 * @param title
	 *            - The expected title is passed in this variable. <br>
	 *            <br>
	 * @exception IOException
	 *                thrown by testFailTakeScreenshot() method. <br>
	 *                <br>
	 * @category Utility Class for Functions
	 */
	public static void checkTitle(String title) throws IOException {

		try {

			assertEquals(driver.getTitle(), title);

			Reporter.log("Website Opened");

			logger.log(LogStatus.PASS, passStringGreenColor("Correct Website Opened."));

		} catch (AssertionError e) {

			System.out.println(e.getMessage());

			Reporter.log("Incorrect Website Opened");

			logger.log(LogStatus.INFO, failStringRedColor("Site Open"));

			imagePath = testFailTakeScreenshot();

			// UploadFileFromWindowUtil.uploadFile(imagePath);

			logger.log(LogStatus.FAIL, e);

			pathForLogger = logger.addScreenCapture(imagePath);

			logger.log(LogStatus.FAIL, failStringRedColor("Error Opening Page : ") + pathForLogger);

			throw e;

		}
	}

	/**
	 * This function is to select the locator type from the types available in
	 * Selenium. <br>
	 * <br>
	 * 
	 * @param locator_Type
	 *            - The type of locator used. <br>
	 *            <br>
	 * @param locator_Path
	 *            - The value of the locator. <br>
	 *            <br>
	 * @return An object of By class with the passed locator type. <br>
	 *         <br>
	 * @category Utility Class for Functions
	 */
	public static By selectLocator(String locator_Type, String locator_Path) {

		switch (locator_Type) {

		case "id":
			element = By.id(locator_Path);
			return element;

		case "className":
			element = By.className(locator_Path);
			return element;

		case "cssSelector":
			element = By.cssSelector(locator_Path);
			return element;

		case "linkText":
			element = By.linkText(locator_Path);
			return element;

		case "name":
			element = By.name(locator_Path);
			return element;

		case "partialLinkText":
			element = By.partialLinkText(locator_Path);
			return element;

		case "tagName":
			element = By.tagName(locator_Path);
			return element;

		case "xpath":
			element = By.xpath(locator_Path);
			return element;

		default:
			Reporter.log("Incorrect Locator property Found " + locator_Type);

			logger.log(LogStatus.FAIL, failStringRedColor(locator_Type + " is an Incorrect Locator Type."));

			fail("Incorrect Locator Property " + locator_Type);
		}

		return null;
	}

	/**
	 * Gets the text.
	 *
	 * @param locator_Type the locator type
	 * @param locator_Path the locator path
	 * @return the text
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String getText(String locator_Type, String locator_Path, String step_description) throws IOException {

		/*wait = new WebDriverWait(driver,readLongFromProperties("Framework.properties",
				"ExplicitWaitTimeOut"));*/

		element = selectLocator(locator_Type, locator_Path);
		/*
		 * wait.until(ExpectedConditions.presenceOfElementLocated(element));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)
		 * ); wait.until(ExpectedConditions.elementToBeClickable(element));
		 */

		String text=driver.findElement(element).getText();

		logger.log(LogStatus.PASS, passStringGreenColor(step_description + " - Passed."));
		return text;
	}

	/**
	 * Gets the list of elements.
	 *
	 * @param locator_Type the locator type
	 * @param locator_Path the locator path
	 * @return the list of elements
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<WebElement> getListOfElements(String locator_Type, String locator_Path) throws IOException {

		/*wait = new WebDriverWait(driver,readLongFromProperties("Framework.properties",
				"ExplicitWaitTimeOut"));*/

		element = selectLocator(locator_Type, locator_Path);
		/*
		 * wait.until(ExpectedConditions.presenceOfElementLocated(element));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)
		 * ); wait.until(ExpectedConditions.elementToBeClickable(element));
		 */

		logger.log(LogStatus.PASS, passStringGreenColor(locator_Path + " - get elements"));
		return driver.findElements(element);
	}

	/**
	 * Gets the web element.
	 *
	 * @param locator_Type the locator type
	 * @param locator_Path the locator path
	 * @return the web element
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static WebElement getWebElement(String locator_Type, String locator_Path) throws IOException {

		/*wait = new WebDriverWait(driver,readLongFromProperties("Framework.properties",
				"ExplicitWaitTimeOut"));*/

		element = selectLocator(locator_Type, locator_Path);
		/*
		 * wait.until(ExpectedConditions.presenceOfElementLocated(element));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)
		 * ); wait.until(ExpectedConditions.elementToBeClickable(element));
		 */

		logger.log(LogStatus.PASS, passStringGreenColor(locator_Path + " - get web element"));
		return driver.findElement(element);
	}

	/**
	 * Check web element present.
	 *
	 * @param locator_Type the locator type
	 * @param locator_Path the locator path
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static boolean checkWebElementPresent(String locator_Type, String locator_Path) throws IOException {
		try{
			/*wait = new WebDriverWait(driver,readLongFromProperties("Framework.properties",
				"ExplicitWaitTimeOut"));*/

			element = selectLocator(locator_Type, locator_Path);
			/*
			 * wait.until(ExpectedConditions.presenceOfElementLocated(element));
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)
			 * ); wait.until(ExpectedConditions.elementToBeClickable(element));
			 */
			driver.findElement(element);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	/**
	 * Click by javascript.
	 *
	 * @param locator_Type the locator type
	 * @param locator_Path the locator path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void clickByJavascript(String locator_Type, String locator_Path) throws IOException {
		try{
		/*wait = new WebDriverWait(driver,readLongFromProperties("Framework.properties",
				"ExplicitWaitTimeOut"));*/
		element = selectLocator(locator_Type, locator_Path);
		/*
		 * wait.until(ExpectedConditions.presenceOfElementLocated(element));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)
		 * ); wait.until(ExpectedConditions.elementToBeClickable(element));
		 */
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", driver.findElement(element));
		}catch (Exception e) {
			Reporter.log(locator_Path + " not Clicked");

			logger.log(LogStatus.INFO, failStringRedColor("Click on Element : " + locator_Path + " - Failed."));

			imagePath = testFailTakeScreenshot();

			// UploadFileFromWindowUtil.uploadFile(imagePath);

			logger.log(LogStatus.FAIL, failStringRedColor("Test Case Failure Reason :- ") + "\n" + e);

			pathForLogger = logger.addScreenCapture(imagePath);

			logger.log(LogStatus.FAIL,
					failStringRedColor("Cannot find " + locator_Path + "on Page :-" + pathForLogger));

			throw e;

		}
	}


	/**
	 * This function performs click on the element. <br>
	 * <br>
	 *
	 * @param locator_Type            - The type of locator used. <br>
	 *            <br>
	 * @param locator_Path            - The value of the locator. <br>
	 *            <br>
	 * @exception IOException                thrown by testFailTakeScreenshot() method. <br>
	 *                <br>
	 * @category Utility Class for Functions
	 */
	public static void clickOnElement(String locator_Type, String locator_Path, String step_description) throws IOException {

		/*wait = new WebDriverWait(driver,readLongFromProperties("Framework.properties",
				"ExplicitWaitTimeOut"));*/

		try {
			element = selectLocator(locator_Type, locator_Path);
			/*
			 * wait.until(ExpectedConditions.presenceOfElementLocated(element));
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)
			 * ); wait.until(ExpectedConditions.elementToBeClickable(element));
			 */

			driver.findElement(element).click();

			logger.log(LogStatus.PASS, passStringGreenColor(step_description + " - Passed."));

		} catch (Exception e) {
			Reporter.log(locator_Path + " not Clicked");

			logger.log(LogStatus.INFO, failStringRedColor("Click on Element : " + step_description + " - Failed."));

			imagePath = testFailTakeScreenshot();

			// UploadFileFromWindowUtil.uploadFile(imagePath);

			logger.log(LogStatus.FAIL, failStringRedColor("Test Case Failure Reason :- ") + "\n" + e);

			pathForLogger = logger.addScreenCapture(imagePath);

			logger.log(LogStatus.FAIL,
					failStringRedColor("Cannot find " + locator_Path + "on Page :-" + pathForLogger));

			throw e;

		}
	}

	/**
	 * Scroll downbottom.
	 */
	public static void scrollDownbottom (){
		executor = (JavascriptExecutor)driver;
		executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * Press enter.
	 */
	public static void pressEnter (){
		keyboard=((RemoteWebDriver) driver).getKeyboard();
		keyboard.pressKey(Keys.ENTER);
	}

	/**
	 * Scrolling to elementof A page.
	 *
	 * @param locator_Type the locator type
	 * @param locator_Path the locator path
	 */
	public static void scrollingToElementofAPage(String locator_Type, String locator_Path) {
		element = selectLocator(locator_Type, locator_Path);
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
	}
	
	/**
	 * Scrolling to element of A page with web element.
	 *
	 * @param webelement the webelement
	 */
	public static void scrollingToElementOfAPageWithWebElement(WebElement webelement) {
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView();", webelement);
	}

	/**
	 * This function clears the field specified by the element. <br>
	 * <br>
	 *
	 * @param locator_Type            - The type of locator used. <br>
	 *            <br>
	 * @param locator_Path            - The value of the locator. <br>
	 *            <br>
	 * @exception IOException                thrown by testFailTakeScreenshot() method. <br>
	 *                <br>
	 * @category Utility Class for Functions
	 */
	public static void clearField(String locator_Type, String locator_Path) throws IOException {

		wait = new WebDriverWait(driver,readLongFromProperties("Framework.properties",
				"ExplicitWaitTimeOut"));

		try {
			element = selectLocator(locator_Type, locator_Path);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			/*
			 * wait.until(ExpectedConditions.presenceOfElementLocated(element));
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)
			 * ); wait.until(ExpectedConditions.elementToBeClickable(element));
			 */

			driver.findElement(element).clear();

			logger.log(LogStatus.PASS, passStringGreenColor(locator_Path + " - Cleared."));
		} catch (Exception e) {
			Reporter.log(locator_Path + " not Cleared");

			logger.log(LogStatus.INFO, failStringRedColor("Clear Field for : " + locator_Path + " - Failed."));

			imagePath = testFailTakeScreenshot();

			// UploadFileFromWindowUtil.uploadFile(imagePath);

			logger.log(LogStatus.FAIL, failStringRedColor("Test Case Failure Reason :- ") + "\n" + e);

			pathForLogger = logger.addScreenCapture(imagePath);

			logger.log(LogStatus.FAIL,
					failStringRedColor("Cannot find " + locator_Path + "on Page :-" + pathForLogger));

			throw e;
		}
	}

	/**
	 * This function enters data into the field specified by the element. <br>
	 * <br>
	 *
	 * @param locator_Type            - The type of locator used. <br>
	 *            <br>
	 * @param locator_Path            - The value of the locator. <br>
	 *            <br>
	 * @param input            - The value to be input in the field. <br>
	 *            <br>
	 * @exception IOException                thrown by testFailTakeScreenshot() method. <br>
	 *                <br>
	 * @category Utility Class for Functions
	 */
	public static void inputData(String locator_Type, String locator_Path, String input, String step_description) throws IOException {

		// wait = new WebDriverWait(driver,
		// readLongFromProperties("Framework.properties",
		// "ExplicitWaitTimeOut"));

		try {

			element = selectLocator(locator_Type, locator_Path);

			/*
			 * wait.until(ExpectedConditions.presenceOfElementLocated(element));
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)
			 * ); wait.until(ExpectedConditions.elementToBeClickable(element));
			 */
			driver.findElement(element).clear();
			driver.findElement(element).sendKeys(input);

			logger.log(LogStatus.PASS, passStringGreenColor(step_description + " - Passed."));
		} catch (Exception e) {
			Reporter.log("Value not Entered in " + step_description);

			logger.log(LogStatus.INFO, failStringRedColor("Data input for : " + step_description + " - Failed."));

			imagePath = testFailTakeScreenshot();

			// UploadFileFromWindowUtil.uploadFile(imagePath);

			logger.log(LogStatus.FAIL, failStringRedColor("Test Case Failure Reason :- ") + "\n" + e);

			pathForLogger = logger.addScreenCapture(imagePath);

			logger.log(LogStatus.FAIL,
					failStringRedColor("Cannot find " + locator_Path + "on Page :-" + pathForLogger));

			throw e;
		}
	}

	/**
	 * Input data without clearing.
	 *
	 * @param locator_Type the locator type
	 * @param locator_Path the locator path
	 * @param input the input
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void inputDataWithoutClearing(String locator_Type, String locator_Path, String input) throws IOException {

		// wait = new WebDriverWait(driver,
		// readLongFromProperties("Framework.properties",
		// "ExplicitWaitTimeOut"));

		try {

			element = selectLocator(locator_Type, locator_Path);

			/*
			 * wait.until(ExpectedConditions.presenceOfElementLocated(element));
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)
			 * ); wait.until(ExpectedConditions.elementToBeClickable(element));
			 */
			driver.findElement(element).sendKeys(input);

			logger.log(LogStatus.PASS, passStringGreenColor(locator_Path + "'s Value Entered."));
		} catch (Exception e) {
			Reporter.log("Value not Entered in " + locator_Path);

			logger.log(LogStatus.INFO, failStringRedColor("Data input for : " + locator_Path + " - Failed."));

			imagePath = testFailTakeScreenshot();

			// UploadFileFromWindowUtil.uploadFile(imagePath);

			logger.log(LogStatus.FAIL, failStringRedColor("Test Case Failure Reason :- ") + "\n" + e);

			pathForLogger = logger.addScreenCapture(imagePath);

			logger.log(LogStatus.FAIL,
					failStringRedColor("Cannot find " + locator_Path + "on Page :-" + pathForLogger));

			throw e;
		}
	}

	/**
	 * This function enters data into the field specified by the element. <br>
	 * <br>
	 * 
	 * @param times
	 *            - The number of times to switch the tabs of the OS. <br>
	 *            <br>
	 * @exception AWTException
	 *                thrown by Robot class. <br>
	 *                <br>
	 * @category Utility Class for Functions
	 */
	public static void alt_tab(int times) throws AWTException {

		Reporter.log("Switching Window");
		logger.log(LogStatus.INFO, normalInfoColor("Switching Window"));

		robo = new Robot();

		for (int i = 0; i < times; i++) {

			robo.keyPress(KeyEvent.VK_ALT);
			robo.keyPress(KeyEvent.VK_TAB);
			robo.keyPress(KeyEvent.VK_ENTER);
		}

		Reporter.log("Window Switched");

		logger.log(LogStatus.INFO, normalInfoColor("Window Switched"));
	}

	/**
	 * This function prints the result of the Test Case in the Extent Report.
	 * <br>
	 * <br>
	 *
	 * @param result            - It is an object of ITestResult class. <br>
	 *            <br>
	 * @return the result
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @category Utility Class for Functions
	 */
	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {

			imagePath = testFailTakeScreenshot();

			// UploadFileFromWindowUtil.uploadFile(imagePath);


			pathForLogger = logger.addScreenCapture(imagePath);

			logger.log(LogStatus.FAIL, failStringRedColor("Error on the Page : ") + pathForLogger);
			logger.log(LogStatus.FAIL, failStringRedColor(result.getName() + " - Failed."));
			logger.log(LogStatus.FAIL, "Test Case Fail reason is :- " + "\n" + result.getThrowable());

		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, skipStringYellowColor(result.getName() + " - Skipped."));

		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.PASS, skipStringYellowColor(result.getName() + " - Passed."));

		}

		extent.endTest(logger);
		Reporter.log("Browser Closed");
		driver.quit();
	}

	/**
	 * This function prints the result of the Test Class in the Extent Report.
	 * <br>
	 * <br>
	 * 
	 * @category Utility Class for Functions
	 */
	@AfterTest(alwaysRun = true)
	public void endReport() {

		/*result = Reporter.getCurrentTestResult();

		switch (result.getStatus()) {

		case ITestResult.SUCCESS:
			logger.log(LogStatus.PASS, passStringGreenColor(suiteName + " - Passed."));
			break;

		case ITestResult.FAILURE:
			logger.log(LogStatus.FAIL, failStringRedColor(suiteName + " - Failed."));
			break;

		case ITestResult.SKIP:
			logger.log(LogStatus.SKIP, skipStringYellowColor(suiteName + " - Skipped."));
			break;

		}
		 */
		extent.flush();
	}

	/**
	 * This function closes the browser. <br>
	 * <br>
	 * 
	 * @category Utility Class for Functions
	 */
	@AfterClass(alwaysRun = true)
	public static void tearDown() {

		// driver.close();
		Reporter.log("Browser Closed");
		driver.quit();
	}

	/**
	 * This function generates Excel Report and adds Description to the Excel
	 * report created. <br>
	 * <br>
	 *
	 * @throws Exception the exception
	 * @category Utility Class for Functions
	 */
	@AfterSuite(alwaysRun = true)
	public void generateExcelReport() throws Exception {

		/*excelReportFileFullPath = System.getProperty("user.dir")
				+ readStringFromProperties("Framework.properties", "ExcelReportFilePath");

		excelReportFileName = readStringFromProperties("Framework.properties", "ExcelReportFileName");

		Xl.generateReport(excelReportFileFullPath, excelReportFileName);

		excelReportFileFullPath = excelReportFileFullPath + excelReportFileName;

		customizeExcelReport(
				System.getProperty("user.dir") + readStringFromProperties("Framework.properties", "ExcelReportFilePath")
						+ readStringFromProperties("Framework.properties", "ExcelReportFileName"));*/
	}

	
	/**
	  * Select by visible text.
	  *
	  * @param locator_Type the locator type
	  * @param locator_Path the locator path
	  * @param text the text
	  * @throws IOException Signals that an I/O exception has occurred.
	  */
	 public static void selectByVisibleText(String locator_Type, String locator_Path, String text, String step_description) throws IOException {

	  /*
	   * wait = new
	   * WebDriverWait(driver,readLongFromProperties("Framework.properties",
	   * "ExplicitWaitTimeOut"));
	   */

	  try {
	   element = selectLocator(locator_Type, locator_Path);
	   /*
	    * wait.until(ExpectedConditions.presenceOfElementLocated(element));
	    * wait.until(ExpectedConditions.visibilityOfElementLocated(element) );
	    * wait.until(ExpectedConditions.elementToBeClickable(element));
	    */

	   Select dropDown = new Select(driver.findElement(element));
	   dropDown.selectByVisibleText(text);

	   logger.log(LogStatus.PASS, passStringGreenColor(step_description + " - Passed."));

	  } catch (Exception e) {
	   Reporter.log(locator_Path + " not Selected");

	   logger.log(LogStatus.INFO, failStringRedColor(step_description + " - Failed."));

	   imagePath = testFailTakeScreenshot();

	   // UploadFileFromWindowUtil.uploadFile(imagePath);

	   logger.log(LogStatus.FAIL, failStringRedColor("Test Case Failure Reason :- ") + "\n" + e);

	   pathForLogger = logger.addScreenCapture(imagePath);

	   logger.log(LogStatus.FAIL,
	     failStringRedColor("Cannot find " + locator_Path + "on Page :-" + pathForLogger));

	   throw e;

	  }
	 }
	 
	 /**
	  * Select by value.
	  *
	  * @param locator_Type the locator type
	  * @param locator_Path the locator path
	  * @param valueAttribute the value attribute
	  * @throws IOException Signals that an I/O exception has occurred.
	  */
	 public static void selectByValue(String locator_Type, String locator_Path, String valueAttribute) throws IOException {

	  /*
	   * wait = new
	   * WebDriverWait(driver,readLongFromProperties("Framework.properties",
	   * "ExplicitWaitTimeOut"));
	   */

	  try {
	   element = selectLocator(locator_Type, locator_Path);
	   /*
	    * wait.until(ExpectedConditions.presenceOfElementLocated(element));
	    * wait.until(ExpectedConditions.visibilityOfElementLocated(element) );
	    * wait.until(ExpectedConditions.elementToBeClickable(element));
	    */

	   Select dropDown = new Select(driver.findElement(element));
	   dropDown.selectByValue(valueAttribute);

	   logger.log(LogStatus.PASS, passStringGreenColor(locator_Path + " - Selected from drop down" + valueAttribute));

	  } catch (Exception e) {
	   Reporter.log(locator_Path + " not Selected");

	   logger.log(LogStatus.INFO, failStringRedColor("Select action : " + locator_Path + " - Failed."));

	   imagePath = testFailTakeScreenshot();

	   // UploadFileFromWindowUtil.uploadFile(imagePath);

	   logger.log(LogStatus.FAIL, failStringRedColor("Test Case Failure Reason :- ") + "\n" + e);

	   pathForLogger = logger.addScreenCapture(imagePath);

	   logger.log(LogStatus.FAIL,
	     failStringRedColor("Cannot find " + locator_Path + "on Page :-" + pathForLogger));

	   throw e;

	  }
	 }
	 
	 /**
	  * Select by index.
	  *
	  * @param locator_Type the locator type
	  * @param locator_Path the locator path
	  * @param index the index
	  * @throws IOException Signals that an I/O exception has occurred.
	  */
	 public static void selectByIndex(String locator_Type, String locator_Path, int index) throws IOException {

	  /*
	   * wait = new
	   * WebDriverWait(driver,readLongFromProperties("Framework.properties",
	   * "ExplicitWaitTimeOut"));
	   */

	  try {
	   element = selectLocator(locator_Type, locator_Path);
	   /*
	    * wait.until(ExpectedConditions.presenceOfElementLocated(element));
	    * wait.until(ExpectedConditions.visibilityOfElementLocated(element) );
	    * wait.until(ExpectedConditions.elementToBeClickable(element));
	    */

	   Select dropDown = new Select(driver.findElement(element));
	   dropDown.selectByIndex(index);

	   logger.log(LogStatus.PASS, passStringGreenColor(locator_Path + " - Selected from drop down" + index));

	  } catch (Exception e) {
	   Reporter.log(locator_Path + " not Selected");

	   logger.log(LogStatus.INFO, failStringRedColor("Select action : " + locator_Path + " - Failed."));

	   imagePath = testFailTakeScreenshot();

	   // UploadFileFromWindowUtil.uploadFile(imagePath);

	   logger.log(LogStatus.FAIL, failStringRedColor("Test Case Failure Reason :- ") + "\n" + e);

	   pathForLogger = logger.addScreenCapture(imagePath);

	   logger.log(LogStatus.FAIL,
	     failStringRedColor("Cannot find " + locator_Path + "on Page :-" + pathForLogger));

	   throw e;

	  }
	 }
	 
	 /**
 	 * Switch browser tab.
 	 *
 	 * @param windowNumber            the window number
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	 public static void switchBrowserTab(int windowNumber) throws IOException {
	  try {
	   System.out.println("start");
	   Set<String> wind = driver.getWindowHandles();
	   Iterator<String> iter = wind.iterator();
	   String firstwindow = iter.next();
	   String secondwind = iter.next();
	   // this script is used for window handler
	   if (windowNumber == 1)
	    driver.switchTo().window(firstwindow);
	   else if (windowNumber == 2)
	    driver.switchTo().window(secondwind);
	   System.out.println("End");

	   logger.log(LogStatus.PASS, passStringGreenColor("Window switched to " + windowNumber));
	  } catch (Exception e) {
	   Reporter.log("Window not switched.");

	   logger.log(LogStatus.INFO, failStringRedColor("Window Switch" + " - Failed."));

	   imagePath = testFailTakeScreenshot();

	   // UploadFileFromWindowUtil.uploadFile(imagePath);

	   logger.log(LogStatus.FAIL, failStringRedColor("Test Case Failure Reason :- ") + "\n" + e);

	   pathForLogger = logger.addScreenCapture(imagePath);

	   logger.log(LogStatus.FAIL, failStringRedColor("Window Switch - " + pathForLogger));

	   throw e;

	  }

	 }	
	 
	 /**
 	 * Click using JS.
 	 *
 	 * @param path the path
 	 * @param type the type
 	 * @return true, if successful
 	 */
 	public static boolean clickUsingJS(String path, String type) 
	 {
	   WebElement element = driver.findElement(selectLocator(type, path));

	   
	   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	   
	   return true;

	 }
	 
	 /**
 	 * Check pdf file is opened.
 	 *
 	 * @return the string
 	 * @throws InterruptedException the interrupted exception
 	 */
 	public static String checkPdfFileIsOpened() throws InterruptedException
		{
			Set<String> wind=driver.getWindowHandles();
			Iterator<String> iter=wind.iterator();
			String firstwindow=iter.next();
			String secondwindow=iter.next();	
			
			driver.switchTo().window(secondwindow);
			
			String getPdfFileOpenedURL = driver.getCurrentUrl();
			
			Thread.sleep(5000);
			
			driver.close();
			
			driver.switchTo().window(firstwindow);
			
			return getPdfFileOpenedURL;
		}
	
}
