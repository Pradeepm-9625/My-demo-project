package EXtentReport;

import java.io.File;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;



public class Functions {
	public static String Record = "/html/body/div[1]/div/div/div/div/div[3]/div[2]/div[2]/div/div/h5/span";
	public static String File = System.getProperty("user.home") + "\\Desktop\\Daily_Record_Count.xlsx";
	public static String Xpath = "/html/body/div/div/div/div/div/div[2]/div/nav/div[1]/div/ul/li";
	public static WebDriver driver;
	public static WebDriver driver1;
	public static Logger logger;
	public static XSSFWorkbook workbook;
	public static Row row;
	
	fg

	public static String FolderPath = "C:\\Users\\AIT\\Desktop\\Pradeep Tester\\Pradeep";

	public static void Chrome() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\AIT\\Desktop\\chromedriver_linux64\\chromedriver_win32 (3)\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
//		options.addArguments("--headless"); //uncomment to view the browser
		driver = new ChromeDriver(options);
		driver.get("https://app.firstclassdata.com/account/login"); 
		driver.manage().window().maximize();
	}

	public static void Edge() throws InterruptedException {
		System.setProperty("webdriver.edge.driver","C:\\Users\\AIT\\Downloads\\edgedriver_win64 (4)\\msedgedriver.exe");
		  // Setup WebDriverManager
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--remote-allow-origins=*");
//		 options.addArguments("--headless"); // Uncomment to run in headless mode
	    driver = new EdgeDriver(options);
		driver.get("https://app.firstclassdata.com/account/login");
		driver.manage().window().maximize();
       


	}

	public static void Element(String Locators, String Sendkeys) {
		WebElement Username = driver.findElement(By.name(Locators));
		Username.sendKeys(Sendkeys);
	}

	public static void Button(String Locators) throws InterruptedException {
		WebElement button = driver.findElement(By.xpath(Locators));
		button.click();
	}

	public static void Js(String path, String itemName, String page) throws InterruptedException, IOException {

		WebElement logo = driver.findElement(By.xpath(path));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; ')", logo);
		Thread.sleep(2000);
		Functions.screenshot(page);
		Thread.sleep(1000);
		jsExecutor.executeScript("arguments[0].removeAttribute('style','')", logo);
		Boolean logo_ = driver.findElement(By.xpath(path)).isDisplayed();
		if (logo_) {
			System.out.println("Yes ! " + itemName + " is Present");
		} else {
			System.out.println("NO ! " + itemName + " is not Present");
		}

		Thread.sleep(2000);
	}

	public static void initiateExcelSetup() throws InterruptedException {
		try {
			System.out.println("Excel setup has been started...");
			FileInputStream fs = new FileInputStream(File);
			workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheetAt(0);
			row = sheet.createRow(sheet.getLastRowNum() + 2);
			System.out.println("Excel setup has been Ended...");

		} catch (Exception ex) {
			System.out.println("Some error has occurred in Excel setup process. The error is " + ex.getMessage());
		}

	}

	public static String time() {
		  LocalDateTime now = LocalDateTime.now(); // Get current date and time
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define the format of the date and time
	        String formattedDateTime = now.format(formatter);
	        System.out.println(formattedDateTime);
		return formattedDateTime;

	}

	public static void screenshot(String image) throws IOException, InterruptedException {

		String current_time = Functions.time();
		String Folder_Created_date = Functions.time();

		// TakesScreenshot driver = driver;

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filename = image + current_time + ".png";
//	        FileUtils.copyFile(screenshot, new File(filename));
		System.out.println("\n");
		FileUtils.copyFile(screenshot, new File(
				FolderPath + "\\Auto create folder\\Record count\\Folder_" + Folder_Created_date + "\\" + filename));

		File imageFile = new File(
				FolderPath + "\\Auto create folder\\Record count\\Folder_" + Folder_Created_date + "\\" + filename);
		System.out.println("\nFile Name : " + imageFile);
		boolean screenshot1 = imageFile.exists();
		System.out.println("screenshot result : " + screenshot1);
		if (screenshot1) {
			System.out.println("Yes ! The Screenshot is captured");
		} else {
			System.out.println("NO ! The Screenshot is not captured");
		}

	}

	public static void folder() {
		String Folder_Created_date = Functions.time();

		File file = new File(FolderPath + "\\Auto create folder\\Record count\\Folder_" + Folder_Created_date);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");

			} else {
				System.out.println("Failed to create directory!");
			}
		}
	}

	public static void outputStream() throws IOException {
		FileOutputStream outputStream = new FileOutputStream(File);
		workbook.write(outputStream);
		workbook.close();
		driver.close();
	}
	public static void initializelogger() {
		  logger = Logger.getLogger("MyLog");
	        FileHandler fh;

	        try {
	            String folderCreatedDate = time();

	            File file = new File(FolderPath + "\\Log_" + folderCreatedDate);
	            if (!file.exists()) {
	                if (file.mkdirs()) {
	                    System.out.println("Directory is created!");
	                } else {
	                    System.out.println("Failed to create directory!");
	                }
	            }

	            // Configure the logger with the log file path
	            String logFilePath = file.getAbsolutePath() + "\\MyLogFile.log";
	            fh = new FileHandler(logFilePath);
	            logger.addHandler(fh);
	            SimpleFormatter formatter = new SimpleFormatter();
	            fh.setFormatter(formatter);

	            // Log a message
	            logger.info("My first log");

	        } catch (SecurityException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	public static void Js(String string, String string2) {
		// TODO Auto-generated method stub

	}

	public static void function() {
		System.out.println("File Path --> " + Functions.File);
		try {
			Functions.Element("username", "test");
			Functions.Element("password", "123");
			Functions.Button("/html/body/div[1]/div/div/div[1]/div/div/div[2]/form/div[3]/button");
			Functions.initiateExcelSetup();

//			LocalDate Current_Date = LocalDate.now();
			String Current_Date = Functions.time();
			Cell cell = Functions.row.createCell(0);
			cell.setCellValue(Current_Date.toString());
			Thread.sleep(3000);

//			        Insurance_Agent
			Functions.Js(Functions.Xpath + "[1]/a", "Insurance_Page");
			Thread.sleep(1000);

			WebElement Insurance = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "Insurance_Agent_");
			System.out.println(Insurance.getText());
			Thread.sleep(1000);
			Functions.row.createCell(1).setCellValue(Insurance.getText());

//        Insurance_Agency
//		Functions.Button("/html/body/div[1]/div/div/div/div/div[3]/div[2]/div[2]/div/div/div/ul/li[1]/span[1]");
			Functions.Button("/html/body/div/div/div/div/div/div[3]/div[2]/div[2]/div/div/div/ul/li[1]/span[1]");
			Thread.sleep(3000);
			WebElement Insurance_Agency = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "Insurance_Agency_");
			System.out.println(Insurance_Agency.getText());
			Thread.sleep(1000);
			Functions.row.createCell(2).setCellValue(Insurance_Agency.getText());

//			        Individual
			Functions.Button(Functions.Xpath + "[2]/a");
			Functions.Js(Functions.Xpath + "[2]/a", "Individual_Agent_page");

			WebElement Individual = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "Individual_Agent_");
			System.out.println(Individual.getText());
			Functions.row.createCell(3).setCellValue(Individual.getText());

//		        RIA
			Functions.Button(Functions.Xpath + "[4]/a");
			Functions.Js(Functions.Xpath + "[4]/a", "RIA_page");

			WebElement RIA = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "RIA");
			System.out.println(RIA.getText());
			Functions.row.createCell(4).setCellValue(RIA.getText());

//		        IAR
			Functions.Button(Functions.Xpath + "[5]/a");
			Functions.Js(Functions.Xpath + "[5]/a", "IAR_page");

			WebElement IAR = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "IAR_");
			System.out.println(IAR.getText());
			Functions.row.createCell(5).setCellValue(IAR.getText());

//		        BD
			Functions.Button(Functions.Xpath + "[6]/a");
			Functions.Js(Functions.Xpath + "[6]/a", "BD_page");

			WebElement BD = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "BD_");
			System.out.println(BD.getText());
			Functions.row.createCell(6).setCellValue(BD.getText());

//		        RR
			Functions.Button(Functions.Xpath + "[7]/a");
			Functions.Js(Functions.Xpath + "[7]/a", "RR_page");

			WebElement RR = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "RR_");
			System.out.println(RR.getText());
			Functions.row.createCell(7).setCellValue(RR.getText());

			// Save the Excel workbook to a file
			Functions.outputStream();
		} catch (Exception ex) {

			Functions.driver.close();
		}
	}
}
