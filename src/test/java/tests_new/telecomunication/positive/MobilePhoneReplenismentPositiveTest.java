package tests_new.telecomunication.positive;

import org.junit.jupiter.api.Test;



import tests_new.base.BaseTest;

import static constants_new.Constants.MobileReplenishmentTestDate.*;
import static constants_new.Constants.Urls.Mobile_PAIMENT_URL;



public class MobilePhoneReplenismentPositiveTest extends BaseTest {


    @Test
    public void checkToAuth(){
        basePage.goToUrl(Mobile_PAIMENT_URL);
        mobilePhoneReplenismentPage.selectCardFromWallet();
        basePage.isAutWidgetPresent();

    }

    @Test
    public void checkMinimumrReplenismentAmount(){
        basePage.goToUrl(Mobile_PAIMENT_URL);
        mobilePhoneReplenismentPage
                .enterPhoneNumber(Mobile_PAIMENT_PHONE_NUMBER)
                .enterAmount("1")
                .enterCardFrom(Mobile_PAIMENT_CARD)
                .enterCardExpDate(Mobile_PAIMENT_CARD_EXP_DATE)
                .enterCvv(Mobile_PAIMENT_CARD_CVV)
                .submitToTheCart()
                .checkPaymentDetailsIsPresentInTheCard("Поповнення телефону. На номер +380975417147");
    }



}
