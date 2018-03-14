package locators;

// TODO: Auto-generated Javadoc
/**
 * <h1>Eligibility Assessment Locators class</h1> <br>
 * Locator class for Eligibility Assessment <br>
 * <br>.
 *
 * @author TestingXperts
 * @version 1.0 <br>
 * 			<br>
 */
public class UserRegistration {

	/** The register link. */
	public static String registerLink= "//a[text()='Register']";
	
	/** The next button. */
	public static String nextButton= "//input[@value='Next']";
	
	/** The accept button. */
	public static String acceptButton= "//input[@value='Accept']";
	
	/** The textbox intermediary group name. */
	public static String textboxIntermediaryGroupName="newIntermediaryGroupForm:name";
	
	/** The textbox address 1. */
	public static String textboxAddress1="newIntermediaryGroupForm:address1";
	
	/** The textbox post code. */
	public static String textboxPostCode="newIntermediaryGroupForm:postCode";//id
	
	/** The textbox principal name. */
	public static String textboxPrincipalName="newIntermediaryGroupForm:principalName";//id
	
	/** The textbox contact number. */
	public static String textboxContactNumber="newIntermediaryGroupForm:contactNumber";//id
	
	/** The textbox go to step 2. */
	public static String textboxGoToStep2="newIntermediaryGroupForm:forward";//id
	
	/** The textbox first name. */
	public static String textboxFirstName="newIntermediaryGroupForm:firstName";//id
	
	/** The textbox last name. */
	public static String textboxLastName="newIntermediaryGroupForm:lastName";//id
	
	/** The textbox company email. */
	public static String textboxCompanyEmail="newIntermediaryGroupForm:companyEmail";//id
	
	/** The list box insurers list. */
	public static String listBoxInsurersList="newIntermediaryGroupForm:newIntGrpPickList_AVAILABLE";//id
	
	/** The button selected. */
	public static String buttonSelected="//input[@value='>' and @type='button']";
	
	/** The textbox insurance account. */
	public static String textboxInsuranceAccount="newIntermediaryGroupForm:dataTable:0:reference";//id
	
	/** The h 1 next. */
	public static String h1Next="//form[@id='introPage2ID']/h4";
	
	/** The h 2 next. */
	public static String h2Next="//form[@id='introPage3ID']/h4";
	
	/** The pdf security incident report link. */
	public static String pdfSecurityIncidentReportLink= "//a[text()='Launch Security Incident Report.pdf']";
	
	/** The error message. */
	public static String errorMessage= "//span[@class='error']";
	
	/** The confirmation M 1. */
	public static String confirmationM1= "//form[@id='newIntermediaryGroupForm']/h4";
	
	/** The confirmation M 2. */
	public static String confirmationM2= "//form[@id='newIntermediaryGroupForm']/p[1]";
	
	/** The confirmation M 3. */
	public static String confirmationM3= "//form[@id='newIntermediaryGroupForm']/p[2]";
	
	/** The confirmation M 4. */
	public static String confirmationM4= "//form[@id='newIntermediaryGroupForm']/p[3]";
	
	/** The confirmation M 5. */
	public static String confirmationM5= "//form[@id='newIntermediaryGroupForm']/p[4]";
	
	/** The welcome pdf. */
	public static String welcomePdf= "//a[contains(@href,'/pdf/Welcome/welcome_pack.pdf')]";

}
