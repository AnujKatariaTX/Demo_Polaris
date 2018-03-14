package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class MailUtility.
 */
public class MailUtility extends PropertiesUtility {

	/** The user name. */
	public static String userName;
	
	/** The password. */
	public static String password;

	/** The email to. */
	public static String emailTo;
	
	/** The email to CC. */
	public static String emailToCC;

	/** The host. */
	public static String host;
	
	/** The port. */
	public static String port;
	
	/** The starttls. */
	public static String starttls;
	
	/** The socket factory class. */
	public static String socketFactoryClass;
	
	/** The fallback. */
	public static String fallback;

	/**
	 * Send email to client.
	 */
	public static void sendEmailToClient() {

		userName = readStringFromProperties("Mail.properties", "userName");
		password = readStringFromProperties("Mail.properties", "password");

		emailTo = readStringFromProperties("Mail.properties", "emailTo");
		emailToCC = readStringFromProperties("Mail.properties", "emailToCC");

		final String subject = readStringFromProperties("Mail.properties", "subject");

		host = readStringFromProperties("Mail.properties", "host");
		port = readStringFromProperties("Mail.properties", "port");

		starttls = readStringFromProperties("Mail.properties", "starttls");
		socketFactoryClass = readStringFromProperties("Mail.properties", "socketFactoryClass");
		fallback = readStringFromProperties("Mail.properties", "fallback");

		Properties props = System.getProperties();
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		if (!"".equals(port)) {
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.socketFactory.port", port);
		}

		if (!"".equals(starttls))
			props.put("mail.smtp.starttls.enable", starttls);

		if (!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);

		if (!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);

		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(false);

		try {

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(userName, prop.getProperty("userFullName")));
			msg.setSubject(subject);

			if (!"".equals(emailToCC)) {

				if (emailTo.contains(",")) {
					String[] multipleEmailTo = emailTo.split(",");
					for (int j = 0; j < multipleEmailTo.length; j++) {
						if (j == 0)
							msg.addRecipient(Message.RecipientType.TO, new InternetAddress(multipleEmailTo[j]));
						else
							msg.addRecipient(Message.RecipientType.CC, new InternetAddress(multipleEmailTo[j]));
					}

				} else {
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
				}
			}

			/*
			 * if (emailToCC.contains(",")) { String[] multipleCC =
			 * emailToCC.split(",");
			 * 
			 * for (int i = 0; i < multipleCC.length; i++) {
			 * msg.addRecipient(Message.RecipientType.CC, new
			 * InternetAddress(multipleCC[i])); } } else
			 * 
			 * msg.addRecipient(Message.RecipientType.CC, new
			 * InternetAddress(emailToCC)); }
			 */

			else if (emailToCC == null || emailToCC.equals("")) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			}

			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText("Hi client, \n Please find Email Report for suite:- "
					+ readIntFromProperties("Mail.properties", "suiteName")
					+ " \n \n \n Thanks & Regards \n Test Engineer");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (new File(System.getProperty("user.dir") + "/output/JIRAhtmlReport").exists()) {
				delDirectory(new File(System.getProperty("user.home") + "/Downloads"));
			}

			if (readStringFromProperties("Mail.properties", "HTMLReport").contains("Y")) {
				copyDirectoryData("HTMLReportPath", "HTMLReportPath");
			}

			createZipFile();

			messageBodyPart = new MimeBodyPart();
			String path = System.getProperty("user.dir") + "/output/JIRAhtmlReport";
			DataSource source = new FileDataSource(path);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("HTMLReport.zip");
			multipart.addBodyPart(messageBodyPart);

			msg.setContent(multipart);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("mail sent successfully");
			// LogUtil.infoLog(sendMail.class, "Mail Sent" + "-PASS ");
			// HtmlReportUtil.stepInfo("Mail Sent" + "-PASS");
			delDirectory(new File(System.getProperty("user.dir") + "/output/JIRAhtmlReport"));

		} catch (AuthenticationFailedException e1) {
			JFrame wrongCredentials = new JFrame();
			JOptionPane.showMessageDialog(wrongCredentials, "Wrong Username or Password");

		} catch (Exception e) {
			e.printStackTrace();
			// LogUtil.infoLog(sendMail.class, "Mail Sent" + "-FAIL ");
			// HtmlReportUtil.stepInfo("Mail Sent" + "-FAIL");

		}

	}

	/**
	 * Del directory.
	 *
	 * @param dir the dir
	 */
	public static void delDirectory(File dir) {
		File[] currList;
		Stack<File> stack = new Stack<File>();
		stack.push(dir);
		while (!stack.isEmpty()) {
			if (stack.lastElement().isDirectory()) {
				currList = stack.lastElement().listFiles();
				if (currList.length > 0) {
					for (File curr : currList) {
						stack.push(curr);
					}
				} else {
					stack.pop().delete();
				}
			} else {
				stack.pop().delete();
			}
		}
		if (new File(System.getProperty("user.dir") + "/output/HTMLReport.zip").exists()) {
			delDirectory(new File(System.getProperty("user.dir") + "/output/HTMLReport.zip"));
		}
	}

	/**
	 * Copy directory data.
	 *
	 * @param sourceDir the source dir
	 * @param targetDir the target dir
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void copyDirectoryData(String sourceDir, String targetDir) throws IOException {
		File srcDir = new File(System.getProperty("user.dir") + "/output/" + sourceDir);
		File destDir = new File(System.getProperty("user.dir") + "/output/JIRAhtmlReports/" + targetDir);
		FileUtils.copyDirectory(srcDir, destDir);
	}

	/** The result folder name. */
	public static String result_FolderName = System.getProperty("user.dir") + "\\output\\JIRAhtmlReport\\HTMLReport";

	/**
	 * Creates the zip file.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String createZipFile() throws IOException {
		result_FolderName = result_FolderName.replace("\\", "/");
		String outputFile = result_FolderName + ".zip";
		FileOutputStream fos = new FileOutputStream(outputFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		packCurrentDirectoryContents(result_FolderName, zos);
		zos.closeEntry();
		zos.close();
		fos.close();
		return outputFile;
	}

	/**
	 * Pack current directory contents.
	 *
	 * @param directoryPath the directory path
	 * @param zos the zos
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void packCurrentDirectoryContents(String directoryPath, ZipOutputStream zos) throws IOException {
		for (String dirElement : new File(directoryPath).list()) {
			String dirElementPath = directoryPath + "/" + dirElement;
			if (new File(dirElementPath).isDirectory()) {
				packCurrentDirectoryContents(dirElementPath, zos);
			} else {
				ZipEntry ze = new ZipEntry(dirElementPath.replaceAll(result_FolderName + "/", ""));
				zos.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(dirElementPath);
				byte[] bytesRead = new byte[512];
				int bytesNum;
				while ((bytesNum = fis.read(bytesRead)) > 0) {
					zos.write(bytesRead, 0, bytesNum);
				}

				fis.close();
			}
		}
	}

}
