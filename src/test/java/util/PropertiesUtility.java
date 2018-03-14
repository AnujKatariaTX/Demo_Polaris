package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Reporter;

// TODO: Auto-generated Javadoc
/**
 * <h1>PropertiesUtility class</h1> <br>
 * Utility Class for Properties <br>
 * <br>.
 *
 * @author TestingXperts
 * @version 1.0 <br>
 *          <br>
 */
public class PropertiesUtility {

	/** The prop. */
	static Properties prop;// create Properties object
	
	/** The fis. */
	static FileInputStream fis;// create FileInputStream object
	
	/** The fos. */
	static FileOutputStream fos;// create FileOutputStream object

	/** The string prop data. */
	static String stringPropData;
	
	/** The long prop data. */
	static long longPropData;
	
	/** The int prop data. */
	static int intPropData;

	/**
	 * This function reads String data from a Properties file. <br>
	 * <br>
	 *
	 * @author TestingXperts <br>
	 *         <br>
	 * @version 1.0 <br>
	 *          <br>
	 * @param fileName            - Name of the properties file. <br>
	 *            <br>
	 * @param propKey            - Key of the value to be extracted. <br>
	 *            <br>
	 * @return String value read from the properties file. <br>
	 *         <br>
	 * @category Utility Class for Properties
	 */
	public static String readStringFromProperties(String fileName, String propKey) {

		prop = new Properties();
		try {

			// initialize the FileInputStream object
			fis = new FileInputStream(fileName);

			// load the properties from the FileInputStream
			prop.load(fis);

			// catches Exception if the properties file is not found
		} catch (FileNotFoundException e) {

			System.out.println(e.getMessage());

			Reporter.log("Properties File not Found");

			// catches Exception if I/O operation not performed on the
			// properties file
		} catch (IOException e) {

			System.out.println(e.getMessage());

			Reporter.log("Properties File Data Read Failed");

		}

		// reads the value of the key from the properties file
		stringPropData = prop.getProperty(propKey);

		Reporter.log("Properties File Data Read Successful");

		return stringPropData;
	}

	/**
	 * This function reads long data from a Properties file. <br>
	 * <br>
	 *
	 * @author TestingXperts <br>
	 *         <br>
	 * @version 1.0 <br>
	 *          <br>
	 * @param fileName            - Name of the properties file. <br>
	 *            <br>
	 * @param propKey            - Key of the value to be extracted. <br>
	 *            <br>
	 * @return long value read from the properties file. <br>
	 *         <br>
	 * @category Utility Class for Properties
	 */
	public static long readLongFromProperties(String fileName, String propKey) {

		prop = new Properties();
		try {

			fis = new FileInputStream(fileName);
			prop.load(fis);

		} catch (FileNotFoundException e) {

			System.out.println(e.getMessage());

			Reporter.log("Properties File not Found");

		} catch (IOException e) {

			System.out.println(e.getMessage());

			Reporter.log("Properties File Data Read Failed");

		}

		// convert String data to long
		longPropData = Long.parseLong(prop.getProperty(propKey));

		Reporter.log("Properties File Data Read Successful");

		return longPropData;
	}

	/**
	 * This function reads int data from a Properties file. <br>
	 * <br>
	 *
	 * @author TestingXperts <br>
	 *         <br>
	 * @version 1.0 <br>
	 *          <br>
	 * @param fileName            - Name of the properties file. <br>
	 *            <br>
	 * @param propKey            - Key of the value to be extracted. <br>
	 *            <br>
	 * @return int value read from the properties file. <br>
	 *         <br>
	 * @category Utility Class for Properties
	 */
	public static int readIntFromProperties(String fileName, String propKey) {

		prop = new Properties();
		try {

			fis = new FileInputStream(fileName);
			prop.load(fis);

		} catch (FileNotFoundException e) {

			System.out.println(e.getMessage());

			Reporter.log("Properties File not Found");

		} catch (IOException e) {

			System.out.println(e.getMessage());

			Reporter.log("Properties File Data Read Failed");

		}

		// convert String data to int
		intPropData = Integer.parseInt(prop.getProperty(propKey));

		Reporter.log("Properties File Data Read Successful");

		return intPropData;
	}

	/**
	 * This function appends String data to a Properties file. <br>
	 * <br>
	 *
	 * @author TestingXperts <br>
	 *         <br>
	 * @version 1.0 <br>
	 *          <br>
	 * @param fileName            - Name of the properties file. <br>
	 *            <br>
	 * @param propKey            - Key of the value to be appended. <br>
	 *            <br>
	 * @param propValue            - Value of the key to be appended. <br>
	 *            <br>
	 * @category Utility Class for Properties
	 */
	public static void appendStringProperties(String fileName, String propKey, String propValue) {

		// initialize the Properties object
		prop = new Properties();

		try {

			// initialize the FileOutputStream object
			// open the properties file in appned mode
			fos = new FileOutputStream(fileName, true);

			prop.setProperty(propKey, propValue);

			// save properties to project root folder
			prop.store(fos, null);

			// catches Exception if the I/O operation fails
		} catch (IOException e) {

			System.out.println(e.getMessage());

		} finally {

			if (fos != null) {

				try {

					// close the FileOutputStream
					fos.close();

					Reporter.log("Properties File not Appended");

					// catches the Exception if the FileOutputStream not closed
				} catch (IOException e) {

					System.out.println(e.getMessage());

					Reporter.log("Properties File Appended");

				}
			}
		}
	}

	/**
	 * This function writes String data to a Properties file after truncating
	 * the existing data. <br>
	 * <br>
	 *
	 * @author TestingXperts <br>
	 *         <br>
	 * @version 1.0 <br>
	 *          <br>
	 * @param fileName            - Name of the properties file. <br>
	 *            <br>
	 * @param propKey            - Key of the value to be appended. <br>
	 *            <br>
	 * @param propValue            - Value of the key to be appended. <br>
	 *            <br>
	 * @category Utility Class for Properties
	 */
	public static void writeToProperties(String fileName, String propKey, String propValue) {

		prop = new Properties();

		try {

			// open the properties file in write mode
			fos = new FileOutputStream(fileName);

			prop.setProperty(propKey, propValue);

			prop.store(fos, null);

		} catch (IOException e) {

			System.out.println(e.getMessage());

		} finally {

			if (fos != null) {

				try {

					fos.close();

					Reporter.log("Properties File not Appended");

				} catch (IOException e) {

					System.out.println(e.getMessage());

					Reporter.log("Properties File Appended");

				}
			}
		}
	}

}
