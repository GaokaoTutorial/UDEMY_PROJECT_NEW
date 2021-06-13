package pages_new.loans;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages_new.base.BasePage;

public class CarLoansPage extends BasePage  {

    //    public CarLoansPage() {
//    }
    //    WebDriver driver;

//    public CarLoansPage(WebDriver driver) {
//        this.driver = driver;
//    }
    public final By tabAgreements = By.xpath("//div[contains(text(), 'Договори')]");

    public CarLoansPage(WebDriver driver) {
        super(driver);
    }

    public CarLoansPage selectAgreementsTab(){
        driver.findElement(tabAgreements).click();
        return this;
    }
}
