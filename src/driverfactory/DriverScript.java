package driverfactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonfunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
	ExtentReports reports;
	ExtentTest logger;
	String inputpath = "./FileInput/TestData.xlsx";
	String outputpath = "./FileOutput/TestResults.xlsx";
	@Test
	public void startTest() throws Throwable {
		reports = new ExtentReports("./Reports/EmployeeLogin.html");
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in sheet
		int rows = xl.rowCount("Emp");
		Reporter.log("No of rows: "+ rows,true);
		//read test data in sheet
		for(int r=1;r<=rows; r++) {
			logger = reports.startTest("Employee Login check");
			logger.assignAuthor("Deepthi");
			String username = xl.getCellData("Emp", r, 0);
			String password = xl.getCellData("Emp", r, 1);
			logger.log(LogStatus.INFO, username+"  "+ password);
			boolean result = FunctionLibrary.adminLogin(username, password);
			if(result) {
				xl.setCellData("Emp", r, 2, "Login success", outputpath);
				xl.setCellData("Emp", r, 3, "Pass", outputpath);
				logger.log(LogStatus.PASS, "Valid username and password");
			}
			else {
				//adding screen shot for fail test
				File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//copy screen shot into local system
				FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+r+"Login.png"));
				xl.setCellData("Emp", r, 2, "Login fail", outputpath);
				xl.setCellData("Emp", r, 3, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "Invalid username and password");
				
			}
			reports.endTest(logger);
			reports.flush();
			
		}
		
		
	}

}






