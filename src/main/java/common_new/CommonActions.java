package common_new;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static common_new.Config.BROWSER_AND_PLATFORM;
import static constants_new.Constants.TimeOutVariables.IMPLICIT_WAIT;

public class CommonActions {

    public static WebDriver createDriver(){
        WebDriver driver = null;
        switch (BROWSER_AND_PLATFORM){
            case "CHROME_WINDOWS":
                System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Downloads\\WDM\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            default:
                Assertions.fail("incorrect browser name " + BROWSER_AND_PLATFORM);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        return driver;
    }
}
