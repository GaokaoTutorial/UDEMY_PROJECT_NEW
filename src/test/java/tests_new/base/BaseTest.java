package tests_new.base;

import common_new.CommonActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages_new.base.BasePage;
import pages_new.loans.CarLoansPage;
import pages_new.telecomunication.MobilePhoneReplenishmentPage;


import java.io.File;
import java.time.LocalTime;
import java.util.Objects;

import static common_new.Config.*;

//расширяем добавлением Listener.class
@ExtendWith(Listener.class)
//нам необходимо чтобы все тесты которые наследуются от BaseTest
//запускались многопоточно
//@Execution(ExecutionMode.CONCURRENT)
//все анатации которые указанны в BaseTest будут выполняться после прогона каждого класса
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected WebDriver driver = CommonActions.createDriver();
    protected BasePage basePage = new BasePage(driver);
    protected CarLoansPage carLoansPage = new CarLoansPage(driver);
    protected MobilePhoneReplenishmentPage mobilePhoneReplenismentPage = new MobilePhoneReplenishmentPage(driver);
    private static final Logger LOGGER =  LoggerFactory.getLogger(BaseTest.class);

    static {
//        начало очистки директории
        LOGGER.info("START TIME: " + LocalTime.now());
        LOGGER.info("Start clear reports dir : build_new/reports_new");
        File alureResults = new File("target/allure-results");
//        Каталог - это объект класса File, который содержит список других файлов и каталогов.
//        После создания объекта класса File, являющего каталогом,
//                его метод isDirectory() вернёт значение true. И тогда вы можете вызывать метод list().
        if(alureResults.isDirectory()){
//            Objects.requireNonNull()
//                    , который внутренне выдает,
//            NullPointerExceptionесли данный объект (аргумент) есть null.
//                    Но NullPointerExceptionбудет брошен в любом случае, если nullобъект разыменован.
//            Итак, зачем делать эту дополнительную нулевую проверку и бросок NullPointerException?
//            Один очевидный ответ (или преимущество) состоит в том,
//                    что он делает код более читабельным, и я согласен.
//            Я хотел бы знать любые другие причины для использования Objects.requireNonNull()в начале метода.

//            Метод имеет несколько перегруженных версий. Он возвращает список файлов в виде массива объектов класса File.
//            Одна из версий метода также использует интерфейс FilenameFilter.
//            Другая версия использует интерфейс FileFilter и возвращает те файлы,
//                    путевые имена которых соответствует интерфейсу.
            for(File item: Objects.requireNonNull(alureResults.listFiles())){
                    item.delete();
            }if(CLEAR_REPORTS_DIR){
                File allureScreenshots = new File("build_new/reports_new/test_new");
                for (File item:Objects.requireNonNull(allureScreenshots.listFiles())){
                    item.delete();
                }
            }

        }

    }
//    afterEach' работает как функции destroy , где обычно мы пишем информацию о высвобождении памяти.
//    Clean browser cookies after each iteration
//    после каждого прогона теста будем чистить куки
    @AfterEach
    void clearCookiesAndLocalStorage(){
        if(CLEAR_COOKIES){
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
//            чистим все сессионные данные которые находятся в определеенном открытом окне
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

    @AfterAll
    void close(){
        if(!HOLD_BROWSER_OPEN){
            driver.close();
        }
    }

}
