package IMarket_Registration;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import locators.UserRegistration;
import util.FunctionsUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistrationSuccessfully.
 */
public class RegistrationSuccessfully extends FunctionsUtility {
	/** The this class. */
	@SuppressWarnings("rawtypes")
	static Class thisClass = RegistrationSuccessfully.class;

	/** The test case ID. */
	static String testCaseID = thisClass.getSimpleName();

	/**
	 * Form Registration successfully.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Test(groups = {"Regression" })
	public static void userRegistrationSuccessfully() throws IOException, InterruptedException {

		className = new Object() {
		}.getClass().getEnclosingMethod().getName();
		logger = extent.startTest(className);

		logger.setDescription("This Test method to register on the user Registration form successfully");
		logger.getDescription();
		Thread.sleep(5000);

		long time = System.nanoTime();

/******************Variables fetching from excel file using in testcase to register intermediary group************/ 
		
		String intermediaryGroupName = getTestDataFromExcelFirstRow("RegistrationSuccessfully",
				"intermediaryGroupName");
		String address1 = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "address1");
		String postCode = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "postCode");
		String principalName = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "principalName");
		String contactNumber = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "contactNumber");
		String firstName = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "firstName");
		String lastName = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "lastName");
		String companyEmail = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "companyEmail");
		String insurerName = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "insurerName");
		String insuranceAccountNumber = getTestDataFromExcelFirstRow("RegistrationSuccessfully",
				"insuranceAccountNumber");
		String h1NextText = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "h1NextText");
		String h2NextText = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "h2NextText");
		String securityIncidentReport = getTestDataFromExcelFirstRow("RegistrationSuccessfully",
				"securityIncidentReport");
		String errorMessageGroupName = getTestDataFromExcelFirstRow("RegistrationSuccessfully",
				"errorMessageGroupName");
		String errorMessage2 = getTestDataFromExcelFirstRow("RegistrationSuccessfully",
				"errorMessage2");
		String confirmationMessage1 = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "confirmationMessage1");
		String confirmationMessage2 = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "confirmationMessage2");
		String confirmationMessage3 = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "confirmationMessage3");
		String confirmationMessage4 = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "confirmationMessage4");
		String confirmationMessage5 = getTestDataFromExcelFirstRow("RegistrationSuccessfully", "confirmationMessage5");
		String intermediaryGroupName2 = getTestDataFromExcelFirstRow("RegistrationSuccessfully",
				"intermediaryGroupName2");
		String pdfSecurityIncidentReportUrl = getTestDataFromExcelFirstRow("RegistrationSuccessfully",
				"pdfSecurityIncidentReportUrl");
		String pdfWelcomeUrl = getTestDataFromExcelFirstRow("RegistrationSuccessfully",
				"pdfWelcomeUrl");

/*---------------------------------------------------------Test Case------------------------------------------------------------------------------------------*/
		
		//Clicking on Register Link
		clickOnElement("xpath", UserRegistration.registerLink, "Click On Registration Link");

		//Clicking on Next Link and verifying text
		clickOnElement("xpath", UserRegistration.nextButton, "Click On Page 1 Next Button");
		Assert.assertTrue(h1NextText.contains(getText("xpath", UserRegistration.h1Next, "Get Text of the First Page Heading")));
		Thread.sleep(3000);
		
		//Clicking on Next Link and verifying text
		clickOnElement("xpath", UserRegistration.nextButton, "Click On Page 2 Next Button");
		Assert.assertTrue(h2NextText.contains(getText("xpath", UserRegistration.h2Next, "Get Text of the Second Page Heading")));
		Thread.sleep(3000);

		//Clicking on Next Link and verifying pdf is there
		clickOnElement("xpath", UserRegistration.nextButton, "Click On Page 3 Next Button");
		Assert.assertTrue(
				securityIncidentReport.contains(getText("xpath", UserRegistration.pdfSecurityIncidentReportLink, "Get Text of the PDF Security Incident Report Link")));
		Thread.sleep(3000);

		//Opening PDF and verifying PDF is opened
		clickOnElement("xpath", UserRegistration.pdfSecurityIncidentReportLink, "Click On Terms & Conditions PDF");
		Assert.assertEquals(checkPdfFileIsOpened(), pdfSecurityIncidentReportUrl);
		Thread.sleep(5000);
		
		//Clicking on Accept Button
		clickOnElement("xpath", UserRegistration.acceptButton, "Click On Terms & conditions Accept Button");
		Thread.sleep(3000);
		
		//Input existing Intermediary Group Name, address, post code, principal name, contact Number
		inputData("id", UserRegistration.textboxIntermediaryGroupName, intermediaryGroupName, "Enter Intermediary Group Name");
		inputData("id", UserRegistration.textboxAddress1, address1, "Enter Address 1");
		inputData("id", UserRegistration.textboxPostCode, postCode, "Enter Post Code");
		inputData("id", UserRegistration.textboxPrincipalName, principalName, "Enter Principal Name");
		inputData("id", UserRegistration.textboxContactNumber, contactNumber, "Enter Contact Number");
		Thread.sleep(3000);
		
		//Clicking on Step2 and verifying error message
		clickOnElement("id", UserRegistration.textboxGoToStep2, "Click On Step 2 Button");
		Assert.assertTrue(errorMessageGroupName.contains(getText("xpath", UserRegistration.errorMessage, "Get Text of the Error Message of Blank Group Name")));
		Thread.sleep(3000);
		
		//Input unique Intermediary Group Name and clicking on Step 2
		inputData("id", UserRegistration.textboxIntermediaryGroupName, intermediaryGroupName + time, "Enter Unique Intermediary Group Name");
		clickOnElement("id", UserRegistration.textboxGoToStep2, "Click On Step 2 Button");
		Thread.sleep(3000);
		
		//Input existing Intermediary Name and clicking on step 3 and verifying error message
		inputData("id", UserRegistration.textboxIntermediaryGroupName, intermediaryGroupName2, "Edit Intermediary Group Name");
		clickOnElement("id", UserRegistration.textboxGoToStep2, "Click On Step 3 Button");
		Assert.assertTrue(errorMessage2.contains(getText("xpath", UserRegistration.errorMessage, "Get Text of the Error Message of Intermediary Group Name")));
		Thread.sleep(3000);
		
		//Input unique Intermediary Name and clicking on step 3
		inputData("id", UserRegistration.textboxIntermediaryGroupName, intermediaryGroupName2 + time, "Edit Unique Intermediary Group Name");
		clickOnElement("id", UserRegistration.textboxGoToStep2, "Click On Step 3 Button");
		Thread.sleep(3000);
		
		// Input first name , last name and company email
		inputData("id", UserRegistration.textboxFirstName, firstName, "Enter First Name");
		inputData("id", UserRegistration.textboxLastName, lastName, "Enter Last Name");
		inputData("id", UserRegistration.textboxCompanyEmail, companyEmail, "Enter Company Email");
		Thread.sleep(3000);
		
		//Clicking on Step4 and select insurer from insurer list
		clickOnElement("id", UserRegistration.textboxGoToStep2, "Click On Step 4 Button");
		selectByVisibleText("id", UserRegistration.listBoxInsurersList, insurerName, "Select the Insurer List");
		clickOnElement("xpath", UserRegistration.buttonSelected, "Click On Insurers Selected Button");
		Thread.sleep(3000);
		
		//Clicking on Step 5 and input account number and clicking on Finish
		clickOnElement("id", UserRegistration.textboxGoToStep2, "Click On Step 5 Button");
		inputData("id", UserRegistration.textboxInsuranceAccount, insuranceAccountNumber, "Enter Insurer Account Number");
		clickOnElement("id", UserRegistration.textboxGoToStep2, "Click On Finish Button");
		Thread.sleep(3000);
		
		//Confirmation message verification
		Assert.assertTrue(confirmationMessage1.contains(getText("xpath", UserRegistration.confirmationM1, "Get Text of the First Confirmation Message")));
		Assert.assertTrue(confirmationMessage2.contains(getText("xpath", UserRegistration.confirmationM2, "Get Text of the Second Confirmation Message")));
		Assert.assertTrue(confirmationMessage3.contains(getText("xpath", UserRegistration.confirmationM3, "Get Text of the Third Confirmation Message")));
		Assert.assertTrue(confirmationMessage4.contains(getText("xpath", UserRegistration.confirmationM4, "Get Text of the Fourth Confirmation Message")));
		Assert.assertTrue(confirmationMessage5.contains(getText("xpath", UserRegistration.confirmationM5, "Get Text of the Fifth Confirmation Message")));
		
		//PDF file verification
		clickOnElement("xpath", UserRegistration.welcomePdf, "Click On Welcome PDF link");
		Assert.assertEquals(checkPdfFileIsOpened(), pdfWelcomeUrl);
		Thread.sleep(5000);
	}

}
