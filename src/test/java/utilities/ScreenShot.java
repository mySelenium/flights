package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	
	public static String takeScreentshot(WebDriver driver, String fileName) throws IOException{
		
		String directory = "C:\\Users\\Timmy\\LUFTHANSA\\Screenshots\\";
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(directory + fileName + ".png"));
		String destination = directory + fileName + ".png";
		 return destination;
		
		
	}

}
