package EXtentReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

import EXtentReport.EXtentReport;

public class EXtentReport {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("File Path --> " + Functions.File);
		Functions.Edge();
//		Functions.Chrome();
		try {
			Functions.Element("username", "manimaran.r@aitechindia.com");
			Functions.Element("password", "Spring2022!");
			Functions.Button("/html/body/div[1]/div/div/div[1]/div/div/div[2]/form/div[3]/button");
			Functions.initiateExcelSetup(); 

//				LocalDate Current_Date = LocalDate.now();
			String Current_Date = Functions.time();
			Cell cell = Functions.row.createCell(0);
			cell.setCellValue(Current_Date.toString());
			Thread.sleep(3000); 

//				        Insurance_Agent
			Functions.Js(Functions.Xpath + "[1]/a", "Insurance_Page");
			Thread.sleep(1000);

			WebElement Insurance = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "Insurance_Agent_");
			System.out.println(Insurance.getText());
			Thread.sleep(1000);
			Functions.row.createCell(1).setCellValue(Insurance.getText());
			
//	        Insurance_Agency
//			Functions.Button("/html/body/div[1]/div/div/div/div/div[3]/div[2]/div[2]/div/div/div/ul/li[1]/span[1]");
			Functions.Button("/html/body/div/div/div/div/div/div[3]/div[2]/div[2]/div/div/div/ul/li[1]/span[1]");
			Thread.sleep(3000);
			WebElement Insurance_Agency = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "Insurance_Agency_");
			System.out.println(Insurance_Agency.getText());
			Thread.sleep(1000);
			Functions.row.createCell(2).setCellValue(Insurance_Agency.getText());

//				        Individual
			Functions.Button(Functions.Xpath + "[2]/a");
			Functions.Js(Functions.Xpath + "[2]/a", "Individual_Agent_page");

			WebElement Individual = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "Individual_Agent_");
			System.out.println(Individual.getText());
			Functions.row.createCell(3).setCellValue(Individual.getText());

//			        RIA
			Functions.Button(Functions.Xpath + "[4]/a");
			Functions.Js(Functions.Xpath + "[4]/a", "RIA_page");

			WebElement RIA = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "RIA");
			System.out.println(RIA.getText());
			Functions.row.createCell(4).setCellValue(RIA.getText());

//			        IAR
			Functions.Button(Functions.Xpath + "[5]/a");
			Functions.Js(Functions.Xpath + "[5]/a", "IAR_page");

			WebElement IAR = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "IAR_");
			System.out.println(IAR.getText());
			Functions.row.createCell(5).setCellValue(IAR.getText());

//			        BD
			Functions.Button(Functions.Xpath + "[6]/a");
			Functions.Js(Functions.Xpath + "[6]/a", "BD_page");

			WebElement BD = Functions.driver.findElement(By.xpath(Functions.Record));
			Functions.Js(Functions.Record, "Records", "BD_");
			System.out.println(BD.getText());
			Functions.row.createCell(6).setCellValue(BD.getText());

//			        RR
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
	}}

