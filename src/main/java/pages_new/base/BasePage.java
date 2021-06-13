package pages_new.base;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants_new.Constants.TimeOutVariables.EXPLICIT_WAIT;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public final By authWidget = By.xpath("//iframe[@src='https://login-widget.privat24.ua/']");



//    TODO the method for navigating to specific URL
    public void goToUrl(String url){
        driver.get(url);

    }
//  TODO  Wait  for visibility element in DOM model
    public WebElement waitElementIsVisible(WebElement element){
//        visibilityOf(WebElement element) — ожидание видимости присутствующего в DOM элемента,
        new WebDriverWait(driver,EXPLICIT_WAIT).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

//    TODO Check is auth frame is visible
   public   void isAutWidgetPresent(){
        WebElement authFrame = driver.findElement(authWidget);
        waitElementIsVisible(authFrame);
    }


}
