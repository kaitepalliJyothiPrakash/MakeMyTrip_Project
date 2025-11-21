
package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class Screenshot {

    public static void getScreenshot(WebDriver driver, String imageName) throws IOException {
        
            TakesScreenshot screenshot = (TakesScreenshot)driver;
            File ss = (File)screenshot.getScreenshotAs(OutputType.FILE);
            File Destination = new File("Screenshots/"+imageName + ".png");
            FileUtils.copyFile(ss, Destination);       
        }
}
