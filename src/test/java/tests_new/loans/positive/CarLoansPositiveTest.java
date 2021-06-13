package tests_new.loans.positive;

import org.junit.jupiter.api.Test;
import tests_new.base.BaseTest;

import static constants_new.Constants.Urls.CAR_LOAN_URL;

public class CarLoansPositiveTest extends BaseTest {

    @Test
    public void selectAgreementsTabInPublicSession(){
        basePage.goToUrl(CAR_LOAN_URL);
        carLoansPage.selectAgreementsTab();
        basePage.isAutWidgetPresent();
    }
}
