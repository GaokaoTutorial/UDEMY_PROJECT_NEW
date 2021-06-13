package tests_new.base;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;


public class Listener implements TestWatcher {

    private static final Logger  LOGGER =  LoggerFactory.getLogger(Listener.class);

    @Override
//    context информация о прогоне сборки
    public void testFailed(ExtensionContext context, Throwable cause) {
//        логируем имя теста который упал
    LOGGER.info("Test failed: " + context.getTestMethod().get().getName());;
//    если в одном классе упадет методы с одинаковыми именами можем их отличить
    String screenshotName = context.getTestMethod().get().getName() +
            String.valueOf(System.currentTimeMillis()).substring(9,13);
    LOGGER.info("Trying to trace screenShot");
//   получаем из контекса(область которая хранит информацию о проведении всех тестов) скрин теста который упал
//        TakesScreenshot селеневская библиотека которая содержит методы по роботе с скринами
//        context.getRequiredTestInstance()).driver получить инстанс теста который будет пройден и
//                будет упавший в него необходимо прокинуть драйвер и все это закастить бейс тесту
//                что бы смочь прокинуть драйвер
        TakesScreenshot ts = (TakesScreenshot)((BaseTest)context.getRequiredTestInstance()).driver;

//        запаковуем файл
//        getScreenshotAs говорим как хотим получить скрин OutputType.FILE
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
//            сохраняем данные в файл
            FileUtils.copyFile(source,new File("build_new/reports_new/test_new/" + screenshotName + ".png"));
        } catch (IOException e) {
            LOGGER.info("Exception on saving screen");
            e.printStackTrace();
        }
        attachScreenShotsToAllure(ts);
    }

    @Attachment
    public byte [] attachScreenShotsToAllure(TakesScreenshot takesScreenshot){
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }
}
